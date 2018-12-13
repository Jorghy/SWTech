package teljesenmindegy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Controller implements Initializable {
    
    @FXML
    TableView table;
    @FXML
    TextField input_vonalkod;
    @FXML
    TextField input_nev;
    @FXML
    TextField input_egysegar;
    @FXML
    TextField input_mennyiseg;
    @FXML
    TextField input_megjegyzes;
    @FXML
    Button addNewProduct;
    @FXML
    StackPane menuPane;
    @FXML
    Pane basePane;
    @FXML
    Pane adminPane;
    @FXML
    Pane loginPane;
    @FXML
    TextField login_user;
    @FXML
    PasswordField login_pass;
    @FXML
    Button login_bejelentkezes;
    @FXML
    TableView user_Table;
    @FXML
    TextField addUser_nev;
    @FXML
    TextField addUser_username;
    @FXML
    TextField addUser_id;
    @FXML
    TextField addUser_email;
    @FXML
    TextField addUser_rank;
    @FXML
    PasswordField addUser_password;
    
    DB db = new DB();
    
    
    private final ObservableList<Termek> products = FXCollections.observableArrayList();
    
    private final ObservableList<User> users = FXCollections.observableArrayList();
    
    public void setTableData(){
        TableColumn nevCol = new TableColumn("Név");
        nevCol.setMinWidth(250);
        nevCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nevCol.setCellValueFactory(new PropertyValueFactory<Termek, String>("nev"));
        nevCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Termek, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Termek, String> t){
                    Termek termek = (Termek) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    termek.setNev(t.getNewValue());
                    db.updateTermek(termek);
                }
            }
        );
        
        TableColumn vonalkodCol = new TableColumn("Vonalkód");
        vonalkodCol.setMinWidth(100);
        vonalkodCol.setCellFactory(TextFieldTableCell.forTableColumn());
        vonalkodCol.setCellValueFactory(new PropertyValueFactory<Termek, String>("vonalkod"));
        vonalkodCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Termek, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Termek, String> t){
                    Termek termek = (Termek) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    termek.setVonalkod(t.getNewValue());
                    db.updateTermek(termek);
                }
            }
        );
        
        TableColumn egysegarCol = new TableColumn("Egység Ár");
        egysegarCol.setMinWidth(100);
        egysegarCol.setCellFactory(TextFieldTableCell.forTableColumn());
        egysegarCol.setCellValueFactory(new PropertyValueFactory<Termek, String>("egysegar"));
        egysegarCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Termek, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Termek, String> t){
                    Termek termek = (Termek) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    termek.setEgysegar(t.getNewValue());
                    db.updateTermek(termek);
                }
            }
        );
        
        TableColumn mennyisegCol = new TableColumn("Mennyiség");
        mennyisegCol.setMinWidth(70);
        mennyisegCol.setCellFactory(TextFieldTableCell.forTableColumn());
        mennyisegCol.setCellValueFactory(new PropertyValueFactory<Termek, String>("mennyiseg"));
        mennyisegCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Termek, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Termek, String> t){
                    Termek termek = (Termek) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    termek.setMennyiseg(t.getNewValue());
                    db.updateTermek(termek);
                }
            }
        );
        
        TableColumn megjegyzesCol = new TableColumn("Megjegyzes");
        megjegyzesCol.setMinWidth(200);
        megjegyzesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        megjegyzesCol.setCellValueFactory(new PropertyValueFactory<Termek, String>("megjegyzes"));
        megjegyzesCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Termek, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Termek, String> t){
                    Termek termek = (Termek) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    termek.setMegjegyzes(t.getNewValue());
                    db.updateTermek(termek);
                }
            }
        );
        
        table.getColumns().addAll(nevCol, vonalkodCol, egysegarCol, mennyisegCol, megjegyzesCol);
        products.addAll(db.getAllProduct());
        table.setItems(products);
    }
    
    public void setMenuData() {
        TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
        TreeView<String> treeView = new TreeView(treeItemRoot1);
        treeView.setShowRoot(false);
        
        TreeItem<String> nodeItemA = new TreeItem<>("Bejelentkezés");
        TreeItem<String> nodeItemB = new TreeItem<>("Termékek");
        TreeItem<String> nodeItemC = new TreeItem<>("Kilépés");
        treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC);
        menuPane.getChildren().add(treeView);
        
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                String selectedMenu;
                selectedMenu = selectedItem.getValue();
                if(null != selectedMenu){
                    switch (selectedMenu) {
                        case "Kilépés": System.exit(0); break;
                        case "Bejelentkezés":
                            loginPane.setVisible(true);
                            basePane.setVisible(false);
                            break;
                        case "Termékek":
                            basePane.setVisible(true);
                            loginPane.setVisible(false);
                            adminPane.setVisible(false);
                    }
                }
            }
        });
    }
    
    public void setUserData(){
        TableColumn nevCol = new TableColumn("Név");
        nevCol.setMinWidth(125);
        nevCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nevCol.setCellValueFactory(new PropertyValueFactory<User, String>("nev"));
        nevCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<User, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<User, String> t){
                    User u = (User) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    u.setNev(t.getNewValue());
                    db.updateUser(u);
                }
            }
        );
        
        TableColumn usernameCol = new TableColumn("Felhasználó név");
        usernameCol.setMinWidth(100);
        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        usernameCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<User, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<User, String> t){
                    User u = (User) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    u.setUsername(t.getNewValue());
                    db.updateUser(u);
                }
            }
        );
        
        TableColumn passwordCol = new TableColumn("Jelszó");
        passwordCol.setMinWidth(100);
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        passwordCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<User, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<User, String> t){
                    User u = (User) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    u.setPassword(t.getNewValue());
                    db.updateUser(u);
                }
            }
        );
        
        TableColumn idCol = new TableColumn("Szig. Szám");
        idCol.setMinWidth(100);
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setCellValueFactory(new PropertyValueFactory<User, String>("szig"));
        idCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<User, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<User, String> t){
                     User u = (User) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    u.setSzig(t.getNewValue());
                    db.updateUser(u);
                }
            }
        );
        
        TableColumn emailCol = new TableColumn("Email cím");
        emailCol.setMinWidth(200);
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        emailCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<User, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<User, String> t){
                    User u = (User) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    u.setEmail(t.getNewValue());
                    db.updateUser(u);
                }
            }
        );
        
        TableColumn rankCol = new TableColumn("Jogosultság");
        rankCol.setMinWidth(100);
        rankCol.setCellFactory(TextFieldTableCell.forTableColumn());
        rankCol.setCellValueFactory(new PropertyValueFactory<User, String>("rank"));
        rankCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<User, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<User, String> t){
                    User u = (User) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    u.setRank(t.getNewValue());
                    db.updateUser(u);
                }
            }
        );
        
        user_Table.getColumns().addAll(nevCol,usernameCol,passwordCol,idCol,emailCol,rankCol);
        users.addAll(db.getAllUsers());
        user_Table.setItems(users);
        
    }
    
    public void login(){
        loginPane.setVisible(false);
        adminPane.setVisible(true);
        setUserData();
    }
    
    public void cancelLogin(){
        loginPane.setVisible(false);
        basePane.setVisible(true);
    }
    
    @FXML
    private void addProduct(ActionEvent event){
        if( !(input_nev.getText().isEmpty()) &&  !(input_vonalkod.getText().isEmpty()) && !(input_egysegar.getText().isEmpty()) && !(input_mennyiseg.getText().isEmpty()) && !(input_megjegyzes.getText().isEmpty())) {
            Termek t = new Termek(input_nev.getText(),input_vonalkod.getText(), input_egysegar.getText(), input_mennyiseg.getText(), input_megjegyzes.getText());
            products.add(t);
            db.addTermek(t);
            input_nev.clear();
            input_vonalkod.clear();
            input_egysegar.clear();
            input_mennyiseg.clear();
            input_megjegyzes.clear();
        }else {
            System.out.println("Minden adatot meg kell adni!");
        }
        
    }
    
    @FXML
    private void addUser(ActionEvent event){
        if( !(addUser_nev.getText().isEmpty()) &&  !(addUser_username.getText().isEmpty()) && !(addUser_id.getText().isEmpty()) && !(addUser_email.getText().isEmpty()) && !(addUser_password.getText().isEmpty()) && !(addUser_rank.getText().isEmpty())) {
            User u = new User(addUser_nev.getText(),addUser_username.getText(),addUser_password.getText(), addUser_id.getText(), addUser_email.getText(), addUser_rank.getText());
            users.add(u);
            db.addUser(u);
            addUser_nev.clear();
            addUser_username.clear();
            addUser_id.clear();
            addUser_email.clear();
            addUser_password.clear();
            addUser_rank.clear();
        }else {
            System.out.println("Minden adatot meg kell adni!");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableData();
        setMenuData();
        
    }
    
}
