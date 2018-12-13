package teljesenmindegy;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    
    private final String FDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private final String URL = "jdbc:derby:teljesenmindegyDB;create=true";
    private final String USERNAME = "";
    private final String PASSWORD = "";
    
        Connection conn = null;
        Statement createStatement = null;
        DatabaseMetaData dbmd = null;
        
    public DB(){
        
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Sikeresen kapcsolódtál az adatbázishoz!");
        } catch (SQLException ex) {
            System.out.println("Nem sikerült kapcsolódni az adatbázishoz!");
            System.out.println(ex);
        }
        
        if(conn != null) {
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Nem sikerült létrehozni a Statement-et!");
                System.out.println(ex);
            }
        }
        
        try {
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Nem sikerült lekérni a metadatokat!");
            System.out.println(ex);
        }
        
        try {
            ResultSet rs1 = dbmd.getTables(null, "APP", "USERS", null);
            ResultSet rs2 = dbmd.getTables(null, "APP", "TERMEK", null);
            if(!rs1.next()){
                String sql = "create table users(id INT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(20), username varchar(20), password varchar(20), szig varchar(20), email varchar(20), rank varchar(20))";
                createStatement.execute(sql);
            }
            if(!rs2.next()){
                String sql = "create table termek(id INT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(20), vonalkod varchar(20), egysegar varchar(20), mennyiseg varchar(20), megjegyzes varchar(20))";
                createStatement.execute(sql);
            }
        } catch (SQLException ex) {
            System.out.println("Nem sikerült létrehozni a táblákat!");
            System.out.println(ex);
        }
    }
    
    public ArrayList<User> getAllUsers(){
        String sql = "select * from users";
        ArrayList<User> users = null;
        try{
            ResultSet rs = createStatement.executeQuery(sql);
            users = new ArrayList<>();
            while(rs.next()){
                User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("szig"), rs.getString("email"), rs.getString("rank"));
                users.add(u);
            }
        }catch(SQLException ex){
            System.out.println("Nem sikerült lekérni az adatokat!");
            System.out.println(ex);
            
        }
        
        return users;
    }
    
    public ArrayList<Termek> getAllProduct(){
        String sql = "select * from termek";
        ArrayList<Termek> products = null;
        try{
            ResultSet rs = createStatement.executeQuery(sql);
            products = new ArrayList<>();
            while(rs.next()){
                Termek t = new Termek(rs.getInt("id"), rs.getString("name"), rs.getString("vonalkod"), rs.getString("egysegar"), rs.getString("mennyiseg"), rs.getString("megjegyzes"));
                products.add(t);
            }
        }catch(SQLException ex){
            System.out.println("Nem sikerült lekérni az adatokat!");
            System.out.println(ex);
            
        }
        
        return products;
    }
    
    public void addUser(User u){
        
        try {
            String sql = "insert into users (name,username,password,szig,email,rank) values (?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, u.getNev());
            pstm.setString(2, u.getUsername());
            pstm.setString(3, u.getPassword());
            pstm.setString(4, u.getSzig());
            pstm.setString(5, u.getEmail());
            pstm.setString(6, u.getRank());
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println("Nem sikerült hozzáadni!");
            System.out.println(ex);
        }
    }
    
    public void addTermek(Termek t){
        try{
            String sql = "insert into termek (name, vonalkod, egysegar, mennyiseg, megjegyzes) values(?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, t.getNev());
            pstm.setString(2, t.getVonalkod());
            pstm.setString(3, t.getEgysegar());
            pstm.setString(4, t.getMennyiseg());
            pstm.setString(5, t.getMegjegyzes());
            pstm.execute();
        } catch (SQLException ex){
            System.out.println("Nem sikerült hozzáadni!");
            System.out.println(ex);
        }
    }
    
    public void updateUser(User u){
        try {
            String sql = "update users set name = ?, username = ?, password = ?, szig = ?, email = ?, rank = ? where id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, u.getNev());
            pstm.setString(2, u.getUsername());
            pstm.setString(3, u.getPassword());
            pstm.setString(4, u.getSzig());
            pstm.setString(5, u.getEmail());
            pstm.setString(6, u.getRank());
            pstm.setInt(7, Integer.parseInt(u.getId()));
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println("Nem sikerült hozzáadni!");
            System.out.println(ex);
        }
    }
    
    public void updateTermek(Termek t){
        try {
            String sql = "update termek set name = ?, vonalkod = ?, egysegar = ?, mennyiseg = ?, megjegyzes = ? where id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, t.getNev());
            pstm.setString(2, t.getVonalkod());
            pstm.setString(3, t.getEgysegar());
            pstm.setString(4, t.getMennyiseg());
            pstm.setString(5, t.getMennyiseg());
            pstm.setInt(6, Integer.parseInt(t.getId()));
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println("Nem sikerült hozzáadni!");
            System.out.println(ex);
        }
    }
    
    public void delUser(String szig){
        try {
            String sql = "delete from users where id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, szig);
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println("Nem sikerült törölni!");
            System.out.println(ex);
        }
    }
    
    public void delTermek(String vonalkod){
        try {
            String sql = "delete from termek where vonalkod = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, vonalkod);
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println("Nem sikerült törölni!");
            System.out.println(ex);
        }
    }
    
    
}
