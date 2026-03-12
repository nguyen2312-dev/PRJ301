/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DButil;

/**
 *
 * @author khoin
 */
public class tblHomestaysDAO {

    List<tblHomestaysDTO> homestayList;

    public List<tblHomestaysDTO> getList() {
        return homestayList;
    }

    public void showHomestayList(String userId)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DButil.getConnection();

            if (con != null) {
                String sql = "SELECT * FROM tblHomestays WHERE userID = ?";

                pstm = con.prepareStatement(sql);

                pstm.setString(1, userId);

                rs = pstm.executeQuery();

                while (rs.next()) {
                    String homeId = rs.getString("homeID");
                    String description = rs.getString("description");
                    int price = rs.getInt("price");
                    String address = rs.getString("address");
                    tblHomestaysDTO dto = new tblHomestaysDTO(homeId, description, price, address, userId);

                    if (this.homestayList == null) {
                        homestayList = new ArrayList<>();
                    }

                    homestayList.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean addNewHomestay(tblHomestaysDTO homestay)
            throws SQLException, ClassNotFoundException {
        boolean result = false;

        Connection con = null;

        PreparedStatement pstm = null;

        try {
            con = DButil.getConnection();

            if (con != null) {
                String sql = "INSERT INTO tblHomestays("
                        + "homeID, description, price, address, userID"
                        + ") VALUES ("
                        + "?, ?, ?, ?, ?"
                        + ")";

                pstm = con.prepareStatement(sql);

                pstm.setString(1, homestay.getHomeId());
                pstm.setString(2, homestay.getDescription());
                pstm.setInt(3, homestay.getPrice());
                pstm.setString(4, homestay.getAddress());
                pstm.setString(5, homestay.getUserId());

                int effectedRows = pstm.executeUpdate();

                if (effectedRows != 0) {
                    result = true;
                }
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;

    }
    
    public boolean updateHomestay(tblHomestaysDTO dto)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;

        boolean result = false;

        try {
            con = DButil.getConnection();
            String sql = "UPDATE tblHomestays "
                    + "SET description = ?, "
                    + "price = ?, "
                    + "address = ? "
                    + "WHERE "
                    + "homeID = ? AND userID = ?";
            
            pstm = con.prepareStatement(sql);
            pstm.setString(1, dto.getDescription());
            pstm.setInt(2, dto.getPrice());
            pstm.setString(3, dto.getAddress());
            pstm.setString(4, dto.getHomeId());
            pstm.setString(5, dto.getUserId());
            
            int effectedRow = pstm.executeUpdate();
            
            if(effectedRow > 0){
                result = true;
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
