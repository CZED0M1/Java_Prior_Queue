
package pobocky;

public enum eTyp {
    POBOCKY("Pobočky"),
    AUTA("Automobily"),
    VYPUJCENA_AUTA("Vypůjčené automobily");

    private final String nazev;

    private eTyp(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }
    
    public static Enum[] getTypy() {
        Enum[] vycet = {POBOCKY,AUTA,VYPUJCENA_AUTA};
        return vycet;
    }

    @Override
    public String toString() {
        return nazev;
    }
}
