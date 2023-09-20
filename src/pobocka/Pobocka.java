package pobocka;

import java.util.Iterator;
import javafx.collections.ObservableList;
import kolekce.EnumPozice;
import priorQueue.AbstrPriorQueue;
import prostredky.Auto;

public class Pobocka implements IPobocka {

    private final String jmeno;
    private int pocetAuto = 0;
    private AbstrPriorQueue<Integer, Auto> primary;

    public Pobocka(String jmeno) {
        this.jmeno = jmeno;
        primary = new AbstrPriorQueue();
    }

    @Override
    public void vlozAuto(Auto auto, EnumPozice Pozice) {
        primary.vloz(auto.getStavKm(), auto);
        pocetAuto++;
    }

    @Override
    public Auto zpristupnAuto(EnumPozice Pozice) {
        return primary.zpristupni();
    }

    @Override
    public Auto odeberAuto(EnumPozice Pozice) {

        pocetAuto--;
        return primary.odeberMax();
    }

    @Override
    public Iterator<Auto> iterator() {
        return primary.iterator();
    }

    @Override
    public Iterator<Auto> iteratorPr() {
        return primary.vytvorIterator();
    }


    @Override
    public void zrus() {
        primary.zrus();
        pocetAuto = 0;
    }

    public int getPocetAuto() {
        return pocetAuto;
    }

    public void setPocetAuto(int pocetAuto) {
        this.pocetAuto = pocetAuto;
    }

    @Override
    public String toString() {
        return jmeno;
    }

    @Override
    public void vybudujFrontu() {
        primary.vybuduj();
    }

}
