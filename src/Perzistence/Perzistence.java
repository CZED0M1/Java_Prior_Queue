package Perzistence;

import com.sun.org.apache.xpath.internal.axes.IteratorPool;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import javafx.collections.ObservableList;
import kolekce.AbstrDoubleList;
import kolekce.EnumPozice;
import kolekce.IAbstrDoubleList;
import pobocka.IPobocka;
import pobocka.Pobocka;
import pobocky.Autopujcovna;
import static pobocky.Autopujcovna.SEZNAM_POBOCEK;
import pobocky.eTyp;
import priorQueue.AbstrPriorQueue.Prvek;
import prostredky.Auto;
import prostredky.OsobniAuta;
import prostredky.UzitkovaAuta;
import util.Barva;

public class Perzistence {

    public static void fileWriter(IAbstrDoubleList<IPobocka> seznam, String file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {

            IPobocka last = SEZNAM_POBOCEK.zpristupniPosledni();
            SEZNAM_POBOCEK.zpristupniPrvni();

            for (IPobocka pobocka : seznam) {
                Iterator<Object> it = pobocka.iteratorPr();
                while (it.hasNext()) {
                    Prvek a =(Prvek)it.next();
                    Auto auto =(Auto) a.getValue();
                    writer.write(auto.saveData());
                    writer.write("\n");
                }
                Iterator<Object> it2 = pobocka.iterator();
                while (it2.hasNext()) {
                    Prvek a =(Prvek)it2.next();
                    Auto auto =(Auto) a.getValue();
                    writer.write(auto.saveData());
                    writer.write("\n");
                }
                if (SEZNAM_POBOCEK.zpristupniAktualni() != last && !SEZNAM_POBOCEK.jePrazdny()) {
                    SEZNAM_POBOCEK.zpristupniNaslednika();
                }
            }
            writer.flush();
            writer.close();
        }
    }

    public static void fileWriterPobocky(Iterator<Pobocka> iterator, String file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {

            while (iterator.hasNext()) {
                writer.write(iterator.next().toString());
                writer.write("\n");
            }

            writer.flush();
            writer.close();
        }
    }

    public static void fileWriterVypujcena(Iterator<Auto> iterator, String file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            while (iterator.hasNext()) {
                writer.write(iterator.next().saveVypujData());
                writer.write("\n");
            }

            writer.flush();
            writer.close();
        }
    }

    public static void fileReaderPob(ObservableList<IPobocka> list, String file) throws IOException {
        Autopujcovna au = new Autopujcovna();
        au.zrus();
        list.clear();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        do {
            String full;
            full = br.readLine();
            Pobocka pob = new Pobocka(full);
            Autopujcovna.SEZNAM_POBOCEK.vlozPosledni(pob);
            list.add(pob);
        } while (br.ready());
    }

    public static void fileReaderVyp(ObservableList<Auto> list, String file) throws IOException {
        String full;
        String[] rozdeleno;
        String spz;
        int km = 0;
        int pocetVypujc = 0;
        String color;
        Barva barva;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        do {
            full = br.readLine();
            rozdeleno = full.split(",");
            spz = rozdeleno[1];
            try {
                km = Integer.parseInt(rozdeleno[2]);
                pocetVypujc = Integer.parseInt(rozdeleno[3]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException();
            }
            switch (rozdeleno[0]) {
                case "ua":
                    int nosnost = 0;
                    try {
                        nosnost = Integer.parseInt(rozdeleno[4]);
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException();
                    }
                    Auto auto = new UzitkovaAuta(nosnost, spz, km, pocetVypujc);
                    Autopujcovna.SEZNAM_VYPUJC.vlozPosledni(auto);
                    list.add(auto);
                    break;
                case "oa":
                    color = rozdeleno[4];
                    switch (color) {
                        case ("bílá"):
                            barva = Barva.BILA;
                            break;
                        case ("modrá"):
                            barva = Barva.MODRA;
                            break;
                        case ("červená"):
                            barva = Barva.CERVENA;
                            break;
                        case ("zelená"):
                            barva = Barva.ZELENA;
                            break;
                        case ("žlutá"):
                            barva = Barva.ZLUTA;
                            break;
                        default:
                            barva = Barva.CERNA;
                            break;
                    }
                    auto = new OsobniAuta(barva, spz, km, pocetVypujc);
                    Autopujcovna.SEZNAM_VYPUJC.vlozPosledni(auto);
                    list.add(auto);
                    break;
            }

        } while (br.ready());

    }

    public static void fileReader(ObservableList<Object> list,String file) throws IOException {
        Auto auto = null;
        String full;
        String[] rozdeleno;
        String spz;
        int km = 0;
        int pocetVypujc = 0;
        String color;
        Barva barva;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        do {
            full = br.readLine();
            rozdeleno = full.split(",");
            spz = rozdeleno[2];
            String jmenoP = rozdeleno[1];
            SEZNAM_POBOCEK.zpristupniPrvni();
            while (!jmenoP.equals(SEZNAM_POBOCEK.zpristupniAktualni().toString())) {
                SEZNAM_POBOCEK.zpristupniNaslednika();
                System.out.println("ne");
            }
            try {
                km = Integer.parseInt(rozdeleno[3]);
                pocetVypujc = Integer.parseInt(rozdeleno[4]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException();
            }
            switch (rozdeleno[0]) {
                case "ua":
                    int nosnost = 0;
                    try {
                        nosnost = Integer.parseInt(rozdeleno[5]);
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException();
                    }
                    auto = new UzitkovaAuta(nosnost, spz, km, pocetVypujc);
                    break;
                case "oa":
                    color = rozdeleno[5];
                    switch (color) {
                        case ("bílá"):
                            barva = Barva.BILA;
                            break;
                        case ("modrá"):
                            barva = Barva.MODRA;
                            break;
                        case ("červená"):
                            barva = Barva.CERVENA;
                            break;
                        case ("zelená"):
                            barva = Barva.ZELENA;
                            break;
                        case ("žlutá"):
                            barva = Barva.ZLUTA;
                            break;
                        default:
                            barva = Barva.CERNA;
                            break;
                    }
                    auto = new OsobniAuta(barva, spz, km, pocetVypujc);
                    break;
            }
            System.out.println(auto.toString());
            SEZNAM_POBOCEK.zpristupniAktualni().vlozAuto(auto, EnumPozice.POSLEDNI);
            list.add(auto);
        } while (br.ready());
    }
}
