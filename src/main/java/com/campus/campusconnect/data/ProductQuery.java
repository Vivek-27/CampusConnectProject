/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.campusconnect.data;

import com.campus.campusconnect.model.Product;
import com.campus.campusconnect.products.ProductsSample;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vivek
 */
public class ProductQuery {

    private final DBConnection c = new DBConnection();

    public boolean addProductToDB(com.campus.campusconnect.model.Product product) {

        int rowsAffected = 0;
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("insert into product(name, category,price, description, pCond"
                    + "ition,productImg, seller_email, university_name, pAddress)values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setString(3, String.valueOf(product.getPrice()));
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getCondition());
            // Set the image data as a byte array
            ps.setBytes(6, product.getProductPic());
            ps.setString(7, product.getSeller());
            ps.setString(8, product.getUniversity_name());
            ps.setString(9, product.getpAddress());

            rowsAffected = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

    public List<Product> getAllProducts() {
        ProductsSample productsSample = new ProductsSample();

        try {
            c.getDBConn();
            PreparedStatement ps = c.getCon().prepareStatement("SELECT * FROM product");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String condition = rs.getString("pCondition");
                String seller = rs.getString("seller_email");
                byte[] productPic = rs.getBytes("productImg");
                String university_name = rs.getString("university_name");
                String pAddress = rs.getString("pAddress");
                Product product = new Product(name, category, condition, price, seller, description, productPic, university_name, pAddress);
                productsSample.addProductsToList(product);

            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productsSample.getProductsSample();
    }
}
