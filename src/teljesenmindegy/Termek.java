package teljesenmindegy;
public class Termek {
    private String nev;
    private String vonalkod;
    private int egysegar;
    private int mennyiseg;
    private String megjegyzes;

    public Termek(String nev, String vonalkod, int egysegar, int mennyiseg, String megjegyzes) {
        this.nev = nev;
        this.vonalkod = vonalkod;
        this.egysegar = egysegar;
        this.mennyiseg = mennyiseg;
        this.megjegyzes = megjegyzes;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getVonalkod() {
        return vonalkod;
    }

    public void setVonalkod(String vonalkod) {
        this.vonalkod = vonalkod;
    }

    public int getEgysegar() {
        return egysegar;
    }

    public void setEgysegar(int egysegar) {
        this.egysegar = egysegar;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public String getMegjegyzes() {
        return megjegyzes;
    }

    public void setMegjegyzes(String megjegyzes) {
        this.megjegyzes = megjegyzes;
    }

    @Override
    public String toString() {
        return "Termek{" + "nev=" + nev + ", vonalkod=" + vonalkod + ", egysegar=" + egysegar + ", mennyiseg=" + mennyiseg + ", megjegyzes=" + megjegyzes + '}';
    }
    
    
}
