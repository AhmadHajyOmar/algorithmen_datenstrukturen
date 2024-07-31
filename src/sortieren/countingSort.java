package sortieren;

public class countingSort {
    /*
    Implementieren Sie CountingSort effizient, d.h. in O(n + range) wobei
    range (den Bereich spezifiziert aus dem die Eingabe stammt.
    Sie können annehmen, dass die Eingabe m[] aus chars besteht.
    Ihr CountingSort sollte allerdings in der
    Lage sein, das Universum aus dem eine konkrete Eingabe m kommt, speichereffizient
    auf das CountsArray abzubilden: Letzteres Array sollte nur Einträge für Chars
    aus der Bandbreite range der Chars
    der Eingabe m, d.h. für Chars mit ASCI-Codes von min(m) bis max(m) besitzen.
    Testen Sie Ihre Implementation für die Eingabe
    char[] m1=['z', 'm', 'm', 'm', 'z', 's', 's', 's', 'b', 'c', 'f', 'd'];
    Hinweis: Benutzen Sie gerne, dass nur Kleinbuchstaben als chars verwendet werden, und dass diese
    mit dem kleinen a bei ASCII-CODE 97 beginnen und mit dem kleinen z mit ASCII-Code 122 enden.
     */
    public static void countingSort(char[] a){
        int min = a[0];
        int max = a[0];
        // ['c', 'b', 'b', 'c', 'g', 'd'] --> a
        // range --> von 'b' bis 'g'
        // --> range = 25
        for(int i = 0; i < a.length; i++){
            if(a[i] < min){ // if(a[1] < min) --> if('m' < 'z') --> if(Wert von m < wert von z)
                min = a[i];
            }
            if(a[i] > max){
                max = a[i];
            }
        }

        /*
        oder
        for(char zeichen : a){
           if(zeichen < min){ // if(a[1] < min) --> if('m' < 'z') --> if(Wert von m < wert von z)
                min = zeichen;
            }
            if(zeichen > max){
                max = zeichen;
            }
        }
         */
        //  ['c', 'b', 'b', 'c', 'g', 'd'] --> range von 'b' --> 'g'
        // range = 103 - 98 + 1 = 6
        int range = max - min + 1;

        int[] counts = new int[range];
        // [ 0, 0, 0, 0, 0, 0]
        //   'b'   'c'   'd'   'e'   'f'   'g'

        // [ 2, 2, 1, 0, 0, 1]

        // zählen
        // ['c', 'b', 'b', 'c', 'g', 'd']
        for(int i = 0; i < a.length; i++){
            counts[a[i] - min]++;
        }
        /*
        i = 0:
                counts[a[i]]++ --> counts[a[0]]++ -- > counts['c']++ --> counts[99]++ Falsch
                                                                     --> counts[1]++ Richtig
                                                  -- > counts['e']++ --> counts[101]++ Falsch
                                                                     --> counts[3]++ Richtig

                    99 --> min = 1
                   101 --> min = 3
         */

        // Aufsteigend
        int j = 0;
        for(int i = 0; i < range; i++){
            while (counts[i] > 0){
                a[j++] = (char) (i + min);
                counts[i]--;
            }
        }

        /*
        // Absteigend
        int j = 0;
        for(int i = range-1; i >= 0; i--){
            while (counts[i] > 0){
                a[j++] = (char) (i + min);
                counts[i]--;
            }
        }
         */
        // [ 2, 2, 1, 0, 0, 1] --> counts
        // ['c', 'b', 'b', 'c', 'g', 'd'] --> a
        /*
        j = 0
              AS: i = 0
                         counts[i] > 0 --> counts[0] > 0 --> 2 > 0 ? T
                         a[j++] = (char) (i + min) --> a[0] = (char) (0 + 98)
                         --> a[0] = 'b'
                         ['b', 'b', 'b', 'c', 'g', 'd']
                         j = 1
                         counts[i]-- --> counts[0] = 1

                         counts[i] > 0 --> counts[0] > 0 --> 1 > 0 ? T
                         a[j++] = (char) (i + min) --> a[1] = (char) (0 + 98)
                         --> a[1] = 'b'
                         ['b', 'b', 'b', 'c', 'g', 'd']
                         j = 2
                         counts[i]-- --> counts[0] = 0

                         counts[i] > 0 --> counts[0] > 0 --> 0 > 0 ? F

               As: i = 1
                         counts[i] > 0 --> counts[1] > 0 --> 2 > 0 ? T
                         a[j++] = (char) (i + min) --> a[2] = (char) (1 + 98)
                         --> a[2] = 'c'
                         ['b', 'b', 'c', 'c', 'g', 'd']
                         j = 3
                         counts[i]-- --> counts[1] = 1


                         counts[i] > 0 --> counts[1] > 0 --> 1 > 0 ? T
                         a[j++] = (char) (i + min) --> a[3] = (char) (1 + 98)
                         --> a[3] = 'c'
                         ['b', 'b', 'c', 'c', 'g', 'd']
                         j = 4
                         counts[i]-- --> counts[1] = 0

                         counts[i] > 0 --> counts[1] > 0 --> 0 > 0 ? F


               As: i = 2
                         counts[i] > 0 --> counts[2] > 0 --> 1 > 0 ? T
                         a[j++] = (char) (i + min) --> a[4] = (char) (2 + 98)
                         --> a[4] = 'd'
                         ['b', 'b', 'c', 'c', 'd', 'd']
                         j = 5
                         counts[i]-- --> counts[2] = 0

                         counts[i] > 0 --> counts[2] > 0 --> 0 > 0 ? F

                As: i = 3
                         counts[i] > 0 --> counts[3] > 0 --> 0 > 0 ? F

                As: i = 4
                         counts[i] > 0 --> counts[4] > 0 --> 0 > 0 ? F

                As: i = 5
                         counts[i] > 0 --> counts[5] > 0 --> 1 > 0 ? T
                          a[j++] = (char) (i + min) --> a[5] = (char) (5 + 98)
                         --> a[5] = 'g'
                         ['b', 'b', 'c', 'c', 'd', 'g']
                         j = 6
                         counts[i]-- --> counts[5] = 0

                         counts[i] > 0 --> counts[5] > 0 --> 0 > 0 ? F






         */

        // Ausgabe: nur falls wir das sorrtierte Array innerhaöb der Methode ausgeben wollen
        // Methode ist mit void geschrieben
        // Ansonsten --> gibt die Methode das sorrtierte Array zurück -->
        //  public static char[] sortieren.countingSort(char[] a){
        // .
        // .
        // return a;
        // }
        // Dann Ausgabe innerhalb der Main-Methode
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]);
            if(i != a.length-1){
                System.out.print(", ");
            }
        }



    }

    public static void main(String[] args){
        char[] arr = {'z', 'm', 'm', 'm', 'z', 's', 's', 's', 'b', 'c', 'f', 'd'};
        countingSort(arr);
    }
}
