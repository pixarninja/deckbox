package beans;

import java.io.Serializable;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserInfo implements Serializable{
    
    private static LinkedHashMap usersById = new LinkedHashMap();
    private static LinkedHashMap usersByNum = new LinkedHashMap();
    private static LinkedHashMap usersByNumAlpha = new LinkedHashMap();
    private Connection connection;
    
    private String username;
    private String password;
    private String picture;
    private String email;
    private String name;
    private java.util.Date dateJoined;
    private String bio;
    
    public UserInfo() {
        try {
            usersById = new LinkedHashMap();
            usersByNum = new LinkedHashMap();
            usersByNumAlpha = new LinkedHashMap();
            String driver = secure.DBConnection.driver;
            Class.forName(driver);
            String dbURL = secure.DBConnection.dbURL;
            String username = secure.DBConnection.username;
            String password = secure.DBConnection.password;
            connection = DriverManager.getConnection(dbURL, username, password);
        
            Statement statement = connection.createStatement();

            int num = 1;
            ResultSet rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table16 + "` ORDER BY joined DESC");
            while(rs.next()) {
                String user = rs.getString("username");
                String pass = rs.getString("password");
                String picture = rs.getString("picture");
                if(picture == null || picture.equals("") || picture.equals("null")) {
                    picture = "images/blank_user.jpg";
                }
                String email = rs.getString("email");
                String name = rs.getString("name");
                java.util.Date dateJoined = rs.getDate("joined");
                String bio = rs.getString("bio");
                
                usersById.put(user, new UserInfo(user, pass, picture, email, name, dateJoined, bio));
                usersByNum.put(num, new UserInfo(user, pass, picture, email, name, dateJoined, bio));
                num++;
            }
            rs.close();
            
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table16 + "` ORDER BY name ASC");
            num = 1;
            while(rs.next()) {
                String user = rs.getString("username");
                String pass = rs.getString("password");
                String picture = rs.getString("picture");
                if(picture == null || picture.equals("") || picture.equals("null")) {
                    picture = "images/blank_user.jpg";
                }
                String email = rs.getString("email");
                String name = rs.getString("name");
                java.util.Date dateJoined = rs.getDate("joined");
                String bio = rs.getString("bio");
                
                usersByNumAlpha.put(num, new UserInfo(user, pass, picture, email, name, dateJoined, bio));
                num++;
            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public UserInfo(String username, String password, String picture, String email, String name, java.util.Date dateJoined, String bio) {
        this.username = username;
        this.password = password;
        this.picture = picture;
        this.email = email;
        this.name = name;
        this.dateJoined = dateJoined;
        this.bio = bio;
    }
    
    public static UserInfo getUser(String username) {
        return ((UserInfo)usersById.get(username));
    }
    
    public static UserInfo getUserByNum(int num) {
        return ((UserInfo)usersByNum.get(num));
    }
    
    public static UserInfo getUserByNumAlpha(int num) {
        return ((UserInfo)usersByNumAlpha.get(num));
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getPicture() {
        return picture;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return name;
    }
    
    public java.util.Date getDateJoined() {
        return dateJoined;
    }
    
    public String getBio() {
        return bio;
    }
    
}
