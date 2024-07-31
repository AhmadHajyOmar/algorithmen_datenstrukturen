package lifo;

// Array-basiert
// Datentyp char
public class LIFO implements LIFOStack{
    private char[] stack; // länge vom Array ist nicht bekkant
    private int index;
    private int laenge;

    // lifo.LIFO stack = new lifo.LIFO(5);  [1,2,3,4, ] -> länge (Anzahl der Plätze) = 5 (länge ist immer vom Typ int)
    // int[] arr = new int[3]; -> [0, 0, 0], boolean[] arr_b = new boolean[3]; [false, false, false]
    // String --> String[] arr = new String[2]; --> [null, null] (String ist ein Objekt)
    // char   --> char[] arr = new char[2];     --> [null, null] .. ['\0', '\0']
    // char c = '\0'; variable c ist leer
    // wobei 5 ist die länge


    // stack index = -1 ??? (index = -1)  Wenn array leer ist index ist immer = -1
    public LIFO(int laenge){
        stack = new char[laenge];
        index = -1;
        this.laenge = laenge;
    }

    // [ null,  null,  null]
    // Eiinfügen von Vorne (index 0) nach hinten (index n-1)
    // z.B länge ->  n Index: 0 .... bis .... n-1
    // n = 5 , index : 0 .... 4


    /*
     Falsche Variante :
     Einfügen -> [ , , ]
     item = 'A'
     index = -1
     stack[index] = item
     stack[-1] = 'A'

     Richtige Variante:
     Aufruf : push('A')
     Einfügen -> [ , , ]
     item = 'A'
     index = 0
     stack[index] = item
     stack[0] = 'A'
     ['A', , ]

     push('B')
     item = 'B'
     index = 1;
     stack[1] = 'B'

     push('C')
     item = 'C'
     index = 2
     stack[2] = 'C'
     Aufruf : isFull() -> index == (laenge - 1) -> index == (3 - 1) ?? true

     push('D')
     item = 'D'
     index = 3
     index existiert nicht !!
     d.h. wir müssen eine Bedingung überprüfen
     */
    @Override
    public void push(char item) {

        if(isFull()){
            throw new IllegalArgumentException("Array ist Voll!");
        }


        // wie kann man auf der Länge eines Arrays zugreifen
        //   NameDesArray.length --<#> gibt uns die Länge des Arrays
        /*
        if(index == stack.length - 1){
            throw new IllegalArgumentException("Array ist Voll!");
        }
        */

        /*
         Die Bildschirmausgabe, (d.h. z.B. System.out.println()-Aufrufe) soll nicht
        von den Methoden der Datenstruktur erfolgen, sondern aus Ihrem Testprogramm heraus.
        System.out.println("Hinzufügtes Element = " + item);  --> diese Anweisung darf hier nicht stehen

         */

        index++;
        stack[index] = item;

        // oder stack[++index] = item;
    }

    /**
     *
     * @return gibt das letzte Element zurück
     */

    // [ ,  ,  ]
    // index = 0 ['A', , ]
    // index = 1 ['A', 'B', ] --> Aufruf : top() gibt uns stack[index] = stack[1] = 'B'
    // was ist wenn das Array leer ist !!!! was gibt uns top() zurück
    // [ , , ] -->> !!! nix (IllegalArgumentException)
    // wie kann man auf die Element des Arrays zugreifen --> NamesDesArrays[index]
    @Override
    public char top() {
        if(isEmpty()){
            throw new IllegalArgumentException("Array ist leer!");
        }
        return stack[index];
    }

