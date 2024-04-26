
package com.campus.campusconnect.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection con;
    
    public void getDBConn() throws ClassNotFoundException {
        synchronized ("") {
            try {
                if(this.getCon() == null || this.getCon().isClosed()) {
                    try {
                        String url = "jdbc:mysql://localhost/campusconnectdb";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        setCon(DriverManager.getConnection(url, "root", "root"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static Connection getCon(){
        return con;
    }
    
    public static void setCon(Connection aCon) {
        con = aCon;
    }
    
    public static void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
