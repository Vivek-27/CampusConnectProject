/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.campusconnect.model;

/**
 *
 * @author vivek
 */
public class User {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String profilePic;
    private int phone;
    private String university;
    
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    public User( String name, String email, String password, String profilePic,  String univeristy, int phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.phone = phone;
        this.university = univeristy;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public User( String name, String email, String profilePic, int phone, String univeristy) {
        this.name = name;
        this.email = email;
        this.profilePic = profilePic;
        this.university = univeristy;
        this.phone = phone;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the profilePic
     */
    public String getProfilePic() {
        return profilePic;
    }

    /**
     * @param profilePic the profilePic to set
     */
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    /**
     * @return the phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * @return the university
     */
    public String getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(String university) {
        this.university = university;
    }


    
}
