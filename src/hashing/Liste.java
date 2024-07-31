// Liste
package hashing;

public class Liste {
    Zelle anfang;
    Zelle cursor;


    // [1,2,3,4,6,...]
    int laenge () {
        Zelle cur = anfang;
        int l = 0;
        while (cur != null) {
            l++;
            cur = cur.next;
        }

        return l;
    }

    /*
      1. Auf welche „Hash-Buckets“ werden zunächst die Strings jeweils abgebildet, und
    wie lautet die ursprünglich hier im Programmcode verwendete Hashfunktion?

    Dokumentieren sie die entsprechende Methode geeignet mit den Antworten auf die
    obigen Fragen.
     */
    /**
     * die Methode liefert den selben Hashwert für alle Eingabestrings
     * @param x der Eingabestrings
     * @param b die länge der Hashtabelle
     * @return den Index des Hash-Buckets, in den der String eingefügt werden soll.
     *         In dieser Methode wird immer der letzte Bucket der Hashtabelle zurückgegeben
     */
    /*
    int hashFunktion (String x,int b ) {
        return b-1;
    }
     */

    /*
    3. Programmieren Sie eine eigene (sinnvollere) Hashfunktion.

    Dokumentieren Sie die neue Methode, und äußern
    Sie sich zu Vorteilen gegenüber der bisherigen
    Hashfuntion.
    */
    /*
    Wie soll ich Strings sortieren
    "Ahmad" --> Bucket 0 ('A')
    "Boushra" --> Bucket 1 ('B')
    --> alphabetisch

    damit wir nicht auf nicht existierten Index zugriefen
    --> Wert % LängeDerHsahtabelle
    Regel:
    X % Y --> Ergebnis ist zwischen 0 und Y-1

    6 % 5 --> 1
     */

    /*
    erste Index = 0
    letzte Index = n-1
     */
    // "1234713".lastIndexOf('1') --> 5

    // letzter Buchstabe im String:
    // int index = wert.charAt(wert.length()-1) % hashtabelleLaenge;


    /*
    "aDam"
    Index - Hashfuntkion
    erste Großer Buchsrabe vorkmmot im String
    int index = 0;
    for(int i = 0; i < wert.length(); i++) {
        if(wert,charAt(i) >= 'A' && wert.charAt(i) <= 'Z'){
            index = wert.charAt(i) % hashtabelleLaenge
            break;
        }
    }
    return index;

    */

    // wert.charAt(i) % hashtabelleLaenge
    // A --> 0



    /**
     * die Methode liefert den Hashwert für alle Eingabestrings
     * @param wert der Eingabestrings
     * @param hashtabelleLaenge die länge der Hashtabelle
     * @return den Index des Hash-Buckets, in den der String eingefügt werden soll.
     *
     */
    // "A" --> Bucket 0
    // "A" --> 65 % 5 = 0
    // "a" --> Bucket 0
    // "a" --> 97 % 5 = 2 (Problem Lösung unten)
    int hashFunktion(String wert, int hashtabelleLaenge){
        int index = 0;
        char ersteBuchstabe = wert.charAt(0);
        // falls der Buchstabe klein ist -->
        /*
        Unterschied zwischen jedem  klein und groß Buchstabe ist immer 32
        b : 98  B: 66
        c : 99  C: 67
        klein - groß = 32

        konvertierung von klein zu groß  buchstabe - 32
        konvertierung von groß zu klein  buchstabe + 32

         */
        if(ersteBuchstabe >= 'a' && ersteBuchstabe <= 'z')
            ersteBuchstabe = (char) (ersteBuchstabe - 32);

        index = ersteBuchstabe % hashtabelleLaenge;
        return index;
    }

    boolean istGueltigePosition (int p) {
        // die Position ist gültig wenn p zwischen 1 und länge steht
        return (p >= 1) && (p <= laenge () );
    }

    /*
    Positionen --> von 1 bis länge
     */

     /*

                  C
       Bucket 0: [A] -> [a]
       anfang :
       A steht position --> 2
       a steht position --> 1
       Bucket 1: []
       Bucket 2: []
       Bucket 3: []

       --------------------
       positionen: von n bis 1
       Bucket n : [n] ......... [1]

     */

