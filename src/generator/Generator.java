package generator;

import java.util.Random;
import kolekce.EnumPozice;
import pobocka.IPobocka;
import prostredky.Auto;
import prostredky.OsobniAuta;
import prostredky.UzitkovaAuta;
import util.Barva;

public class Generator {

    private static final Random RANDOM = new Random();

    public static void generuj(int pocet,IPobocka pob) {
        Auto auto = null;
        for (int i = 0; i < pocet; i++) {
            switch (RANDOM.nextInt(2)) {
                case 0:
                    auto = new UzitkovaAuta(genNosnost(), genSpz(), genKm(), genVypujc());
                    break;
                case 1:
                    auto = new OsobniAuta(genBarva(), genSpz(), genKm(), genVypujc());
                    break;
            }
            pob.vlozAuto(auto, EnumPozice.POSLEDNI);
            System.out.println(auto);
        }
        System.out.println("hotovo " + pocet);

    }

    private static String genSpz() {
        String spz = new String();
        spz += (RANDOM.nextInt(10));
        spz += (char) (RANDOM.nextInt(26) + 'A');
        spz += (char) (RANDOM.nextInt(26) + 'A');
        spz += " ";
        spz += (RANDOM.nextInt(10));
        spz += (RANDOM.nextInt(10));
        spz += (RANDOM.nextInt(10));
        spz += (RANDOM.nextInt(10));
        return spz;
    }

    private static int genKm() {
        int km = RANDOM.nextInt(200000);
        return km;
    }

    private static int genVypujc() {
        int vypujceni = RANDOM.nextInt(100);
        return vypujceni;
    }

    private static Barva genBarva() {
        Barva barva = null;
        switch (RANDOM.nextInt(6)) {
            case 0:
                barva = Barva.BILA;
                break;
            case 1:
                barva = Barva.CERNA;
                break;
            case 2:
                barva = Barva.CERVENA;
                break;
            case 3:
                barva = Barva.MODRA;
                break;
            case 4:
                barva = Barva.ZELENA;
                break;
            case 5:
                barva = Barva.ZLUTA;
                break;
        }
        return barva;
    }

    private static int genNosnost() {
        int nosnost = RANDOM.nextInt(12000) + 5000;
        return nosnost;
    }
}
