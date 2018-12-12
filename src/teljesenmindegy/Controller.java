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
    TextField login_pass;
    @FXML
    Button login_bejelentkezes;
    
    private final ObservableList<Termek> products = FXCollections.observableArrayList(
            new Termek("Telefon","12345","50000","20","Sony"),
            new Termek("Melegszendvics sütő","12346","5000","50","SilverCrest"),
            new Termek("IMac","12347","500000","5","IMac")
    );
    
    public void setTableData(){
        TableColumn nevCol = new TableColumn("Név");
        nevCol.setMinWidth(250);
        nevCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nevCol.setCellValueFactory(new PropertyValueFactory<Termek, String>("nev"));
        nevCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Termek, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Termek, String> t){
                    ((Termek) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setNev(t.getNewValue());
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
                    ((Termek) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setVonalkod(t.getNewValue());
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
                    ((Termek) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setEgysegar(t.getNewValue());
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
                    ((Termek) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setMennyiseg(t.getNewValue());
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
                    ((Termek) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setMegjegyzes(t.getNewValue());
                }
            }
        );
        
        table.getColumns().addAll(nevCol, vonalkodCol, egysegarCol, mennyisegCol, megjegyzesCol);
        table.setItems(products);
    }
    
    public void setMenuData() {
        TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
        TreeView<String> treeView = new TreeView(treeItemRoot1);
        treeView.setShowRoot(false);
        
        TreeItem<String> nodeItemA = new TreeItem<>("Bejelentkezés");
        TreeItem<String> nodeItemB = new TreeItem<>("Kilépés");
        treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB);
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
                    }
                }
            }
        });
    }
    
    public void login(){
        loginPane.setVisible(false);
        adminPane.setVisible(true);
    }
    
    public void cancelLogin(){
        loginPane.setVisible(false);
        basePane.setVisible(true);
    }
    
    @FXML
    private void addProduct(ActionEvent event){
        if( !(input_nev.getText().isEmpty()) &&  !(input_vonalkod.getText().isEmpty()) && !(input_egysegar.getText().isEmpty()) && !(input_mennyiseg.getText().isEmpty()) && !(input_megjegyzes.getText().isEmpty())) {
            products.add(new Termek(input_nev.getText(),input_vonalkod.getText(), input_egysegar.getText(), input_mennyiseg.getText(), input_megjegyzes.getText()));
            input_nev.clear();
            input_vonalkod.clear();
            input_egysegar.clear();
            input_mennyiseg.clear();
            input_megjegyzes.clear();
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
