package command;

import Perzistence.Perzistence;
import static command.Keyboard.keyboard;
import static generator.Generator.generuj;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;
import kolekce.EnumPozice;
import pobocka.Pobocka;
import static pobocka.Pobocka.seznamAut;
import pobocky.Autopujcovna;
import pobocky.eTyp;
import prostredky.Auto;
import prostredky.OsobniAuta;
import util.Barva;

public class CommandLineMain {

    private static final String FILE = "Save.txt";

    public static void main(String[] args) throws IOException {
        run();
    }

    private static void run() throws IOException {
        String cmd;
        boolean close = false;
        while (!close) {
            System.out.print("Zadej příkaz: ");
            cmd = keyboard.nextLine().trim();
            switch (cmd) {
                case "generuj":
                    System.out.print("Zadej počet: ");
                    cmd = keyboard.nextLine().trim();
                    generuj(Integer.parseInt(cmd), Autopujcovna.SEZNAM_POBOCEK.zpristupniAktualni());
                    break;
                case "exit":
                    close = true;
                    break;
                case "obnov":
                    Perzistence.fileReader(seznamAut, FILE);
                    break;
                case "zalohuj":
                    Perzistence.fileWriter(seznamAut, FILE);
                    break;
                case "AutoDalsi":
                    System.out.println(Autopujcovna.SEZNAM_POBOCEK.zpristupniAktualni().zpristupnAuto(EnumPozice.NASLEDNIK));
                    break;
                case "AutoPred":
                    System.out.println(Autopujcovna.SEZNAM_POBOCEK.zpristupniAktualni().zpristupnAuto(EnumPozice.PREDCHUDCE));
                    break;
                case "AutoPrv":
                    System.out.println(Autopujcovna.SEZNAM_POBOCEK.zpristupniAktualni().zpristupnAuto(EnumPozice.PRVNI));
                    break;
                case "AutoPosl":
                    System.out.println(Autopujcovna.SEZNAM_POBOCEK.zpristupniAktualni().zpristupnAuto(EnumPozice.POSLEDNI));
                    break;
                case "zrus":
                    Autopujcovna.SEZNAM_POBOCEK.zpristupniAktualni().zrus();
                    break;
                case "pocet":
                    System.out.println(Autopujcovna.SEZNAM_POBOCEK.zpristupniAktualni().toString());
                    break;
                case "zobrazA":
                    Iterator it = new Autopujcovna().iterator(eTyp.AUTA);
                    while (it.hasNext()) {
                        System.out.println(it.next().toString());
                    }
                    break;
                case "novy":
                    Auto a = new OsobniAuta(Barva.BILA, "LAH", 0, 0);
                    Autopujcovna.SEZNAM_POBOCEK.zpristupniAktualni().vlozAuto(a, EnumPozice.PRVNI);
                    break;
                case "vytvorPob":
                    System.out.print("Zadej jméno: ");
                    cmd = keyboard.nextLine().trim();
                    Pobocka pob = new Pobocka(cmd);
                    Autopujcovna.SEZNAM_POBOCEK.vlozPosledni(pob);
                    Autopujcovna.SEZNAM_POBOCEK.zpristupniPosledni();
                    break;
                case "PobPosl":
                    System.out.println(Autopujcovna.SEZNAM_POBOCEK.zpristupniPosledni());
                    break;
                case "PobPrv":
                    System.out.println(Autopujcovna.SEZNAM_POBOCEK.zpristupniPrvni());
                    break;
                case "PobDalsi":
                    System.out.println(Autopujcovna.SEZNAM_POBOCEK.zpristupniNaslednika());
                    break;
                case "PobPred":
                    System.out.println(Autopujcovna.SEZNAM_POBOCEK.zpristupniPredchudce());
                    break;
                default:
                    System.out.println("EEE");
            }
        }
    }

}
