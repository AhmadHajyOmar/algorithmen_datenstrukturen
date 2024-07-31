package fifo;

import lifo.KnotenNonGenericTwo;

import java.util.NoSuchElementException;

public class ZeigerFIFO implements FIFOQueue {
    private KnotenNonGenericTwo node;

    @Override
    public FIFOQueue enqueue(char zeichen) {
        if (node == null) {
            node = new KnotenNonGenericTwo(zeichen, null);
        } else {
            KnotenNonGenericTwo aktuell = node;
            while (aktuell.getNext() != null) {
                aktuell = aktuell.getNext();
            }
            aktuell.setNext(new KnotenNonGenericTwo(zeichen, null));
        }
        return this;
    }

    @Override
    public FIFOQueue dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Stqack ist leer!");
        }
        node = node.getNext();
        return this;
    }

    @Override
    public char front() {
        // falls die Liste leer ist
        if (isEmpty()){
            throw new NoSuchElementException("Stack ist leer!!");
        }
        return node.getElement();
    }

    @Override
    public boolean isEmpty() {
        return node == null;
    }


    public static void main(String[] args){

        // objekt vom Typ lifo.LIFO
        ZeigerFIFO l1 = new ZeigerFIFO(); // länge ist bekannt aber werte sind nicht bekannt
        // Array ist leer
        // l1 -> ['\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0']

        // Schreiben wir ein Array (Werte sind bekannt)
        char[] zeichen = {'1', '2', '3', '4', '5', '6', '7', '8'};
        int index = 0;
        // i < 4 (da von 0 bis 3  --> 4 durchläufe werden durchgeführt)
        char element;
        for(int i = 0; i < 4;i++){
            element = zeichen[index]; // element = zeichen[index] = zeichen[0] = '1'
            index++; // index = 1;
            System.out.println("Hinzufügtes Element = " + element);
            l1.enqueue(element);
            element = zeichen[index]; // element = zeichen[index] = zeichen[1] = '2'
            index++; // index = 2;
            System.out.println("Hinzufügtes Element = " + element);
            l1.enqueue(element);
            // l1 -> ['1', '2', , , , , , ]
            System.out.println("Entferntes Element = " + l1.front()); // gibt '2' zurück
            l1.dequeue();
            // l1 --> ['1', , , , , , , ]
        }

        /*
        Geben Sie nach der obigen Prozedur den Zustand der Datenstruktur (in unserem Fall ist das Objekt l1 aus,
         indem Sie in einer Schleife alle verbliebenen Elemente jeweils
          erst auf den Bildschirm ausgeben und dann entfernen.
         */




        // Nur zu Erklärung und hat mit der Lösung nix zu tun
        // Ausgabe der verbliebenen Elemente

        for (int i = 0; !l1.isEmpty(); i++){
            System.out.println(l1.front());
            l1.dequeue(); //geht hier nicht Erkläung OBEN

            // --> endlosse Schleife
        }


        // Lösung - Variante 1  -->
        while(!l1.isEmpty()){
            System.out.println(l1.front()); // Ausgabe letzte eingefügte Element
            l1.dequeue(); // Entfernen letzte eingefügte Element
        }

        /*
        // Variante - 2
        // Lösung Ausgabe + Entfernen
        // ['1', '3', '5', '7', , , , ] --> index = 3
        for(int i = 0; !l1.isEmpty(); i++){
            System.out.println(l1.top());
            l1.pop();
        }
        */

        /*
        dritte Variante
        int counter = l1.index;
        for(int i = 0; i <= counter; i++){
            if(!l1.isEmpty()){
                System.out.println(l1.top());
                l1.pop();
            }
        }
         */



    }

}

class KnotenNonGeneric {
    private char wert; // Das zu verwaltende Datenelement
    private KnotenNonGenericTwo next; // Referenz auf den nächsten Knoten
    public KnotenNonGeneric(char wert, KnotenNonGenericTwo next) {
        this.wert = wert;
        this.next = next;
    }

    /** Setzt die Referenz auf den Nachfolger-Knoten */
    public void setNext(KnotenNonGenericTwo next){
        this.next=next;
    }
    /** Gibt das Datenelement des Knotens zurück */
    public char getElement() { // Gibt das gespeicherte Objekt aus
        return wert;
    }
    /** Gibt den Nachfolge-Knoten zurück */
    public KnotenNonGenericTwo getNext() {
        return next;
    }
}


