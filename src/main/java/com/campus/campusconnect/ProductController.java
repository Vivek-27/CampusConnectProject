/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.campus.campusconnect;

import com.campus.campusconnect.model.Product;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author vivek
 */
public class ProductController implements Initializable {

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private ImageView image;

    @FXML
    public Group productDetailsBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Product p) {
        
        productName.setText(p.getName());
        productPrice.setText(String.valueOf(p.getPrice()));
        byte[] imageData = p.getProductPic();
        if (imageData != null) {
            Image img = new Image(new ByteArrayInputStream(imageData));
            image.setImage(img);
        }

    }
    

}
