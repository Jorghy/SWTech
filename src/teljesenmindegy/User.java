package teljesenmindegy;
public class User {
    private String nev;
    private String username;
    private String password;
    private String id;
    private String address;
    private String rank;

    public User(String nev, String username, String password, String id, String address, String rank) {
        this.nev = nev;
        this.username = username;
        this.password = password;
        this.id = id;
        this.address = address;
        this.rank = rank;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "User{" + "nev=" + nev + ", username=" + username + ", password=" + password + ", id=" + id + ", address=" + address + ", rank=" + rank + '}';
    }
    
    
    
}
