package teljesenmindegy;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleStringProperty nev;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty id;
    private final SimpleStringProperty address;
    private final SimpleStringProperty rank;

    public User(String nev, String username, String password, String id, String address, String rank) {
        this.nev = new SimpleStringProperty(nev);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.id = new SimpleStringProperty(id);
        this.address = new SimpleStringProperty(address);
        this.rank = new SimpleStringProperty(rank);
    }

    public String getNev() {
        return nev.get();
    }

    public void setNev(String n) {
        nev.set(n);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String u) {
        username.set(u);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String p) {
        password.set(p);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String i) {
        id.set(i);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String a) {
        address.set(a);
    }

    public String getRank() {
        return rank.get();
    }

    public void setRank(String r) {
        rank.set(r);
    }
}
