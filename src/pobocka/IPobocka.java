package pobocka;

import java.util.Iterator;
import javafx.collections.ObservableList;
import kolekce.EnumPozice;
import prostredky.Auto;

public interface IPobocka {

    void vlozAuto(Auto auto, EnumPozice Pozice);
//vloží nové auto do
//seznamu na příslušnou pozici (první, poslední, předchůdce, následník)

    Auto zpristupnAuto(EnumPozice Pozice);
//zpřístupní auto z požadované
//pozice (první, poslední, předchůdce, následník, aktuální)

    Auto odeberAuto(EnumPozice Pozice);
//odebere auto z požadované
//pozice (první, poslední, předchůdce, následník, aktuální),

    Iterator iterator();
//vytvoří iterátor

    void zrus();
//zruší všechny auta.

    Iterator iteratorPr();

    void vybudujFrontu();
    }
