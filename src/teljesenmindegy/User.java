package teljesenmindegy;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleStringProperty nev;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty szig;
    private final SimpleStringProperty email;
    private final SimpleStringProperty rank;
    private final SimpleStringProperty id;

    public User(String nev, String username, String password, String szig, String email, String rank) {
        this.nev = new SimpleStringProperty(nev);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.szig = new SimpleStringProperty(szig);
        this.email = new SimpleStringProperty(email);
        this.rank = new SimpleStringProperty(rank);
        this.id = new SimpleStringProperty("");
    }
    
    public User(Integer id, String nev, String username, String password, String szig, String email, String rank) {
        this.nev = new SimpleStringProperty(nev);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.szig = new SimpleStringProperty(szig);
        this.email = new SimpleStringProperty(email);
        this.rank = new SimpleStringProperty(rank);
        this.id = new SimpleStringProperty(String.valueOf(id));
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

    public String getSzig() {
        return szig.get();
    }

    public void setSzig(String i) {
        szig.set(i);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String a) {
        email.set(a);
    }

    public String getRank() {
        return rank.get();
    }

    public void setRank(String r) {
        rank.set(r);
    }
    
    public String getId() {
        return id.get();
    }

    public void setId(String d) {
        id.set(d);
    }
}
