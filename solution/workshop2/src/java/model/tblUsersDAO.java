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
import java.util.List;
import utils.DButil;

/**
 *
 * @author khoin
 */
public class tblUsersDAO implements Serializable {

    public tblUsersDTO validateLogin(String userId, String password)
            throws SQLException, ClassNotFoundException {
        tblUsersDTO result = null;

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DButil.getConnection();

            if (con != null) {
                String sql = "SELECT * FROM tblUsers WHERE userID = ? AND password = ?";

                pstm = con.prepareStatement(sql);

                pstm.setString(1, userId);
                pstm.setString(2, password);

                rs = pstm.executeQuery();

                if (rs.next()) {
                    String fullName = rs.getString("fullname");
                    String roleId = rs.getString("roleID");

                    result = new tblUsersDTO(userId, fullName, roleId, null);
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
        return result;
    }
}
