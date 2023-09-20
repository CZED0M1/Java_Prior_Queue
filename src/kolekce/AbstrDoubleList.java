package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class AbstrDoubleList<T> implements IAbstrDoubleList<T> {

    private PolozkaSeznamu<T> prvniPolozka;
    private PolozkaSeznamu<T> posledniPolozka;
    private PolozkaSeznamu<T> aktualniPolozka;

    private int velikostSeznamu = 0;

    public class PolozkaSeznamu<T> {

        private final T hodnota;
        private PolozkaSeznamu<T> dalsi;
        private PolozkaSeznamu<T> predchozi;

        public PolozkaSeznamu(T data) {
            this.hodnota = data;
        }
    }

    class IteratorImpl implements Iterator<T> {


        PolozkaSeznamu<T> element = prvniPolozka;
        


        @Override
        public boolean hasNext() {
            return element != null;
        }

        @Override
        public T next() {

            if (hasNext()) {
                T data = element.hodnota;
                element = element.dalsi;
                return data;
            }
            throw new NoSuchElementException();
        }

    }

    @Override
    public void zrus() {
        velikostSeznamu = 0;
        aktualniPolozka = null;
        prvniPolozka = null;
        posledniPolozka = null;
    }

    @Override
    public boolean jePrazdny() {
        return velikostSeznamu == 0;
    }

    @Override
    public void vlozPrvni(T data) {
        if (data == null) {
            throw new NullPointerException();
        }
        PolozkaSeznamu<T> element = new PolozkaSeznamu(data);
        if (jePrazdny()) {
            prvniPolozka = element;
            posledniPolozka = element;
        } else {
            element.dalsi = prvniPolozka;
            prvniPolozka.predchozi = element;
            prvniPolozka = element;
        }
        velikostSeznamu++;
    }

    @Override
    public void vlozPosledni(T data) {
        if (data == null) {
            throw new NullPointerException();
        }
        PolozkaSeznamu<T> element = new PolozkaSeznamu(data);

        if (jePrazdny()) {
            prvniPolozka = element;
            posledniPolozka = element;
        } else {
            element.predchozi = posledniPolozka;
            posledniPolozka.dalsi = element;
            posledniPolozka = element;
        }
        velikostSeznamu++;
    }

    @Override
    public void vlozNaslednika(T data) {
        PolozkaSeznamu<T> element = new PolozkaSeznamu(data);
        PolozkaSeznamu<T> temp;
        if (data == null) {
            throw new NullPointerException();
        }
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }

        if (aktualniPolozka == posledniPolozka) {
            vlozPosledni(data);

        } else if (aktualniPolozka == posledniPolozka && aktualniPolozka == prvniPolozka) {
            prvniPolozka.dalsi = element;
            posledniPolozka = element;
        } else {
            temp = aktualniPolozka.dalsi;
            aktualniPolozka.dalsi = element;
            element.predchozi = aktualniPolozka;
            element.dalsi = temp;
            temp.predchozi = element;
        }
        velikostSeznamu++;
    }

    @Override
    public void vlozPredchudce(T data) {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        PolozkaSeznamu<T> element = new PolozkaSeznamu(data);
        PolozkaSeznamu<T> temp;
        if (aktualniPolozka == prvniPolozka) {
            element.dalsi = prvniPolozka;
            prvniPolozka.predchozi = element;
            prvniPolozka = element;
        } else if (aktualniPolozka == posledniPolozka && aktualniPolozka == prvniPolozka) {
            prvniPolozka.dalsi = prvniPolozka;
            prvniPolozka = element;
        } else {
            temp = aktualniPolozka.predchozi;
            aktualniPolozka.predchozi = element;
            element.dalsi = aktualniPolozka;
            element.predchozi = temp;
            temp.dalsi = element;
        }
        velikostSeznamu++;

    }

    @Override
    public T zpristupniAktualni() {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        if (aktualniPolozka.hodnota == null) {
            throw new NullPointerException();
        }
        return aktualniPolozka.hodnota;
    }

    @Override
    public T zpristupniPrvni() {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        aktualniPolozka = prvniPolozka;
        return prvniPolozka.hodnota;
    }

    @Override
    public T zpristupniPosledni() {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        aktualniPolozka = posledniPolozka;
        return posledniPolozka.hodnota;
    }

    @Override
    public T zpristupniNaslednika() {
        if (aktualniPolozka.dalsi != null) {
            aktualniPolozka = aktualniPolozka.dalsi;
        }
        return aktualniPolozka.hodnota;
    }

    @Override
    public T zpristupniPredchudce() {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        if (aktualniPolozka.predchozi.hodnota == null) {
            throw new NullPointerException();
        }
        aktualniPolozka = aktualniPolozka.predchozi;
        return aktualniPolozka.hodnota;
    }

    @Override
    public T odeberAktualni() {
        if (aktualniPolozka == null) {
            throw new NullPointerException();
        }

        if (aktualniPolozka == posledniPolozka) {
            return odeberPosledni();
        } else if (aktualniPolozka == prvniPolozka) {
            return odeberPrvni();
        } else {
            PolozkaSeznamu<T> temp = aktualniPolozka;
            aktualniPolozka = aktualniPolozka.dalsi;
            aktualniPolozka.predchozi = temp.predchozi;
            aktualniPolozka = aktualniPolozka.predchozi;
            aktualniPolozka.dalsi = temp.dalsi;
            aktualniPolozka = prvniPolozka;
            velikostSeznamu--;
            return temp.hodnota;
        }
    }

    @Override
    public T odeberPrvni() {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        PolozkaSeznamu<T> temp = prvniPolozka;
        if (prvniPolozka.dalsi == null) {
            zrus();
        } else {
            prvniPolozka = prvniPolozka.dalsi;
        }
        velikostSeznamu--;
        return temp.hodnota;
    }

    @Override
    public T odeberPosledni() {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        PolozkaSeznamu<T> temp = posledniPolozka;
        if (posledniPolozka.predchozi == null) {
            zrus();
        } else {
            posledniPolozka = posledniPolozka.predchozi;
            posledniPolozka.dalsi = null;
        }
        velikostSeznamu--;
        return temp.hodnota;
    }

    @Override
    public T odeberNaslednika() {
        if (aktualniPolozka.dalsi == null) {
            throw new NullPointerException();
        }
        velikostSeznamu--;
        if (aktualniPolozka.dalsi == posledniPolozka) {
            PolozkaSeznamu<T> temp = posledniPolozka;
            posledniPolozka = aktualniPolozka;
            posledniPolozka.dalsi = null;
            return temp.hodnota;
        } else {
            PolozkaSeznamu<T> temp = aktualniPolozka.dalsi;
            aktualniPolozka.dalsi = aktualniPolozka.dalsi.dalsi;
            aktualniPolozka.dalsi.predchozi = aktualniPolozka;
            return temp.hodnota;
        }
    }

    @Override
    public T odeberPredchudce() {
        if (aktualniPolozka.predchozi == null) {
            throw new NullPointerException();
        }
        velikostSeznamu--;
        if (aktualniPolozka.predchozi == prvniPolozka) {
            PolozkaSeznamu<T> temp = prvniPolozka;
            prvniPolozka = aktualniPolozka;
            prvniPolozka.predchozi = null;
            return temp.hodnota;
        } else {
            PolozkaSeznamu<T> temp = aktualniPolozka.predchozi;
            aktualniPolozka.predchozi = aktualniPolozka.predchozi.predchozi;
            aktualniPolozka.predchozi.dalsi = aktualniPolozka;
            return temp.hodnota;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void forEach(Consumer<? super T> cnsmr) {
        IAbstrDoubleList.super.forEach(cnsmr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<T> spliterator() {
        return IAbstrDoubleList.super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getVelikostSeznamu() {
        return velikostSeznamu;
    }

}
