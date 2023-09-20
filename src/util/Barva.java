package util;

public enum Barva {

    BILA("bílá"),
    MODRA("modrá"),
    CERVENA("červená"),
    ZELENA("zelená"),
    ZLUTA("žlutá"),
    CERNA("černá");

    private final String nazev;

    private Barva(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }

    @Override
    public String toString() {
        return nazev;
    }

}
