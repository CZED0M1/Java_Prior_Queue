package pobocky;

import java.util.Iterator;
import kolekce.EnumPozice;
import pobocka.IPobocka;
import prostredky.Auto;

public interface IAutopujcovna {

    void vlozPobocku(IPobocka Pobocka, EnumPozice Pozice);
//vloží novou pobočku do seznamu na příslušnou pozici (první, poslední, předchůdce, následník)

    IPobocka zpristupniPobocku(EnumPozice Pozice);
//zpřístupní pobočku z požadované pozice (první, poslední, předchůdce, následník, aktuální)

    IPobocka odeberPobocku(EnumPozice Pozice);
//odebere pobočku z požadované pozice (první, poslední, předchůdce, následník, aktuální)

    void vlozAuto(Auto auto, EnumPozice Pozice);
//vloží nové auto do seznamu aktuální pobočky na příslušnou pozici (první, poslední, předchůdce, následník)

    Auto zpristupnAuto(EnumPozice Pozice);
//zpřístupní auto z požadované pozice aktuální pobočky (první, poslední, předchůdce, následník, aktuální)

    Auto odeberAuto(EnumPozice Pozice);
//odebere auto z požadované pozice (první, poslední, předchůdce, následník, aktuální),

    Auto vypujcAuto(EnumPozice Pozice);
//odebere auto z požadované pozice aktuální pobočky a vloží ho do seznamu 
//        výpůjček (první, poslední, předchůdce následník, aktuální)

    Auto vratAuto(EnumPozice Pozice);
//odebere auto z požadované pozice výpůjček a vloží ho do seznamu aktuální
//pobočky (první, poslední, předchůdce, následník,aktuální)

    Auto zpristupniVypujceneAuto(EnumPozice Pozice);
//zpřístupní auto z požadované pozice ze seznamu vypůjčených aut 
//(první, poslední, předchůdce,následník, aktuální),

    Iterator iterator(eTyp typ);
//vrací požadovaný iterátor Poboček/Automobilů/Vypůjčených automobilů

    void zrusPobocku();
//zruší všechny auta v aktuální pobočce

    void zrus();
//zruší všechny pobočky.
//Výčtový typ EnumPozice je dán stavy: první, poslední, předchůdce, následník, aktuální)
}
