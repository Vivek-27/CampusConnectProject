/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.campusconnect.session;

import com.campus.campusconnect.model.User;
import java.util.prefs.Preferences;

/**
 *
 * @author vivek
 */
public class UserSession {

    public static boolean isLoggedIn() {
        Preferences preferences = Preferences.userRoot().userNodeForPackage(UserSession.class);
        String username = preferences.get("username", null);
//        String authToken = preferences.get("username", null);
//        return username!= null && authToken !=null;
        return username != null;
    }

    public static void saveUserSession(User user) {
        Preferences preferences = Preferences.userNodeForPackage(UserSession.class);
        preferences.put("username", user.getName());
        preferences.put("email", user.getEmail());
        preferences.put("profilePic", user.getProfilePic());
        preferences.put("phone", String.valueOf(user.getPhone()));
        preferences.put("university", user.getUniversity());
    }

    public static void clearUserSession() {
        Preferences preferences = Preferences.userNodeForPackage(UserSession.class);
        preferences.remove("username");
        preferences.remove("email");
        preferences.remove("profilePic");
        preferences.remove("phone");
        preferences.remove("university");
    }

    public static String getSessionUsername() {
        Preferences preferences = Preferences.userNodeForPackage(UserSession.class);
        return preferences.get("username", null);
    }

    public static String getSessionEmail() {
        Preferences preferences = Preferences.userNodeForPackage(UserSession.class);
        return preferences.get("email", null);
    }
    
    public static String getSessionUserUni() {
        Preferences preferences = Preferences.userNodeForPackage(UserSession.class);
        return preferences.get("university", null);
    }
    
    public static String getSessionUserProfilePic() {
        Preferences preferences = Preferences.userNodeForPackage(UserSession.class);
        return preferences.get("profilePic", null);
    }
    public static String getSessionUserPhone() {
        Preferences preferences = Preferences.userNodeForPackage(UserSession.class);
        return preferences.get("phone", null);
    }
    
}
