package priorQueue;

import java.util.Iterator;

public interface IAbstrPriorQueue<K extends Comparable<K>, V> extends Iterable<V> {
void zrus();
boolean jePrazdny();
void vloz(K Key,V Value);
V odeberMax();
V zpristupni();
Iterator vytvorIterator();
void vybuduj();
}
