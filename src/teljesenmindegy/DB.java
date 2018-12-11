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
                String sql = "create table users(name varchar(20), username varchar(20), password varchar(20), id varchar(20), cim varchar(20), rank varchar(20))";
                createStatement.execute(sql);
            }
            if(!rs2.next()){
                String sql = "create table termek(name varchar(20), vonalkod varchar(20), egysegar varchar(20), mennyiseg varchar(20), megjegyzes varchar(20))";
                createStatement.execute(sql);
            }
        } catch (SQLException ex) {
            System.out.println("Nem sikerült létrehozni a táblákat!");
            System.out.println(ex);
        }
    }
    
    public void addUser(String nev, String username, String password, String id, String address, String rank){
        
        try {
            String sql = "insert into users values (?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nev);
            pstm.setString(2, username);
            pstm.setString(3, password);
            pstm.setString(4, id);
            pstm.setString(5, address);
            pstm.setString(6, rank);
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
    
    public void addTermek(String nev, String vonalkod, int egysegar, int mennyiseg, String megjegyzes){
        try{
            String sql = "insert into termek values(?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nev);
            pstm.setString(2, vonalkod);
            pstm.setInt(3, egysegar);
            pstm.setInt(4, mennyiseg);
            pstm.setString(5, megjegyzes);
            pstm.execute();
        } catch (SQLException ex){
            System.out.println("Nem sikerült hozzáadni!");
            System.out.println(ex);
        }
    }
    
    public ArrayList<Termek> getAllTermek(){
        String sql = "select * from termek";
        ArrayList<Termek> termekek = null;
        try{
            ResultSet rs = createStatement.executeQuery(sql);
            termekek = new ArrayList<>();
            while(rs.next()){
                String nev = rs.getString("name");
                String vonalkod = rs.getString("vonalkod");
                int egysegar = rs.getInt("egysegar");
                int mennyiseg = rs.getInt("mennyiseg");
                String megjegyzes = rs.getString("megjegyzes");
                Termek t = new Termek(nev,vonalkod,egysegar,mennyiseg,megjegyzes);
                termekek.add(t);
            }
        }catch(SQLException ex){
            System.out.println("Nem sikerült lekérdezni!");
            System.out.println(ex);
        }
        return termekek;
    }
    
    public boolean searchUser(String szig){
        try{
            String sql = "select * from users where id = " + szig;
            ResultSet rs = createStatement.executeQuery(sql);
            if(rs.next()){
                return true;
            }else {
                return false;
            }
        } catch(SQLException ex){
            System.out.println("Nem sikerült hozzáadni!");
            System.out.println(ex);
        }
        return false;
    }
    
    public boolean searchTermek(String vonalkod){
        String sql = "select * from termek where vonalkod =" + "'vonalkod'";
        ArrayList<Termek> termekek = null;
        try{
            ResultSet rs = createStatement.executeQuery(sql);
            termekek = new ArrayList<>();
            while(rs.next()){
                String nev = rs.getString("name");
                vonalkod = rs.getString("vonalkod");
                int egysegar = rs.getInt("egysegar");
                int mennyiseg = rs.getInt("mennyiseg");
                String megjegyzes = rs.getString("megjegyzes");
                Termek t = new Termek(nev,vonalkod,egysegar,mennyiseg,megjegyzes);
                termekek.add(t);
            }
            if(termekek.isEmpty()){
                return true;
            }
        }catch(SQLException ex){
            System.out.println("Nem sikerült lekérdezni!");
            System.out.println(ex);
        }
        return false;
    }
}
