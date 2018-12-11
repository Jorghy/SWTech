package teljesenmindegy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
    
    DB db = new DB();
    @FXML
    private Label label_name;
    @FXML
    private Label label_vonalkod;
    @FXML
    private Label label_egysegar;
    @FXML
    private Label label_mennyiseg;
    @FXML
    private Label label_megjegyzes;
    @FXML
    private Pane basePane;
    @FXML
    private Pane LoginPane;
    @FXML
    private Pane adminPane;
    @FXML
    private Pane termekPane;
    @FXML
    private TextField delUser_id;
    @FXML
    private TextField delTermek_id;
    @FXML
    private TextField addTermek_name;
    @FXML
    private TextField addTermek_vonalkod;
    @FXML
    private TextField addTermek_egysegar;
    @FXML
    private TextField addTermek_mennyiseg;
    @FXML
    private TextField addTermek_megjegyzes;
    
    
    
    @FXML
    private void bejelentkezes(ActionEvent event) {
        basePane.setDisable(true);
        basePane.setOpacity(0);
        LoginPane.setVisible(true);
        LoginPane.setDisable(false);
        LoginPane.setOpacity(1);
    }
    @FXML
    private void kereses(ActionEvent event) {
        
    }
    @FXML
    private void termek(ActionEvent event) {
        basePane.setDisable(true);
        basePane.setOpacity(0);
        termekPane.setVisible(true);
        termekPane.setDisable(false);
        termekPane.setOpacity(1);
    }
    @FXML
    private void cancelTermek(ActionEvent event) {
        termekPane.setDisable(true);
        termekPane.setOpacity(0);
        basePane.setVisible(true);
        basePane.setDisable(false);
        basePane.setOpacity(1);
        getAllTermek();
    }
    @FXML
    private void cancelLogin(ActionEvent event) {
        LoginPane.setDisable(true);
        LoginPane.setOpacity(0);
        basePane.setVisible(true);
        basePane.setOpacity(1);
        basePane.setDisable(false);
        getAllTermek();
    }
    @FXML
    private void Login(ActionEvent event) {
        LoginPane.setDisable(true);
        LoginPane.setOpacity(0);
        adminPane.setVisible(true);
        adminPane.setOpacity(1);
        adminPane.setDisable(false);
    }
    @FXML
    private void cancelAdmin(ActionEvent event) {
        adminPane.setDisable(true);
        adminPane.setOpacity(0);
        basePane.setVisible(true);
        basePane.setOpacity(1);
        basePane.setDisable(false);
        getAllTermek();
    }
    @FXML
    private void delUser(ActionEvent event) {
        if(!(delUser_id.getText().isEmpty())){
            String szig = delUser_id.getText();
            db.delUser(szig);
            System.out.println("Sikeres törlés!");
        } else{
            System.out.println("Sikertelen törlés!");
        }
        
    }
    @FXML
    private void addUser(ActionEvent event) {
        
        db.addUser("", "", "", "", "", "");
        System.out.println("Sikeres hozzáadás!");
    }
    @FXML
    private void modUser(ActionEvent event) {
        
        db.addUser("", "", "", "", "", "");
        System.out.println("Sikeres hozzáadás!");
    }
    @FXML
    private void modTermek(ActionEvent event) {
        
        db.addUser("", "", "", "", "", "");
        System.out.println("Sikeres hozzáadás!");
    }
    @FXML
    private void addTermek(ActionEvent event) {
        if(!(addTermek_name.getText().isEmpty()) && !(addTermek_vonalkod.getText().isEmpty()) && !(addTermek_egysegar.getText().isEmpty()) && !(addTermek_mennyiseg.getText().isEmpty()) && !(addTermek_megjegyzes.getText().isEmpty())){
            String nev = addTermek_name.getText();
            String vonalkod = addTermek_vonalkod.getText();
            int egysegar = Integer.parseInt(addTermek_egysegar.getText());
            int mennyiseg = Integer.parseInt(addTermek_mennyiseg.getText());
            String megjegyzes = addTermek_megjegyzes.getText();
            if(!(db.searchTermek(vonalkod))){
                db.addTermek(nev, vonalkod, egysegar, mennyiseg, megjegyzes);
                System.out.println("Sikeres hozzáadás!");
            }else{
                System.out.println("Ez a vonalkód már használatban van!");
            }
        } else {
            System.out.println("Sikertelen hozzáadás!");
        }
        
    }
    @FXML
    private void delTermek(ActionEvent event) {
        if(!(delTermek_id.getText().isEmpty())){
            String vkod = delTermek_id.getText();
            db.delTermek(vkod);
            System.out.println("Sikeres törlés!");
        }else {
            System.out.println("Sikertelen törlés!");
        }
    }
    @FXML
    private void bezar(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    private void getAllTermek() {
        String str_name = "";
        String str_vonalkod = "";
        String str_egysegar = "";
        String str_mennyiseg = "";
        String str_megjegyzes = "";
        for(Termek t : db.getAllTermek()){
            str_name += (t.getNev() + " \n");
            str_vonalkod += (t.getVonalkod() + "\n");
            str_egysegar += (t.getEgysegar() + "\n");
            str_mennyiseg += (t.getMennyiseg() + "\n");
            str_megjegyzes += (t.getMegjegyzes() + "\n");
        }
        label_name.setText(str_name);
        label_vonalkod.setText(str_vonalkod);
        label_egysegar.setText(str_egysegar);
        label_mennyiseg.setText(str_mennyiseg);
        label_megjegyzes.setText(str_megjegyzes);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(db.searchTermek("62347"));
        getAllTermek();
        System.out.println("Elindult a program!");
    }    
    
}
