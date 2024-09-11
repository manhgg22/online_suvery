/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author Norttie
 */
public class AccountDao extends DBContext {

    public User getUserByUserName(String username) {
        String sql = "Select * from Users where username =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs;
            rs = st.executeQuery();
            while (rs.next()) {
                User acc = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                return acc;
            }
        } catch (SQLException e) {
            System.out.println("Error cannot get account");
            return null;
        }
        return null;
    }

    public User getUserById(String id) {
        String sql = "Select * from Users where user_id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs;
            rs = st.executeQuery();
            while (rs.next()) {
                User acc = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                return acc;

            }
        } catch (SQLException e) {
            System.out.println("Error cannot get account");
            return null;
        }

        return null;
    }

    public List<User> getAllUser() {
        String sql = "Select * from Users";
        List<User> listU = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs;
            rs = st.executeQuery();
            while (rs.next()) {
                User acc = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                listU.add(acc);

            }
        } catch (SQLException e) {
            System.out.println("Error cannot get account");
        }

        return listU;
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, password);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to login: " + e.getMessage());
        }
        return null;
    }

    public void createUser(String username, String password, String displayname, String role) {
        String sql = "INSERT INTO Users (username,password,displayname,avatar,role) values (?,?,?,?,?) ";
        String avatar = "Avatar.png";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, displayname);
            st.setString(4, avatar);
            st.setString(5, role);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while trying to sign up: " + e.getMessage());
        }
    }

    public void updateUser(String id, String displayname, String avatar, String desc, String address) {
        String sql = "UPDATE Users SET displayname = ? , avatar = ? ,description = ?, address = ? WHERE user_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, displayname);
            st.setString(2, avatar);
            st.setString(3, desc);
            st.setString(4, address);
            st.setString(5, id);
            st.executeUpdate();
        } catch (SQLException e) {
        }

    }

    //delete acc 
  
    public void deleteAcc(String accid) {
        String sql = "delete from Users where user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, accid);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
  public void updateUserByAdmin(String id, String username, String password,String displayname, String desc, String address){
      String sql = "UPDATE Users SET username = ? ,password = ?,displayname = ? ,description = ?, [address] = ? WHERE user_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, displayname);
            st.setString(4, desc);
            st.setString(5, address);
            st.setString(6, id);
            st.executeUpdate();
        } catch (SQLException e) {
        }
  }

    public static void main(String[] args) {
        AccountDao dao = new AccountDao();
        dao.updateUserByAdmin("16", "bacvu120","Bacvu120","bacvux", "","");
    }
}
