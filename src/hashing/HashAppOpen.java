package hashing;

import javax.swing.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashAppOpen {
    public static void main(String[] args){
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
        ListeOpen hashTabelle = new ListeOpen (B);




        while (true) {

            String menueeingabe = JOptionPane.showInputDialog(menue);
            if (menueeingabe == null)
                break;
            String dialogEingabe;

            if (menueeingabe.equals("1")) {
                dialogEingabe = JOptionPane.showInputDialog("Datenstring?");
                ausgabe = hashTabelle.einfuegen(dialogEingabe);
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("2")) {
                dialogEingabe = JOptionPane.showInputDialog("Datenstring?");
                int bucketNummer = hashTabelle.suche(dialogEingabe);
                if(bucketNummer == -1){
                    ausgabe = "Element existiert nicht!!";
                }else{
                    ausgabe = "Element gefunden in Bucket " + bucketNummer;
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("3")) {
                dialogEingabe = JOptionPane.showInputDialog("Datenstring?");
                int bucketNummer = hashTabelle.suche(dialogEingabe);
                if(bucketNummer == -1){
                    ausgabe = "Element existiert nicht!!";
                }else{
                    String zuloeschendeElement = hashTabelle.zellen[bucketNummer].inhalt;
                    hashTabelle.loescheElement(bucketNummer);
                    ausgabe = "Element in Bucket "+ bucketNummer+ " --> " + zuloeschendeElement + " gelöscht!";
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }


            if (menueeingabe.equals("4")) {
                dialogEingabe = JOptionPane.showInputDialog("Von welchem Bucket soll die Laenge angezeigt werden?");
                int k = Integer.parseInt(dialogEingabe);
                //int l = hashTabelle.zellen[k];
                //ausgabe = "Liste hat Laenge " + l;
                // JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("5")) {
                dialogEingabe = JOptionPane.showInputDialog("Von welchem Bucket soll die Laenge angezeigt werden?");
                int bucketNummer = hashTabelle.suche(dialogEingabe);
                if(bucketNummer == -1){
                    ausgabe = "Element existiert nicht!!";
                }else{
                    ausgabe = "im Bucket " + bucketNummer + " existiert das Element " + hashTabelle.zellen[bucketNummer].inhalt;
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("6")) {
                ausgabe = hashTabelle.elementeImBucketsZeigen();
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("7")) {
                dialogEingabe = JOptionPane.showInputDialog("Anzahl?");
                int zahl = Integer.parseInt(dialogEingabe);
                if(zahl > B){
                    ausgabe = "Es gibt leider nur " + B + " Buckets!";
                    JOptionPane.showMessageDialog(null, ausgabe);
                }else{
                    for (int j = 1; j <= zahl; j++) {
                        String s = "";
                        int laenge = (int) ((Math.random() * 8) + 3);

                        for (int k = 1; k <= laenge; k++) {
                            char zufall = (char) ((Math.random() * 26) + 65);
                            s += zufall;
                        }

                        hashTabelle.einfuegen(s);
                    }
                }


            }

        }
    }
}
