/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.campus.campusconnect;

import com.campus.campusconnect.model.University;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author vivek
 */
public class UniversityDetailsController implements Initializable {

    @FXML
    private Label universityAddress;

    @FXML
    private Label universityCity;

    @FXML
    private Label universityContact;

    @FXML
    private Label universityCountry;

    @FXML
    private ImageView universityImage;

    @FXML
    private Label universityName;

    @FXML
    private Label universityPOST;

    @FXML
    private Label universityState;

    @FXML
    private Label universityWeb;

    com.campus.campusconnect.session.UserSession s = new com.campus.campusconnect.session.UserSession();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setData(University u) {
        universityName.setText(u.getName());
        universityAddress.setText(u.getAddress());
        universityCity.setText(u.getCity());
        universityState.setText(u.getState());
        universityCountry.setText(u.getCountry());
        universityPOST.setText(u.getPostalCode());
        universityContact.setText(String.valueOf(u.getPhone()));
        universityWeb.setText(u.getWebsite());

        String imageUrl = u.getLogo();
        if (imageUrl != null && !imageUrl.equals("")) {
            Image image = new Image(imageUrl);
            universityImage.setImage(image);
        }
 
    }

}
