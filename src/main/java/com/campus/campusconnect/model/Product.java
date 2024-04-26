/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.campusconnect.model;

/**
 *
 * @author vivek
 */
public class Product {



    private Integer id;
    private String name;
    private String category;
    private double price;
    private String description;
    private String condition;
    private String seller;
    private byte[] productPic;
    private String university_name;
    private String pAddress;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {

    }

    public Product(String name, String category, String condition, double price, String seller, String description, byte[] productPic, String university_name, String pAddress) {

        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.condition = condition;
        this.seller = seller;
        this.productPic = productPic;
        this.university_name = university_name;
        this.pAddress = pAddress;
    }
    
    
    
        /**
     * @return the productPic
     */
    public byte[] getProductPic() {
        return productPic;
    }

    /**
     * @param productPic the productPic to set
     */
    public void setProductPic(byte[] productPic) {
        this.productPic = productPic;
    }

    /**
     * @return the pAddress
     */
    public String getpAddress() {
        return pAddress;
    }

    /**
     * @param pAddress the pAddress to set
     */
    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    /**
     * @return the university_name
     */
    public String getUniversity_name() {
        return university_name;
    }

    /**
     * @param university_name the university_name to set
     */
    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }



    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return the seller
     */
    public String getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(String seller) {
        this.seller = seller;
    }


    /**
     * @return the location
     */
}
