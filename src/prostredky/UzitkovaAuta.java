package prostredky;

public class UzitkovaAuta extends Auto {

    private final int nosnost;

    public UzitkovaAuta(int nosnost, String spz, int stavKm, int pocetVypujceni) {
        super(spz, stavKm, pocetVypujceni);
        this.nosnost = nosnost;
    }

    @Override
    public String toString() {
        return "UzitkovaAuta{" + super.toString() + "nosnost=" + nosnost + '}';
    }

    @Override
    public String saveData() {
        return "ua," + super.saveData() + "," + nosnost;
    }

    @Override
    public String saveVypujData() {
        return "ua," + super.saveVypujData() + "," + nosnost;
    }

    @Override
    public int compareTo(Auto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
