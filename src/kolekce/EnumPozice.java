package kolekce;

public enum EnumPozice {
    PRVNI("První"),
    POSLEDNI("Poslední"),
    PREDCHUDCE("Predchudce"),
    NASLEDNIK("Následník"),
    AKTUALNI("Aktualní");

    private final String nazev;

    private EnumPozice(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }

    public static Enum[] getTypy() {
        Enum[] vycet = {PRVNI,POSLEDNI,PREDCHUDCE,NASLEDNIK,AKTUALNI};
        return vycet;
    }

    @Override
    public String toString() {
        return nazev;
    }
}
