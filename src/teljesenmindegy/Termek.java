package teljesenmindegy;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Termek {
    private final SimpleStringProperty nev;
    private final SimpleStringProperty vonalkod;
    private final SimpleStringProperty egysegar;
    private final SimpleStringProperty mennyiseg;
    private final SimpleStringProperty megjegyzes;
    private final SimpleStringProperty id;

    public Termek(String nev, String vonalkod, String egysegar, String mennyiseg, String megjegyzes) {
        this.nev = new SimpleStringProperty(nev);
        this.vonalkod = new SimpleStringProperty(vonalkod);
        this.egysegar = new SimpleStringProperty(egysegar);
        this.mennyiseg = new SimpleStringProperty(mennyiseg);
        this.megjegyzes = new SimpleStringProperty(megjegyzes);
        this.id = new SimpleStringProperty("");
    }
    
    public Termek(Integer id, String nev, String vonalkod, String egysegar, String mennyiseg, String megjegyzes) {
        this.nev = new SimpleStringProperty(nev);
        this.vonalkod = new SimpleStringProperty(vonalkod);
        this.egysegar = new SimpleStringProperty(egysegar);
        this.mennyiseg = new SimpleStringProperty(mennyiseg);
        this.megjegyzes = new SimpleStringProperty(megjegyzes);
        this.id = new SimpleStringProperty(String.valueOf(id));
    }
    
    public String getNev() {
        return nev.get();
    }
    
    public void setNev(String n){
        nev.set(n);
    }
    
    public String getVonalkod() {
        return vonalkod.get();
    }
    
    public void setVonalkod(String v){
        vonalkod.set(v);
    }
    
    public String getEgysegar() {
        return egysegar.get();
    }
    
    public void setEgysegar(String e){
        egysegar.set(e);
    }
    
    public String getMennyiseg() {
        return mennyiseg.get();
    }
    
    public void setMennyiseg(String mn){
        mennyiseg.set(mn);
    }
    
    public String getMegjegyzes() {
        return megjegyzes.get();
    }
    
    public void setMegjegyzes(String mj){
        nev.set(mj);
    }
    
    public String getId() {
        return id.get();
    }

    public void setId(String d) {
        id.set(d);
    }
}
