/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.campus.campusconnect;

import com.campus.campusconnect.data.DBConnection;
import com.campus.campusconnect.model.Product;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vivek
 */
public class ProductDetailsController implements Initializable {

    @FXML
    private AnchorPane addProductScreen;

    @FXML
    private Button conatctSellerBtn;

    @FXML
    private Text productAddress;

    @FXML
    private Label productCategory;

    @FXML
    private Label productCondition;

    @FXML
    private Text productDesc;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Label sellerEmail;

    @FXML
    private ImageView sellerImage;

    @FXML
    public Button closeButton;

    @FXML
    private Label sellerName;

    @FXML
    private Label sellerNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            getSellerDetails();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public interface ProductDetailsListener {

        void onCloseButtonClicked();
    }

    private ProductDetailsListener listener;

    public void setProductDetailsListener(ProductDetailsListener listener) {
        this.listener = listener;
    }

    @FXML
    private void handleCloseButton() {
        if (listener != null) {
            listener.onCloseButtonClicked();
        }
    }

    public void initializeData(Product p) {

//        System.out.println(product.getName());
        productName.setText(p.getName());
        productPrice.setText(String.valueOf(p.getPrice()));
        byte[] imageData = p.getProductPic();
        if (imageData != null) {
            Image img = new Image(new ByteArrayInputStream(imageData));
            productImage.setImage(img);
        }

        productAddress.setText(p.getpAddress());
        productCategory.setText(p.getCategory());
        productCondition.setText(p.getCondition());
        productDesc.setText(p.getDescription());
        sellerEmail.setText(p.getSeller());

    }

    public void getSellerDetails() throws SQLException {
        DBConnection c = new DBConnection();

        c.getCon();

        java.sql.PreparedStatement ps = c.getCon().prepareStatement("SELECT name, email, profile_pic, phone FROM user WHERE email = '" + sellerEmail.getText() + "'");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            byte[] sellerimage = rs.getBytes("profile_pic");
            if (sellerimage != null) {
                Image img = new Image(new ByteArrayInputStream(sellerimage));
                sellerImage.setImage(img);
            }
            sellerName.setText(rs.getString("name"));
            sellerNumber.setText(rs.getString("phone"));
        }
        rs.close();
        ps.close();
    }



}
