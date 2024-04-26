/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.campusconnect.data;

import com.campus.campusconnect.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author vivek
 */
public class AuthQuery {

    private final DBConnection c = new DBConnection();

    public String registerUser(com.campus.campusconnect.model.User user) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("insert into user(name, email, password,profile_pic,phone,university_name )values(?,?,?,?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(4, user.getProfilePic());
            ps.setString(5, String.valueOf(user.getPhone()));
            ps.setString(6, user.getUniversity());

            // Hash the password
            String hashedPassword = hashPassword(user.getPassword());
            ps.setString(3, user.getPassword());
            ps.execute();
            ps.close();

        } catch (Exception e) {
            return "E-mail already exists";
        }
        return "Registered";
    }

    public void loginUser(String email, String password) {
        try {
            String query = "select * from user where email like ? and password like ?";

            c.getDBConn();

            java.sql.PreparedStatement ps = c.getCon().prepareStatement(query);
            ps.setString(1, email);
            // Hash the password
            String hashedPassword = hashPassword(password);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("profile_pic"), rs.getInt("phone"), rs.getString("university_name"));
                com.campus.campusconnect.session.UserSession.saveUserSession(user);
                com.campus.campusconnect.App.setRoot("home");

//                return true;
            }

            rs.close();
            ps.close();

        } catch (Exception ex) {
//            return false;
            ex.printStackTrace();
        }
//        return false;
    }

    // Method to hash passwords using SHA-256
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
