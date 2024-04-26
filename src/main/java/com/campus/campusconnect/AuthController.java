/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.campus.campusconnect;

import com.campus.campusconnect.data.DBConnection;
import com.campus.campusconnect.model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author vivek
 */
public class AuthController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxCampus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getUniversities();
    }

    @FXML
    public Hyperlink alerady_have_account;

    @FXML
    public Pane loginScreen;

    @FXML
    public Pane registerScreen;

    @FXML
    private void switchScene() throws IOException {

        if (loginScreen.isVisible() == true) {
            loginScreen.setVisible(false);
            registerScreen.setVisible(true);

        } else {
            registerScreen.setVisible(false);
            loginScreen.setVisible(true);
        }

    }

    @FXML
    public TextField filedName;

    @FXML
    public TextField filedEmail;

    @FXML
    public TextField filedPassword;

    @FXML
    public TextField filedEmailLogin;

    @FXML
    public TextField filedPasswordLogin;

    @FXML
    public Label invalidEP;

    @FXML
    public Label invalidEPLogin;

    public void getUniversities() {

        DBConnection c = new DBConnection();

        try {
            String query = "select name from university";

            c.getDBConn();

            java.sql.PreparedStatement ps = c.getCon().prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            List<String> universityNames = new ArrayList<>();

            while (rs.next()) {
                String universityName = rs.getString("name");
                universityNames.add(universityName);
            }

            comboBoxCampus.setItems(FXCollections.observableArrayList(universityNames));
            rs.close();
            ps.close();
            c.getCon().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void registerUser() {

        String email = filedEmail.getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        if (filedName.getText().equals("")) {
            invalidEP.setText("Please enter name");
        } else if (filedEmail.getText().equals("")) {
            invalidEP.setText("Please enter email");
        } else if (filedPassword.getText().equals("")) {
            invalidEP.setText("Please enter password");
        } else if (comboBoxCampus.getValue() == null || comboBoxCampus.getValue().isEmpty()) {
            invalidEP.setText("Please select your campus first");
        } else {
            if (matcher.matches()) {

                com.campus.campusconnect.model.User user = new com.campus.campusconnect.model.User(filedName.getText(), filedEmail.getText(), filedPassword.getText(), "profile pic", comboBoxCampus.getValue(), 23435);
                com.campus.campusconnect.data.AuthQuery query = new com.campus.campusconnect.data.AuthQuery();
                String r = query.registerUser(user);
                invalidEP.setText(r);
            } else {
                invalidEP.setText("*please enter a valid email");
            }
        }

    }

    @FXML
    private void removeInvalidErr() {
        invalidEP.setText("");
    }

    @FXML
    private void loginUser() {
        if (filedEmailLogin.getText().equals("")) {
            invalidEPLogin.setText("Please enter email");
        } else if (filedPasswordLogin.getText().equals("")) {
            invalidEPLogin.setText("Please enter password");
        }
        com.campus.campusconnect.data.AuthQuery query = new com.campus.campusconnect.data.AuthQuery();
        query.loginUser(filedEmailLogin.getText(), filedPasswordLogin.getText());
//        if (!correct) {
//            invalidEPLogin.setText("invalid eamil or password");
//        }
    }

}
