package hashing;

import java.util.ArrayList;

public class ListeOpen {
    Zelle zelle;
    Zelle[] zellen;

    public ListeOpen(int b) {
        zellen = new Zelle[b];
    }

    String einfuegen(String wert){
        String ausgabe = "";
        int h = hashFunktion(wert, zellen.length); //Welcher Korb?
        ArrayList<Integer> freierPlaetze = new ArrayList<>();
        boolean existsInHashtable = false;
        for (int j = 0; j < zellen.length; j++) {
            if (zellen[j] == null){
                freierPlaetze.add(j);
            }else{
                if(zellen[j].inhalt.equals(wert)) {
                    existsInHashtable = true;
                    ausgabe = "Element in Bucket " + j + " gefunden, daher nicht eingefuegt";
                }
            }
        }

        if(!existsInHashtable){
            if (freierPlaetze.size() > 0) {
                if(freierPlaetze.contains(h)){
                    zellen[h] = new Zelle(wert);
                    ausgabe = "Eingabe eingefuegt in bucket " + h;
                }else{
                    int neuer_h = -1;
                    for(int i = 0; i < freierPlaetze.size(); i++){
                        if(freierPlaetze.get(i) > h){
                            neuer_h = freierPlaetze.get(i);
                            break;
                        }
                    }
                    if(neuer_h != -1){
                        zellen[neuer_h] = new Zelle(wert);
                    }else{
                        zellen[freierPlaetze.get(0)] = new Zelle(wert);
                    }
                    ausgabe = "Eingabe eingefuegt in bucket " + neuer_h;
                }
            }else{
                ausgabe = "Alle Buckets sind voll!";
            }
        }
        return ausgabe;
    }



    void loeschenn(){
        this.zelle = null;
    }

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

    int suche(String dialogEingabe) {
        for (int j = 0; j < zellen.length; j++) {
            if (zellen[j] != null){
                if(zellen[j].inhalt.equals(dialogEingabe)) {
                   return j;
                }
            }
        }
        return -1;
    }

    void loescheElement(int bucket){
        zellen[bucket] = null;
    }

    String elementeImBucketsZeigen(){
        String ausgabe = "Elemente : \n";
        for (int j = 0; j < zellen.length; j++) {
            ausgabe += "Bucket Nr: "+ j + " " + (zellen[j] != null ? "hat das Element " + zellen[j].inhalt: "ist leer.");
            ausgabe += "\n";
        }
        return ausgabe;
    }

}
