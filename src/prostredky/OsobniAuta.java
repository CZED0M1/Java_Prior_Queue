package prostredky;

import util.Barva;

public class OsobniAuta extends Auto {

    private final Barva barva;

    public OsobniAuta(Barva barva, String spz, int stavKm, int pocetVypujceni) {
        super(spz, stavKm, pocetVypujceni);
        this.barva = barva;
    }

    @Override
    public String toString() {
        return "OsobniAuta{" + super.toString() + "barva=" + barva + '}';
    }

    @Override
    public String saveData() {
        return "oa," + super.saveData() + "," + barva;
    }

    @Override
    public String saveVypujData() {
        return "oa," + super.saveVypujData() + "," + barva;
    }

    @Override
    public int compareTo(Auto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
