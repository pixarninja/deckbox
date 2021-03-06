package beans;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoreCardInfo implements Serializable{
    
    private String id;
    private String game;
    private String name;
    private String set_name;
    private String set_id;
    private String rarity;
    private String mc;
    private float cmc;
    private String colors;
    private String type;
    private String text;
    private String flavor;
    private String power;
    private String toughness;
    private String loyalty;
    private String revMc;
    private String revColors;
    private String revName;
    private String revType;
    private String revText;
    private String revFlavor;
    private String revPower;
    private String revToughness;
    private String revLoyalty;
    private String artist;
    private String year;
    private int multiverse;
    private String legalities;
    private String kingdom;
    private String usd;
    private Boolean digital;
    private java.util.Date dateViewed;
    private String frontURI;
    private String backURI;
    private int scryId;
    
    public MoreCardInfo() {}
    
    public MoreCardInfo(String id, String game, String name, String set_name, String set_id, String rarity, String mc, float cmc, String colors, String type, String text, String flavor, String power, String toughness, String loyalty, String revMc, String revColors, String revName, String revType, String revText, String revFlavor, String revPower, String revToughness, String revLoyalty, String artist, String year, int multiverse, String legalities, String kingdom, String usd, Boolean digital, java.util.Date dateViewed, String frontURI, String backURI, int scryId) {
        this.id = id;
        this.game = game;
        this.name = name;
        this.set_name = set_name;
        this.set_id = set_id;
        this.rarity = rarity;
        this.mc = mc;
        this.cmc = cmc;
        this.colors = colors;
        this.type = type;
        this.text = text;
        this.flavor = flavor;
        this.power = power;
        this.toughness = toughness;
        this.loyalty = loyalty;
        this.revMc = revMc;
        this.revColors = revColors;
        this.revName = revName;
        this.revType = revType;
        this.revText = revText;
        this.revFlavor = revFlavor;
        this.revPower = revPower;
        this.revToughness = revToughness;
        this.revLoyalty = revLoyalty;
        this.artist = artist;
        this.year = year;
        this.multiverse = multiverse;
        this.legalities = legalities;
        this.kingdom = kingdom;
        this.usd = usd;
        this.digital = digital;
        this.dateViewed = dateViewed;
        this.frontURI = frontURI;
        this.backURI = backURI;
        this.scryId = scryId;
    }
    
    public static MoreCardInfo getCardById(String id) {
        MoreCardInfo cardInfo = null;
        
        try {
            String driver = secure.DBConnection.driver;
            Class.forName(driver);
            String dbURL = secure.DBConnection.dbURL;
            String username = secure.DBConnection.username;
            String password = secure.DBConnection.password;
            Connection connection = DriverManager.getConnection(dbURL, username, password);
        
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table1 + "` WHERE id='" + id + "'");
            if(rs.next()) {
                String game = rs.getString("game");
                String name = rs.getString("name");
                String set_name = rs.getString("set_name");
                String set_id = rs.getString("set_id");
                String rarity = rs.getString("rarity");
                String mc = rs.getString("mc");
                float cmc = rs.getFloat("cmc");
                String colors = rs.getString("colors");
                String type = rs.getString("type");
                String text = rs.getString("text");
                String flavor = rs.getString("flavor");
                String power = rs.getString("power");
                String toughness = rs.getString("toughness");
                String loyalty = rs.getString("loyalty");
                String revMc = rs.getString("rev_mc");
                String revColors = rs.getString("rev_colors");
                String revName = rs.getString("rev_name");
                String revType = rs.getString("rev_type");
                String revText = rs.getString("rev_text");
                String revFlavor = rs.getString("rev_flavor");
                String revPower = rs.getString("rev_power");
                String revToughness = rs.getString("rev_toughness");
                String revLoyalty = rs.getString("rev_loyalty");
                String artist = rs.getString("artist");
                String year = rs.getString("year");
                int multiverse = rs.getInt("multiverse");
                String legalities = rs.getString("legalities");
                String kingdom = rs.getString("kingdom");
                String usd = rs.getString("usd");
                Boolean digital = false;
                if(rs.getInt("digital") == 1) {
                    digital = true;
                }
                java.util.Date dateViewed = rs.getDate("viewed");
                String frontURI = rs.getString("frontURI");
                String backURI = rs.getString("backURI");
                int scryId = rs.getInt("scryId");
                
                cardInfo = new MoreCardInfo(id, game, name, set_name, set_id, rarity, mc, cmc, colors, type, text, flavor, power, toughness, loyalty, revMc, revColors, revName, revType, revText, revFlavor, revPower, revToughness, revLoyalty, artist, year, multiverse, legalities, kingdom, usd, digital, dateViewed, frontURI, backURI, scryId);
            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CardInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CardInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cardInfo;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getGame() {
        return game;
    }
    
    public String getSetName() {
        return set_name;
    }
    
    public String getSetId() {
        return set_id;
    }
    
    public String getRarity() {
        return rarity;
    }
    
    public String getManaCost() {
        return mc;
    }
    
    public float getConvertedManaCost() {
        return cmc;
    }
    
    public String getColors() {
        return colors;
    }
    
    public String getType() {
        return type;
    }
    
    public String getText() {
        return text;
    }
    
    public String getFlavor() {
        return flavor;
    }
    
    public String getPower() {
        return power;
    }
    
    public String getToughness() {
        return toughness;
    }
    
    public String getLoyalty() {
        return loyalty;
    }
    
    public String getRevManaCost() {
        return revMc;
    }
    
    public String getRevColors() {
        return revColors;
    }
    
    public String getRevName() {
        return revName;
    }
    
    public String getRevType() {
        return revType;
    }
    
    public String getRevText() {
        return revText;
    }
    
    public String getRevFlavor() {
        return revFlavor;
    }
    
    public String getRevPower() {
        return revPower;
    }
    
    public String getRevToughness() {
        return revToughness;
    }
    
    public String getRevLoyalty() {
        return revLoyalty;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public String getYear() {
        return year;
    }
    
    public int getMultiverse() {
        return multiverse;
    }
    
    public String getLegalities() {
        return legalities;
    }
    
    public String getKingdom() {
        return kingdom;
    }
    
    public String getUsd() {
        return usd;
    }
    
    public Boolean getDigital() {
        return digital;
    }
    
    public java.util.Date getDateViewed() {
        return dateViewed;
    }
    
    public String[] getImageURLs() {
        HashMap<Object,Object> frontMap;
        HashMap<Object,Object> backMap;
        try {
            frontMap = resource.ScryfallScraper.TranslateImageURI(frontURI);
        } catch(Exception ex) {
            frontMap = null;
        }
        try {
            backMap = resource.ScryfallScraper.TranslateImageURI(backURI);
        } catch(Exception ex) {
            backMap = null;
        }
        return resource.ScryfallScraper.ParseImageURIs(frontMap, backMap);
    }
    
    public String[] scrapeImageURLs() {
        Object[] scrapedImageURIs = {null, null};
        try {
            scrapedImageURIs = resource.ScryfallScraper.ScrapeImageURIs(scryId, set_id);
            
            /* update database if necessary */
            if(scrapedImageURIs[0] != null && !scrapedImageURIs[0].toString().equals("") && !scrapedImageURIs[0].toString().equals(frontURI)) {
                String driver = secure.DBConnection.driver;
                Class.forName(driver);
                String dbURL = secure.DBConnection.dbURL;
                String user = secure.DBConnection.username;
                String pass = secure.DBConnection.password;
                Connection connection = DriverManager.getConnection(dbURL, user, pass);

                String query = "UPDATE `" + secure.DBStructure.table1 + "` SET frontURI = ? WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, scrapedImageURIs[0].toString());
                ps.setString(2, id);
                ps.executeUpdate();
                ps.close();
            }
            if(scrapedImageURIs[1] != null && !scrapedImageURIs[1].toString().equals("") && !scrapedImageURIs[1].toString().equals(backURI)) {
                String driver = secure.DBConnection.driver;
                Class.forName(driver);
                String dbURL = secure.DBConnection.dbURL;
                String user = secure.DBConnection.username;
                String pass = secure.DBConnection.password;
                Connection connection = DriverManager.getConnection(dbURL, user, pass);

                String query = "UPDATE `" + secure.DBStructure.table1 + "` SET backURI = ? WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, scrapedImageURIs[1].toString());
                ps.setString(2, id);
                ps.executeUpdate();
                ps.close();
            }
            
            String pair[] = {null, null};
            
            @SuppressWarnings("unchecked")
            HashMap<Object,Object> frontURI = null;
            @SuppressWarnings("unchecked")
            HashMap<Object,Object> backURI = null;
            
            if(scrapedImageURIs[0] != null) {
                frontURI = resource.ScryfallScraper.TranslateImageURI(scrapedImageURIs[0].toString());
                String images[] = resource.ScryfallScraper.ParseImageURIs(frontURI, null);
                pair[0] = images[0];
            }
            else {
                pair[0] = this.frontURI;
            }
            if(scrapedImageURIs[1] != null) {
                backURI = resource.ScryfallScraper.TranslateImageURI(scrapedImageURIs[1].toString());
                String images[] = resource.ScryfallScraper.ParseImageURIs(null, backURI);
                pair[1] = images[1];
            }
            else {
                pair[1] = this.backURI;
            }
            
            return pair;
        } catch(Exception ex) {
            Logger.getLogger(CardInfo.class.getName()).log(Level.SEVERE, null, ex);
            String pair[] = {null, null};
            return pair;
        }
    }
    
}
