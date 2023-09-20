package priorQueue;

import java.util.Iterator;
import kolekce.AbstrDoubleList;


public class AbstrPriorQueue<K extends Comparable<K>, V> implements IAbstrPriorQueue<K, V> {

    private AbstrDoubleList<Prvek> utrizene = new AbstrDoubleList<>();
    private AbstrDoubleList<Prvek> neutr = new AbstrDoubleList<>();
    private K mez;
    private final int MAX = 5;

    @Override
    public void vybuduj() {

        for (int i = 0; i < MAX; i++) {
            Iterator it = neutr.iterator();
            Prvek prvek = (Prvek) it.next();
            neutr.zpristupniPrvni();

            it = neutr.iterator();
            while (it.hasNext()) {
                Prvek prvekNext = (Prvek) it.next();
                if (prvekNext.key.compareTo(prvek.key) < 0) {
                    prvek = prvekNext;
                }

            }

            utrizene.vlozPosledni(prvek);
            neutr.zpristupniPrvni();
            while (true) {
                if (neutr.zpristupniAktualni().key.compareTo(prvek.key) == 0) {
                    System.out.println(neutr.odeberAktualni());
                    mez = prvek.key;
                    break;
                }else {
                    neutr.zpristupniNaslednika();
                }

            }

        }

    }

    public class Prvek {

        V value;
        K key;

        public Prvek(V value, K key) {
            this.value = value;
            this.key = key;
        }

        @Override
        public String toString() {
            return "" + value;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }
        

    }

    @Override
    public void zrus() {
        utrizene = new AbstrDoubleList<>();
    }

    @Override
    public boolean jePrazdny() {
        return utrizene.jePrazdny();
    }

    @Override
    public void vloz(K key, V value) {
        if (key != null && value != null) {
            Prvek prvek = new Prvek(value, key);
            if (jePrazdny()) {
                utrizene.vlozPrvni(prvek);
                mez = key;
                return;
            } else {
                if (utrizene.getVelikostSeznamu() == MAX) {
                    if (key.compareTo(mez) >= 0) {
                        neutr.vlozPosledni(prvek);
                    } else {
                        Iterator it = utrizene.iterator();
                        utrizene.zpristupniPrvni();
                        while (it.hasNext()) {

                            Prvek p = (Prvek) it.next();

                            if (key.compareTo(p.key) <= 0) {
                                utrizene.vlozPredchudce(prvek);

                                neutr.vlozPosledni(utrizene.odeberPosledni());
                                mez = utrizene.zpristupniPosledni().key;
                                return;
                            }
                            utrizene.zpristupniNaslednika();
                        }
                    }

                } else {
                    if (key.compareTo(utrizene.zpristupniPrvni().key) == 0) {
                        utrizene.vlozPrvni(prvek);
                        return;
                    }
                    if (key.compareTo(utrizene.zpristupniPosledni().key) > 0) {
                        utrizene.vlozPosledni(prvek);
                        mez = key;
                        return;
                    }

                    Iterator it = utrizene.iterator();
                    utrizene.zpristupniPrvni();
                    while (it.hasNext()) {
                        Prvek p = (Prvek) it.next();

                        if (key.compareTo(p.key) <= 0) {
                            utrizene.vlozPredchudce(prvek);
                            return;
                        }
                        utrizene.zpristupniNaslednika();

                    }
                }

            }
        }
    }

    @Override
    public V odeberMax() {
        if (utrizene.jePrazdny()) {
            return null;
        }
        if (utrizene.getVelikostSeznamu() > 0) {
            return utrizene.odeberPrvni().value;
        } else {
            return null;
        }
    }

    @Override
    public V zpristupni() {
        if (!utrizene.jePrazdny()) {
            return utrizene.zpristupniPrvni().value;
        } else {
            return null;
        }

    }

    @Override
    public Iterator vytvorIterator() {
        return utrizene.iterator();
    }

    @Override
    public Iterator iterator() {
        return neutr.iterator();
    }

}
