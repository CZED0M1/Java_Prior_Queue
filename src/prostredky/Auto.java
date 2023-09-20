package prostredky;

import static pobocky.Autopujcovna.SEZNAM_POBOCEK;


public abstract class Auto implements java.io.Serializable, Comparable<Auto> {

    private final String spz;
    private int stavKm;
    private int pocetVypujceni;

    public Auto(String spz, int stavKm, int pocetVypujceni) {
        this.spz = spz;
        this.stavKm = stavKm;
        this.pocetVypujceni = pocetVypujceni;
    }

    @Override
    public String toString() {
        return "spz=" + spz + ", stavKm=" + stavKm + ", pocetVypujceni=" + pocetVypujceni ;
    }
    
    public String saveData() {
        return SEZNAM_POBOCEK.zpristupniAktualni().toString() + "," + spz + "," + stavKm + "," + pocetVypujceni;
    }
    
    public String saveVypujData() {
        return spz + "," + stavKm + "," + pocetVypujceni;
    }

    public int getStavKm() {
        return stavKm;
    }

    public void setStavKm(int stavKm) {
        this.stavKm = stavKm;
    }

    public int getPocetVypujceni() {
        return pocetVypujceni;
    }

    public void setPocetVypujceni(int pocetVypujceni) {
        this.pocetVypujceni = pocetVypujceni;
    }
    
    
}
