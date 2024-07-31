package hashing;

import javax.swing.*;
import java.util.NoSuchElementException;

/*
Generelles:
Die Aufgabenstellung besteht aus einer Hauptaufgabe, in der Sie Hashtabellen
betrachten sollen, in denen Verkettung verwendet wird. Als optionale Zusatzaufgabe
können Sie (für Bonuspunkte) dann eine zweite, alternative Implementation der
HashStruktur realisieren, die statt Verkettung offenes Hashing verwendet.

Aufgabenbeschreibung: Hash-Datenstruktur mit Verkettungen

    Gegeben sei die Applikation HashApp.java, die einzugebende Strings in eine Hashtabelle
    gemäß einer definierten Hashfunktion einfügt.
    // von Ahmad
    // wo wird jedes eingefügtes Element stehen (in welche Index)#
    // wer berechnet den Index --> die HashFunktion

    1. Auf welche „Hash-Buckets“ werden zunächst die Strings jeweils abgebildet, und
    wie lautet die ursprünglich hier im Programmcode verwendete Hashfunktion?

    Dokumentieren sie die entsprechende Methode geeignet mit den Antworten auf die
    obigen Fragen.

    2. Schreiben Sie die 4 Funktionalitäten Element suchen, Element löschen, Liste eines
    Buckets ausgeben, und listenLaengen mit den Eigenschaften:

    a. Element suchen: sucht einen einzugebenden String in der Hashtabelle und gibt -
    falls vorhanden- die Nummer des Korbes an.

    b. Element löschen: löscht ein Datenelement aus der Hashtabelle sofern existent.

    c. Liste eines Buckets ausgeben: Gibt alle Elemente der Datenstruktur aus, die
    sich im Bucket mit dem angegebenen Index befinden.

    d. listenLaengen: gibt die Länge der Listen aller Buckets aus.


    3. Programmieren Sie eine eigene (sinnvollere) Hashfunktion.
    Dokumentieren Sie die neue Methode, und äußern Sie sich zu Vorteilen gegenüber der bisherigen
    Hashfuntion.
 */


