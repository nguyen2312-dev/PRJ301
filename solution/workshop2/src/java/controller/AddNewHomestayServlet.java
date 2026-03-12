/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.tblHomestaysDAO;
import model.tblHomestaysDTO;
import model.tblHomestaysError;
import model.tblUsersDTO;

/**
 *
 * @author khoin
 */
@WebServlet(name = "AddNewHomestayServlet", urlPatterns = {"/AddNewHomestayServlet"})
public class AddNewHomestayServlet extends HttpServlet {

    private static final String ERROR_PAGE = "addNew.jsp";
    private static final String SUCCESS_PAGE = "ListHomestayServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;

        String homeId = request.getParameter("txtHomeId");

        String description = request.getParameter("txtDescription");

        String strPrice = request.getParameter("txtPrice");

        String address = request.getParameter("txtAddress");

        HttpSession session = request.getSession(false);
        tblUsersDTO dto = (tblUsersDTO) session.getAttribute("LOGIN_INFO");
        String userId = dto.getUserId();

        boolean error = false;
        tblHomestaysError err = new tblHomestaysError();
        int price = 0;

        try {

            if (homeId.trim().length() <= 2 || homeId.trim().length() >= 7) {
                error = true;
                err.setHomeIdLengthErr("Homestay ID is required from 2 - 6 character");
            }

            if (description.trim().length() < 2 || description.trim().length() >= 50) {
                error = true;
                err.setDescriptionLengthErr("Description is required from 2 - 50 characters");
            }

            if (strPrice != null && !strPrice.trim().isEmpty()) {
                try {
                    price = Integer.parseInt(strPrice);
                } catch (NumberFormatException e) {
                    error = true;
                    err.setPriceErr("Price must be a number");
                }
            }

            if (address.trim().length() < 2 || address.trim().length() >= 50) {
                error = true;
                err.setAddressLenghtErr("Address is required from 2 - 50 characters");
            }

            if (error) {
                request.setAttribute("HOMESTAY_ERR", err);
            } else {
                tblHomestaysDAO dao = new tblHomestaysDAO();
                tblHomestaysDTO homedto = new tblHomestaysDTO(homeId, description, price, address, userId);
                boolean result = dao.addNewHomestay(homedto);

                if (result) {
                    url = SUCCESS_PAGE;
                }
            }

        } catch (NumberFormatException e) {
            log("AddNewHomestayServlet_NumberFormatException: " + e.getMessage());
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("duplicate")) {
                err.setHomeIdExsitedErr("THIS HOME ID " + homeId + "IS EXISTED");
                request.setAttribute("HOMESTAY_ERR", err);
            }
            log("AddNewHomestayServlet_SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("AddNewHomestayServlet_ClassNotFoundException: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
