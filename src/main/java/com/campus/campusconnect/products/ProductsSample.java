/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.campusconnect.products;

import com.campus.campusconnect.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vivek
 */
public class ProductsSample {

    List<Product> productList = new ArrayList<>();

    public List<Product> getProductsSample() {
        return productList;
    }

    // Method to create a Product instance
    private static Product createProduct(String name, String category, String condition, double price, String seller, String description, byte[] productPic, String university_name, String pAddress) {
        Product product = new Product(name, category, condition, price, seller, description, productPic, university_name, pAddress);
        return product;
    }

    public void addProductsToList(Product p) {
        String name = p.getName();
        String category = p.getCategory();
        double price = p.getPrice();
        String description = p.getDescription();
        String condition = p.getCondition();
        String seller = p.getSeller();
        byte[] productPic = p.getProductPic();
        String university_name = p.getUniversity_name();
        String pAddress = p.getpAddress();
        productList.add(createProduct(name, category, condition, price, seller, description, productPic, university_name, pAddress));
        
    }

}