    // lifo.LIFO entfernt das letzte eingefügte Element( Last In Firsst Out)
    // ['a', 'z', 'i', ] index = 2
    // pop() --> ['a', 'z', , ], -->  char = '\0' falls leer ist ... index--
    // push('t')
    // ['a', 'z', , ] index = 3 --> ['a', 'z', ,'t']
    @Override
    public void pop() {
        if(isEmpty()){
            throw new IllegalArgumentException("Array ist leer!!");
        }
        /*
          Die Bildschirmausgabe, (d.h. z.B. System.out.println()-Aufrufe) soll nicht
        von den Methoden der Datenstruktur erfolgen, sondern aus Ihrem Testprogramm heraus.
                System.out.println("Entferntes Element = " + this.top()); --> diese Anweisung darf hier nicht stehen

         */
        stack[index] = '\0';
        index--;
        // oder stack[--inedx] = '\0'; ist nicht zulässig
        // ['a', 'z', 't', ]
        // pop() --> index -- -> index = 1, dann stack[index] = '\0' -> stack[1] = '\0'

        // oder stack[inedx--] = '\0'; ist zulässig
        // ['a', 'z', 't', ]
        // pop() --> stack[index] = '\0' -> stack[2] = '\0', dann index-- -> index = 1

    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    @Override
    public boolean isFull() {
        return index == (laenge - 1);
        // falls die länge ist gleich 6;
        // maximale index = länge - 1;
        // d.h. array ist voll nur falls index = n-1
    }


    /*
    1. Test-Anforderungen für Z2:
    Programmieren Sie ein Programm DataStructureTest um Ihre Datenstruktur zu testen. --> main methode
    • In diesem Programm sollen innerhalbeiner Schleife die folgenden Aktionen in jedem Schleifendurchlauf
     durchgeführt werden:
     2 Elemente (z.B. Integer, Double und Strings) werden der Datenstruktur hinzugefügt,
     (d.h. in jeder Iteration werden 2 Elemente auf einmal hinzufügt (push(..) zwei mal))
     und dann eines entfernt (dann pop() ).
        o Führen Sie den obigen Test aus für folgende (in 4 Durchläufen der obigen Schleife) Beispieleingabe von
         insgesamt 8 Eingabeelementen: [1, 2, 3, 4, 5, 6, 7, 8]. Beachten Sie:
         1. Alle Eingabeelementen sollten genau einmal eingefügt werden.
         2. Der Test soll auch für alternative Eingabearrays zumindest für solche bestehend aus eine geraden Anzahl
         von Zahlen funktionieren.
        o Geben Sie vor jedem einzelnen Hinzufügen und Entfernen von Elementen diese auf den Bildschirm aus,
        und geben sie weiterhin aus welches das derzeit zugreifbare Element der Datenstruktur ist.
        Beachten Sie: Die Bildschirmausgabe, (d.h. z.B. System.out.println()-Aufrufe) soll nicht
        von den Methoden der Datenstruktur erfolgen, sondern aus Ihrem Testprogramm heraus.
    • Geben Sie nach der obigen Prozedur den Zustand der Datenstruktur aus, indem Sie in einer
    Schleife alle verbliebenen Elemente jeweils erst auf den Bildschirm ausgeben und dann entfernen.

     */

    public static void main(String[] args){
        // objekt vom Typ lifo.LIFO
        LIFO l1 = new LIFO(8); // länge ist bekannt aber werte sind nicht bekannt
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
            l1.push(element);
            element = zeichen[index]; // element = zeichen[index] = zeichen[1] = '2'
            index++; // index = 2;
            System.out.println("Hinzufügtes Element = " + element);
            l1.push(element);
            // l1 -> ['1', '2', , , , , , ]
            System.out.println("Entferntes Element = " + l1.top()); // gibt '2' zurück
            l1.pop();
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
        for (int i = 0; i < l1.stack.length; i++){
            //System.out.println(l1.stack[i]);
           // l1.pop(); geht hier nicht Erkläung OBEN
        }

        // Lösung - Variante 1
        while(!l1.isEmpty()){
            System.out.println(l1.top()); // Ausgabe letzte eingefügte Element
            l1.pop(); // Entfernen letzte eingefügte Element
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