    void setzeCursor (int p){
        // crusor zeigt auf null, wenn die Position ungültig ist ( <1 odwer >länge)
        cursor = null;
        if (istGueltigePosition(p) ) {
            Zelle cur = anfang;
            for (int i = 1; i < p;i++)
                cur = cur.next;
            cursor = cur;
        }
    }


    /*
    anfang wird immer aktualisiert wenn neues Element hinzufügt wird
     */
    void einsetzenAnfang (String e){
        Zelle z = new Zelle (e,anfang);
        // anfang aktualisiseren
        anfang = z;
    }


    /*
    loescheElem(E):

       Bucket 0: [a] -> [A]
       Bucket 1: []
       Bucket 2: []
       Bucket 3: []
       Bucket 4: [e] -> [E]

       Anfang --> e (Anfang ist immer in  position 1)
       Anfang.next = E

        loesche(2)
    */
    void loesche (int p) {
        if (istGueltigePosition(p)){ // true
            // Falls wir den Anfang löschen wollen
            // --> aktuallisierung Anfang
            if (p == 1) // Loesche 1. Zelle
                anfang = anfang.next;
            else {
                // Aufruf von setzeCursor
                setzeCursor(p-1);
                // cursor = e
                // cursor.next = E
                // cursor.next.next = null
                // e -> E -> null
                // löschen E
                // e -> null
                cursor.next = cursor.next.next;
                // e.next = null
            }
        }
    }


    /*
    loescheElem(E):

       Bucket 0: [a] -> [A]
       Bucket 1: []
       Bucket 2: []
       Bucket 3: []
       Bucket 4: [e] -> [E]

     */
    void loescheElem (String e) {
        for (int i = 1; i <= laenge(); i++) {
            if(inhalt(i).equals(e))
                loesche(i);
        }
        //Loesche(Suche (e));
    }
    /*
                  C
       Bucket 0: [A] -> [a]
       A steht position --> 1
       a steht position --> 2
       Bucket 1: []
       Bucket 2: []
       Bucket 3: []
     */

    String inhalt (int p){
        setzeCursor (p);
        return cursor.inhalt;
    }

    /*
    gültige Position : von 1 bis länge
    suche --> Rückgabe : 1- gültige Position
          --> Rückgabe : 2- -1 (nicht gefunden)
     */

    /*
      Korb : k
                 k1    k2
      Bucket 0: [a] -> [A]
      Bucket 1: []
      Bucket 2: []
      Bucket 3: []
      Bucket 4: []
     */
    // laenge() --> gibt uns die Länge des Bucket zurück (wie viele Elemente)
    // suche von A --> element = A
    int suche(String element) {
        // warum von 1 bis länge (gültige Position von 1 bis länge)
        for (int i = 1; i <= laenge(); i++) {
            if (inhalt(i).equals(element)) {
                return i;
            }
        }
        return -1; // gibt -1 zurück, wenn das Element nicht gefunden wird
    }

    /*
        Bucket 0 (leer):
        einfügen : A
        Bucket 0: [A]
        einfügen : a
        Bucket 0: [a] -> [A]
        einfügen : aa
        Bucket 0: [aa] -> [a] -> [A]

       Bucket 0: [aa] -> [a] -> [A]
       Bucket 1: []
       Bucket 2: []
       Bucket 3: []
       Bucket 4: []

       // erste eingefügtes Element in position --> laenge()
       // letzte eingefügtes Element in position --> 1
     */

    // Ausgabe vom ersten eingefügten Element bis letzten eingefügten Element
    String zeigeAlleElementeInBucket(int bucket){
        String ausgabe = "Elemente in Bucket " + bucket +" :\n";
        ausgabe += "--------------------------------------\n";
        for (int i = laenge(); i >= 1; i--) {
            ausgabe += inhalt(i) + "\n";
        }
        /*
        falls ausgabe vom letzte eingefügten Element bis ersten eingefügten Element
         for (int i = 1; i <= laenge(); i++) {
            ausgabe += inhalt(i) + "\n";
         }
         */
        ausgabe += "--------------------------------------\n";
        return ausgabe;
    }

    String listenLaengen(int bucket){
        String ausgabe = "";
        ausgabe += "Bucket " + bucket + " hat " + laenge() + " Elemente\n";
        return ausgabe;
    }
}


