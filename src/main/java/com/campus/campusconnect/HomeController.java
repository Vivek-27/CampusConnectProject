/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.campus.campusconnect;

import com.campus.campusconnect.data.DBConnection;
import com.campus.campusconnect.data.ProductQuery;
import com.campus.campusconnect.model.Product;
import com.campus.campusconnect.model.University;
import com.campus.campusconnect.session.UserSession;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vivek
 */
public class HomeController implements Initializable, ProductDetailsController.ProductDetailsListener {

    @FXML
    private GridPane grid;

    @FXML
    private Group addBtn;

    @FXML
    private Slider priceSlider;

    @FXML
    private Label priceLabel;

    @FXML
    public AnchorPane productDetailsAnchorPane;

    @FXML
    private ImageView uniImage;

    @FXML
    private Label uniName;

    @FXML
    private HBox universityDetailBtn;

    @FXML
    private AnchorPane textbooksAndEducationalMaterials;
    @FXML
    private AnchorPane electronics;
    @FXML
    private AnchorPane furniture;
    @FXML
    private AnchorPane clothingAndAccessories;
    @FXML
    private AnchorPane appliances;
    @FXML
    private AnchorPane sportsAndFitnessEquipment;
    @FXML
    private AnchorPane booksAndStationery;
    @FXML
    private AnchorPane musicalInstruments;
    @FXML
    private AnchorPane kitchenwareAndCookware;
    @FXML
    private AnchorPane miscellaneous;

    @FXML
    private Label categoryNameLabel;

    @FXML
    private Label usernameHome;

    @FXML
    private AnchorPane hammBurgerMenu;

    com.campus.campusconnect.session.UserSession s = new com.campus.campusconnect.session.UserSession();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gettingAllProducts();

        priceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the label text with the new value
            priceLabel.setText(String.format("₹%.2f", newValue.doubleValue()));
        });

        // Add event handlers for each category
        textbooksAndEducationalMaterials.setOnMouseClicked(event -> handleCategoryClick("Textbooks and Educational Materials"));
        electronics.setOnMouseClicked(event -> handleCategoryClick("Electronics"));
        furniture.setOnMouseClicked(event -> handleCategoryClick("Furniture"));
        clothingAndAccessories.setOnMouseClicked(event -> handleCategoryClick("Clothing and Accessories"));
        appliances.setOnMouseClicked(event -> handleCategoryClick("Appliances"));
        sportsAndFitnessEquipment.setOnMouseClicked(event -> handleCategoryClick("Sports and Fitness Equipment"));
        booksAndStationery.setOnMouseClicked(event -> handleCategoryClick("Books and Stationery"));
        musicalInstruments.setOnMouseClicked(event -> handleCategoryClick("Musical Instruments"));
        kitchenwareAndCookware.setOnMouseClicked(event -> handleCategoryClick("Kitchenware and Cookware"));
        miscellaneous.setOnMouseClicked(event -> handleCategoryClick("Miscellaneous"));

        if (s.getSessionUserUni() != null || s.getSessionUsername() != null) {
            uniName.setText(s.getSessionUserUni());
            usernameHome.setText(s.getSessionUsername());
        }
        
        try {
            getUniDetails();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }        showUniDetailsHome();


    }

    ProductQuery pq = new ProductQuery();
    List<Product> productList = new ArrayList<>(pq.getAllProducts());

    public void gettingAllProducts() {
        //Product Sample
        int col = 0;
        int row = 0;

        if (productList.size() > 0) {
            grid.getChildren().clear();
            categoryNameLabel.setText("");
            for (int i = 0; i < productList.size(); i++) {
                final int index = i;
                try {
                    // TODO

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product.fxml"));

                    AnchorPane ap = fxmlLoader.load();

                    ProductController pc = fxmlLoader.getController();
                    pc.setData(productList.get(index));

                    pc.productDetailsBtn.getChildren().forEach(child -> {
                        if (child instanceof Node) {
                            ((Node) child).setOnMouseClicked(event -> {
                                productDetailsAnchorPane.setVisible(true);
                                productDetailsAnchorPane.setDisable(false);

                                showProductDetailsHome(productList.get(index));
                            });
                        }
                    });

                    grid.add(ap, col, row);
                    // Move to the next column
                    col++;
                    if (col >= 3) {
                        // If we reach the end of a row (3 columns), reset the column index
                        // and move to the next row
                        col = 0;
                        row++;
                    }

                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void filterProductsByCategory(String category) {

        List<Product> filteredProducts = productList.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());

        grid.getChildren().clear();

        int col = 0;
        int row = 0;
        for (Product product : filteredProducts) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product.fxml"));
                AnchorPane ap = fxmlLoader.load();
                ProductController pc = fxmlLoader.getController();
                pc.setData(product);

                // Add click event to show product details
                pc.productDetailsBtn.getChildren().forEach(child -> {
                    if (child instanceof Node) {
                        ((Node) child).setOnMouseClicked(event -> {
                            productDetailsAnchorPane.setVisible(true);
                            productDetailsAnchorPane.setDisable(false);
                            showProductDetailsHome(product);
                        });
                    }
                });

                grid.add(ap, col, row);
                // Move to the next column
                col++;

                if (col >= 3) {
                    // If we reach the end of a row (3 columns), reset the column index
                    // and move to the next row
                    col = 0;
                    row++;
                }
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleCategoryClick(String categoryName) {
        categoryNameLabel.setText(categoryName);
        filterProductsByCategory(categoryName);
    }

    @FXML
    private void toggleUniversityDetails() {

        universityAnchorPane.setVisible(!universityAnchorPane.isVisible());
        universityAnchorPane.setDisable(!universityAnchorPane.isDisable());

        // Clip the anchor pane to hide overflow
        if (!universityAnchorPane.isVisible()) {
            universityAnchorPane.setClip(null);
        } else {
            Rectangle clip = new Rectangle(universityAnchorPane.getWidth(), universityAnchorPane.getHeight());
            universityAnchorPane.setClip(clip);
        }
    }

    University u = new University();

    public void getUniDetails() throws SQLException, IOException {
        DBConnection c = new DBConnection();

        c.getCon();

        java.sql.PreparedStatement ps = c.getCon().prepareStatement("SELECT * FROM university WHERE name = '" + s.getSessionUserUni() + "'");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            if (!rs.getString("logo").equals("")) {
                URL url = new URL(rs.getString("logo"));

                // Open a connection to the URL
                InputStream inputStream = url.openStream();

                Image img = new Image(inputStream);
                uniImage.setImage(img);
            }

            u.setName(rs.getString("name"));
            u.setAddress(rs.getString("address"));
            u.setCity(rs.getString("city"));
            u.setState(rs.getString("state"));
            u.setCountry(rs.getString("country"));
            u.setPostalCode(rs.getString("postal_code"));
            u.setPhone(rs.getString("phone"));
            u.setWebsite(rs.getString("website"));
            u.setLogo(rs.getString("logo"));
        }
        rs.close();
        ps.close();
    }

    @FXML
    private AnchorPane universityAnchorPane;

    public void showUniDetailsHome() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("universityDetails.fxml"));
            AnchorPane uniDetailsPane = fxmlLoader.load();
            UniversityDetailsController udc = fxmlLoader.getController();

            if (udc != null) {
                udc.setData(u);
                universityAnchorPane.getChildren().setAll(uniDetailsPane);
            } else {
                System.out.println("ProductDetailsController is null");
            }

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showProductDetailsHome(Product selectedProduct) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productDetails.fxml"));
            AnchorPane productDetailsPane = fxmlLoader.load();
            ProductDetailsController pdc = fxmlLoader.getController();

            if (pdc != null) {

                pdc.setProductDetailsListener(this); // Pass HomeController reference
                // Call initializeData method with selectedProduct
                pdc.initializeData(selectedProduct);

                // Set the productDetailsPane
                productDetailsAnchorPane.getChildren().setAll(productDetailsPane);
            } else {
                System.out.println("ProductDetailsController is null");
            }
            productDetailsAnchorPane.getChildren().setAll(productDetailsPane);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void getCategoryProducts() {
//        String choice = chooseCategory.getValue();

        ProductQuery pc = new ProductQuery();
    }

    @FXML
    private ComboBox<String> comboBoxCategory;

    @FXML
    private ComboBox<String> comboBoxCondition;

    @FXML
    public void addItemToComboBox() {
        comboBoxCategory.setItems(FXCollections.observableArrayList("Textbooks and Educational Materials", "Electronics", "Furniture", "Cl"
                + "othing and Accessories", "Appliances", "Sports and Fitness Equipment", "Books and Stat"
                + "ionery", "Musical Instruments", "Kitchenware and Cookware", "Miscellaneous"));

        comboBoxCondition.setItems(FXCollections.observableArrayList("New", "Like New", "Good", "Fair", "Poor"));

    }

    @FXML
    private Label selectedFileLabel;

    @FXML
    private ImageView productImage;

    private byte[] imageData2;

    @FXML
    public void selectFile() throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Product Image");

        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        FileChooser.ExtensionFilter jpegFilter = new FileChooser.ExtensionFilter("JPEG files (*.jpeg, *.jpg)", "*.jpeg", "*.jpg");
        fileChooser.getExtensionFilters().addAll(pngFilter, jpegFilter);

        Stage stage = (Stage) selectedFileLabel.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            FileInputStream fis = new FileInputStream(selectedFile);
            byte[] imageData = new byte[(int) selectedFile.length()];
            fis.read(imageData);
            fis.close();
            imageData2 = imageData;
            selectedFileLabel.setText("Selected file: " + selectedFile.getAbsolutePath());

            Image image = new Image(new ByteArrayInputStream(imageData));
            productImage.setImage(image);

        } else {
            selectedFileLabel.setText("No file selected.");
        }
    }

    @FXML
    public AnchorPane addProductScreen;

    @FXML
    private TextField sellerEmail;

    @FXML
    private TextField sellerUniversity;

    @FXML
    public void showAddProduct() {
        addItemToComboBox();

        sellerEmail.setText(s.getSessionEmail());
        sellerUniversity.setText(s.getSessionUserUni());

        addProductScreen.setVisible(true);
        addProductScreen.setDisable(false);
        priceSlider.adjustValue(5);
    }

    @FXML
    public void hideAddProduct(ActionEvent event) {
        addProductScreen.setVisible(false);
        addProductScreen.setDisable(true);

    }

    @FXML
    private TextField productName;

    @FXML
    private Label sampleProductName;

    @FXML
    private Label sampleProductPrice;

    @FXML
    private Label errorLabel;

    @FXML
    private TextArea productDesc;

    @FXML
    private TextArea productAddress;

    @FXML
    public void setSampleDetailsName() {
        sampleProductName.setText(productName.getText());
    }

    @FXML
    public void setSampleDetailsPrice() {
        sampleProductPrice.setText(priceLabel.getText());
    }

    @FXML
    private Button submitProductBtn;

    @FXML
    public void addProduct() {
        UserSession us = new UserSession();
        String name = productName.getText();
        String category = comboBoxCategory.getValue();
        double price = priceSlider.getValue();
        String description = productDesc.getText();
        String condition = comboBoxCondition.getValue();
        String seller = us.getSessionEmail();
        String location = productAddress.getText();
        byte[] productPic = imageData2;
        String university_name = us.getSessionUserUni();
        String pAddress = productAddress.getText();

        com.campus.campusconnect.data.ProductQuery pc = new com.campus.campusconnect.data.ProductQuery();
        Product product = new Product(name, category, condition, price, seller, description, productPic, university_name, pAddress);

        if (name.equals("")) {
            errorLabel.setText("Enter item name please");
        } else if (category == null) {
            errorLabel.setText("Select item category please");
        } else if (description.equals("")) {
            errorLabel.setText("Enter item description please");
        } else if (condition == null) {
            errorLabel.setText("Select item condition please");
        } else if (pAddress.equals("")) {
            errorLabel.setText("Enter item pick-up address please");
        } else if (productPic == null) {
            errorLabel.setText("Select item image please");
        } else {
            boolean success = pc.addProductToDB(product);
            if (success) {

                errorLabel.setText("Product successfully listed on application");
                submitProductBtn.setDisable(true);
            } else {
                errorLabel.setText("Error while sending product to db");
            }
        }

    }

    @Override
    public void onCloseButtonClicked() {
        productDetailsAnchorPane.getChildren().clear();
    }

    @FXML
    public void logout() {
        try {
            s.clearUserSession();
            com.campus.campusconnect.App.setRoot("auth");
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleHammBurger() {
        hammBurgerMenu.setVisible(!hammBurgerMenu.isVisible());
        hammBurgerMenu.setDisable(!hammBurgerMenu.isDisable());

    }
}
