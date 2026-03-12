/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
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
public class HomestaysDAO implements Serializable {

    List<HomestaysDTO> homestaysList;

    public List<HomestaysDTO> getHomestays() {
        return homestaysList;
    }

    public void searchHomestaysByAddress(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DButil.getConnection();

            if (con != null) {
                String sql = "SELECT * FROM tblHomestays WHERE address LIKE ? ";
                pstm = con.prepareStatement(sql);

                pstm.setString(1, "%" + searchValue + "%");

                rs = pstm.executeQuery();

                while (rs.next()) {
                    String homeID = rs.getString("homeID");
                    String description = rs.getString("description");
                    int price = rs.getInt("price");
                    String address = rs.getString("address");
                    String userID = rs.getString("userID");

                    HomestaysDTO dto = new HomestaysDTO(homeID, description, price, address, userID);

                    if (this.homestaysList == null) {
                        homestaysList = new ArrayList<>();
                    }

                    homestaysList.add(dto);
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

    public boolean deleteHomestays(String homeId)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DButil.getConnection();

            if (con != null) {
                String sql = "DELETE FROM tblHomestays WHERE homeID = ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, homeId);

                int effectedRows = pstm.executeUpdate();

                if (effectedRows > 0) {
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
}
