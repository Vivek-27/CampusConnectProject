module com.campus.campusconnect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.prefs;

    opens com.campus.campusconnect to javafx.fxml;
    exports com.campus.campusconnect;
    exports com.campus.campusconnect.data;
    exports com.campus.campusconnect.model;
    exports com.campus.campusconnect.session;
    exports com.campus.campusconnect.products;
    
}