public class HashApp {
    public static void main (String args []) {

        final int B = 5;
        String  menue = "Eingabe\n" ;
        menue += " 1) Element einfuegen\n";
        menue += " 2) Element suchen\n"; //TODO
        menue += " 3) Zelle loeschen\n"; //TODO
        menue += " 4) Laenge der Liste von Korb k\n";
        menue += " 5) Liste von Korb k ansehen\n"; //TODO
        menue += " 6) Laenge der Listen von allen Koerben\n"; //TODO
        menue += " 7) Liste zufaellig fuellen\n";
        String ausgabe="";
        Liste hashTabelle[];
        // hashTabelle ist ein Array, dass alle Buckets enthält
        // Jedes Buckes ist eine Liste
        // wobei die Liste porgrammieren wir selber
        // hashTabelle[L1, L2, L3, L4] jedes L ist ein Objekt von der Klasse Liste

        hashTabelle = new Liste [B];
        //  hashTabelle hat 5 Buckets
        // von Bucket 0 bis Bucket 4

        // Was ist die Zelle :
        // Bucket 0 enthält eine Liste, wobei jede Liste hat biliebig viele Zellen
        // Bucket 0 : ["5"] -> ["15"] -> ["25"]
        // ["5"] . next ["15"]

        // durchlaufen jede Buckets erstellen Liste in jedem Bucket
        // Wenn es kein Konstruktor in der Klasse existiert
        // wird Standard konstruktor automatisch definiert
        // -> nutzen wir den Standard Konstruktor
        for (int i = 0; i < B;i++)
            hashTabelle[i] = new Liste();




        while (true) {

            String menueeingabe = JOptionPane.showInputDialog(menue);
            if (menueeingabe == null)
                break;
            String dialogEingabe;

            // Einfügen ist gegeben
            // hashFunktion(dialogEingabe, B)
            // dialogEingabe --> Eingabe vom Typ String
            // B
            /*
            Buckets : 5
            Bucket 0: []
            Bucket 1: []
            Bucket 2: []
            Bucket 3: []
            Bucket 4: ["A"] -> ["B"]
            Einfügen "A"
            dialogEingabe = "A"
            B = 5

            Einfügen "B"
            dialogEingabe = "A"
            B = 5
             */

            /*
            Bucket 0: [A] -> [a]
            Bucket 1: []
            Bucket 2: []
            Bucket 3: []
             */

            if (menueeingabe.equals("1")) {
                dialogEingabe = JOptionPane.showInputDialog("Datenstring?");
                //int zahl = Integer.parseInt (eingabe);
                int h = hashTabelle[0].hashFunktion(dialogEingabe, B); //Welcher Korb?
                boolean existsInHashtable = false;
                for (int j = 1; j <= hashTabelle[h].laenge(); j++) {
                    if (hashTabelle[h].inhalt(j).equals(dialogEingabe)){
                        ausgabe = "Element in Korb " + h + " gefunden, daher nicht eingefuegt";
                        existsInHashtable = true;
                        break;
                    }

                }
                if (!existsInHashtable) {
                    hashTabelle[h].einsetzenAnfang(dialogEingabe);
                    ausgabe = "Eingabe eingefuegt in bucket " + h;
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("2")) {
                dialogEingabe = JOptionPane.showInputDialog("Welches Element soll gesucht werden?");
                // Suche : zuerst müssen wir herausfinden:
                // welcher Bucket ?
                // hashFunktion --> welcher  Bucket
                // wo stehet die gesuchte Element (welcher Bucket)
                // suche(A)
                // A steht im Bucket 0 in Korb 2 (Position 2)
                /*
                   Korb : k
                             k1     k2
                   Bucket 0: [a] -> [A]
                   Bucket 1: []
                   Bucket 2: []
                   Bucket 3: []
                   Bucket 4: []
                 */
                // dialogEingabe = A, B = 5
                // um die Methode hashFunktion aufrufen
                // klassenmethode(static) oder  Instanzmethode(nicht static) ?
                // Instanzmethode
                // um eine Instanzmethode aufzurufen (Instanz -> Objekt)
                // brauchen wir ein Objekt von der Klasse (wo die Methode steht)

                // --> hashTabelle[0] enthält ein Objekt von der Klasse Liste
                // hashTabelle[0] oder hashTabelle[1] oder hashTabelle[2] ....
                // hauptsache ist eine Objekt von der Klasse Liste, damit wier die Methode
                // hashFunktion aufrufen können
                int h = hashTabelle[0].hashFunktion(dialogEingabe, B);
                // h = 0, (Wenn Eingabe ist A)

                // diese Variable enthält die Position (Korb)  (d.h. -1 oder gültige Position)
                int position = hashTabelle[h].suche(dialogEingabe);
                // x == 0 ? 1 : 0
                // dumm-if
                // if(x==0) then 1 else 0
                //ausgabe = position >= 0 ? "Element gefunden in Bucket " + h + ", die Nummer des Korbes : " + ( hashTabelle[h].laenge() -(position+1)) : "Element nicht gefunden";
                if(position != -1){
                    /*
                   Korb : k
                              k1     k2     k3
                   Bucket 0: [aa] -> [a] -> [A]

                   Bucket 1: []
                   Bucket 2: []
                   Bucket 3: []
                   Bucket 4: []
                   */
                    ausgabe = "Element gefunden in Bucket " + h + ", die Nummer des Korbes : " + position;
                }else{
                    ausgabe = "Element nicht gefunden";
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("3")) {

                /*
                Ausnahmen:
                löschebn --> ist nicht gültig
                1- wenn bucket leer!
                2- wenn Element nicht gefunden!
                 */
                dialogEingabe = JOptionPane.showInputDialog("Welches Element soll geloescht werden?");

                int h = hashTabelle[0].hashFunktion(dialogEingabe, B);
                // h enthält die Nummer des Bucket
                int position = hashTabelle[h].suche(dialogEingabe);
                // wenn bucket leer!
                if(hashTabelle[h].laenge() == 0){
                    throw new IllegalArgumentException("Bucket ist leer!!");
                }
                if(position == -1){
                    throw new NoSuchElementException("Element existiert nicht");
                }

                String loeschendeElement = hashTabelle[h].inhalt(position);
                // hashTabelle[h] --> d.h. im Bucket (h) passiert das Löschen
                hashTabelle[h].loescheElem(dialogEingabe);
                ausgabe = "Element in Bucket "+ h + " --> " + loeschendeElement + " gelöscht!";
                JOptionPane.showMessageDialog(null, ausgabe);
                //...
            }

            if (menueeingabe.equals("4")) {
                dialogEingabe = JOptionPane.showInputDialog("Von welchem Korb soll die Laenge angezeigt werden?");
                //ausgabe = "";
                int k = Integer.parseInt(dialogEingabe);
                int l = hashTabelle[k].laenge();
                ausgabe = "Liste hat Laenge " + l;
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("5")) {
                //...
                dialogEingabe = JOptionPane.showInputDialog("Welcher Bucket soll gezeigt werden?");
                int bucket = Integer.parseInt(dialogEingabe);
                // bucket enthält die eingelesene Nummer des Bucket
                /*
                Ausnahmen:
                1- nicht existerte Nummer eines Bucket
                2- wenn Bucket leer!!
                 */

                // nicht existerte Nummer eines Bucket (ungültige Nummer eines Bucket)
                if(bucket < 0 || bucket > B){
                    throw new IllegalArgumentException("In der Hashtabelle stehen nur Buckets von 0 bis 4!!");
                }
                // wenn bucket leer!
                if(hashTabelle[bucket].laenge() == 0){
                    throw new IllegalArgumentException("Bucket ist leer!!");
                }
                /*
                   Bucket 0: [aa] -> [a] -> [A]
                   Bucket 1: []
                   Bucket 2: []
                   Bucket 3: []
                   Bucket 4: []
                 */
                ausgabe = hashTabelle[bucket].zeigeAlleElementeInBucket(bucket);
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("6")) {
                /*
                   Bucket 0: [aa] -> [a] -> [A]
                   Bucket 1: []
                   Bucket 2: []
                   Bucket 3: []
                   Bucket 4: [e] -> [E]
                 */

                ausgabe = "Anzahl der Elemente in jedem Bucket in der Hashtabelle:\n";
                /*
                ohne Methode in der Klasse Liste zu schreiben
                for(int i = 0; i < B; i++){
                    ausgabe += "Bucket " + i + " hat " + hashTabelle[i].laenge() + " Elemente\n";
                }
                 */


                // Mit Methode
                for(int i = 0; i < B; i++){
                    ausgabe += hashTabelle[i].listenLaengen(i);
                }

                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("7")) {
                dialogEingabe = JOptionPane.showInputDialog("Anzahl?");
                ausgabe = "";
                int zahl = Integer.parseInt(dialogEingabe);
                for (int j = 1; j <= zahl; j++) {
                    String s = "";
                    // (int)(Math.random() * range) + min;
                    // Rückgabe liegt zwischen von min bis (min+range-1)
                    // Länge von jeder String zwischen 3 und (3+8-1)
                    // Länge von jeder String zwischen 3 und 10
                    int laenge = (int) ((Math.random() * 8) + 3);

                    // große Buchstaben
                    for (int k = 1; k <= laenge; k++) {
                        char zufall = (char) ((Math.random() * 26) + 65);
                        // ((Math.random() * 26) + 65) --> zufäliger wert zwischen 65 und 90
                        // dann zu char umwandeln
                        s += zufall;
                    }

                    int h = hashTabelle[0].hashFunktion(s, B); //Welcher Korb?
                    hashTabelle[h].einsetzenAnfang(s);
                }
            }

        }
    }
}


