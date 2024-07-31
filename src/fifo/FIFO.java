package fifo;

import java.util.NoSuchElementException;

public class FIFO implements FIFOQueue{
    private char[] queue; // länge vom Array ist nicht bekkant
    private int index;
    private int laenge;

    public FIFO(int laenge){
        this.laenge = laenge;
        queue = new char[laenge];
        index = -1;
    }


    @Override
    public FIFOQueue enqueue(char zeichen) {
        if(index == laenge-1){
            throw new IllegalArgumentException("Array ist voll!");
        }
        index++;
        queue[index] = zeichen;
        return this;
    }

    @Override
    public FIFOQueue dequeue() {
        if(isEmpty()){
            throw new NoSuchElementException("Array ist leer!!");
        }
        // entferne das Element, das zuerst im Array hinzugefügt wurden
        // ['a', 'h' , 't', '\0']
        // dequeue --> ['\0', 'h', 't', '\0']
        queue[0] = '\0';
        // um die Lücken zu entfernen
        for(int i = 0; i <= index ; i++){
            queue[i] = queue[i+1];
            queue[i+1] = '\0';
        }
        //--> ['\0', 'h', 't', '\0'] to ['h', 't', '\0', '\0']
        index--;

        return this;
    }

    @Override
    public char front() {
        if(isEmpty()){
            throw new NoSuchElementException("Array ist leer!!");
        }
        return queue[0];
    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    public static void main(String[] args){


        // objekt vom Typ lifo.LIFO
        FIFO l1 = new FIFO(8); // länge ist bekannt aber werte sind nicht bekannt
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
        // l1.   (nach . kommt alle Attributen und Methoden, die in der Klasse lifo.LIFO stehen)
        // --> ['1', '3', '5', '7', , , , ] index = 3  (i = 0) isEmpty() = false
        // --> ['1', '3', '5', , , , , ] index = 2     (i = 1) isEmpty() = false
        // --> ['1', '3', , , , , , ] index = 1        (i = 2) isEmpty() = false
        // --> ['1', , , , , , , ] index = 0           (i = 3) isEmpty() = false
        // --> [ , , , , , , , ] index = -1            (i = 4) isEmpty() = true
        // i = 5 ---> pop() .. isEmpty() = true --> Exception




        // Nur zu Erklärung und hat mit der Lösung nix zu tun
        // Ausgabe der verbliebenen Elemente
        // ['1', '3', '5', '7', , , , ] -->  l1.stack.length = 8

        for (int i = 0; i < l1.queue.length; i++){
            //System.out.println(l1.stack[i]);
            // l1.pop(); geht hier nicht Erkläung OBEN
        }

        // Lösung - Variante 1
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


        /*
         i = 0 --> Ausgabe : 7 ..  l1.pop() , index = 3 --> stack[3] = '\0' --> ['1', '3', '5', , , , , ] --> index = 2
         i = 1 --> Ausgabe : 5 ..  l1.pop() , index = 2 --> stack[2] = '\0' --> ['1', '3', , , , , , ] --> index = 1
         i = 2 --> i <= index ??? 2 <= 1 ?? false

         .
         */
    }
}
