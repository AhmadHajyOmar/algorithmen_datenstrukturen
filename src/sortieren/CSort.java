package sortieren;

public class CSort {

    // [2,5,3,0,2,3,0,3]
    // Range = max - min + 1

    // 10 , 5 --> 5
    // min = 5 , max = 10
    // 5, 6, 7, 8, 9, 10 = 6 Zahlen
    public static int[] countingSort(int[] a){
        int min = a[0];
        int max = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] < min){
                min = a[i];
            }
            if(a[i] > max){
                max = a[i];
            }
        }
        //  [2,5,3,0,-2,3,0,3]
        // zwischen -2 und 5 --> 8
        int range = max - min + 1;

        // Häufigkeiten
        // [2, 0, 2, 3, 0, 1] // Range = 6
        //  0  1  2  3  4  5  --> dies sind kein Index (sondern Zahlen referenz)
        // d.h.: die Zahl 0 kommt 2 mal im array vor.
        int[] counts = new int[range];
        // [ 0, 0, 0, 0, 0, 0] --> Array enthält intiale Werte

        // unnötig #amani
        for(int i = 0; i < counts.length; i++){
            counts[i] = 0;
        }
        // [ 0, 0, 0, 0, 0, 0, 0 , 0]

        // zählen (counting)
        // [2,5,3,0,-2,3,0,3]
        for(int i = 0; i < a.length; i++){
            counts[a[i] - min]++;
        }
        // [0, 0, 0, 0, 1, 0, 0, 0]
        // -2  -1 0  1  2  3  4  5

        /*
        a : [2,5,3,0,2,3,0,3]
        i = 0:
               counts[a[i]]++ -->  counts[a[0]]++ --> counts[2]++;
               // [ 0, 0, 1, 0, 0, 0] --> counts
               //   0  1  2  3  4  5
        i = 1:
               counts[a[i]]++ -->  counts[a[1]]++ --> counts[5]++;
               // [ 0, 0, 1, 0, 0, 1] --> counts
               //   0  1  2  3  4  5
        i = 2:
               counts[a[i]]++ -->  counts[a[2]]++ --> counts[3]++;
               // [ 0, 0, 1, 1, 0, 1] --> counts
               //   0  1  2  3  4  5
        i = 3:
               counts[a[i]]++ -->  counts[a[3]]++ --> counts[0]++;
               // [ 1, 0, 1, 1, 0, 1] --> counts
               //   0  1  2  3  4  5
        i = 4:
               counts[a[i]]++ -->  counts[a[4]]++ --> counts[2]++;
               // [ 1, 0, 2, 1, 0, 1] --> counts
               //   0  1  2  3  4  5
         */

        // counts-ergebnis : [2, 0, 2, 3, 0, 1]
        //Teilsummen (beleib unveränder t für positive und neagtive Zahlen
        // counts-ergebnis : für negative Zahlen :  [1, 0, 2, 0, 1, 3, 0, 1] --> a [2,5,3,0,-2,3,0,3]
        // [1, 0, 2, 0, 1, 3, 0, 1] --> [1, 1, 3, 3, 4, 7, 7, 8]


        /*
        for(int j = 1; j < range; j++){
            counts[j] += counts[j-1];
        }
        */


        /*
        j = 1:
             counts[j] += counts[j-1] += counts[1-1] += counts[0]
             counts[1] += counts[0] --> counts[1]
             --> [2, 2, 2, 3, 0, 1]
             --> summe i + (i-1) -> Ergebnis in i

         */

        // Ergebnis : [2, 0, 2, 3, 0, 1] j = 1 --> counts[1] + counts[0] --> ergebnis in counts[1]
        // Ergebnis : [2, 2, 2, 3, 0, 1] j = 2 --> counts[2] + counts[1] --> ergebnis in counts[2]
        // Ergebnis : [2, 2, 4, 3, 0, 1] j = 3
        // Ergebnis : [2, 2, 4, 7, 0, 1] j = 4
        // Ergebnis : [2, 2, 4, 7, 7, 1] j = 5
        // Ergebnis : [2, 2, 4, 7, 7, 8] j = 6

        // sortieren
        // [1, 1, 3, 3, 4, 7, 7, 8]
        // a [2,5,3,0,-2,3,0,3]


        /*
        int j = 0;
        for(int i = 0; i < range; i++) {
            while (j < counts[i])
                a[j++] = i + min;
        }
        */


        // Aufsteigend
        int j = 0;
        for (int i = 0; i < range; i++) {
            while (counts[i] > 0) {
                a[j++] = i + min;
                counts[i]--;
            }
        }

        /*
        Absteigend:
        int j = 0;
        for (int i = range - 1; i >= 0; i--) {
            while (counts[i] > 0) {
                a[j++] = i + min;
                counts[i]--;
            }
        }
         */


        // [3, 2, 4, 3, 1]
        // [0, 0, 0, 0] --> [1, 1, 2, 1]
        // Teil: [1, 2, 4, 5]
        /*
        j = 0
            i = 0
                   j < counts[i] --> 0 < 1? T
                   a[0] = 0
         */

        // AS-> äußere Schleife
        // IS-> innere Schleife
        // [1, 1, 3, 3, 4, 7, 7, 8] --> counts
        // a [2,5,3,0,-2,3,0,3]
        /*
        j = 0
            AS: i = 0
                 IS: j < counts[i] --> 0 < 1 ? T
                     a[j++] = i -->  a[0] = 0
                     [0, 5, 3, 0, -2, 3, 0, 3]
                     j = 1

                     j < counts[i] --> 1 < 2 ? T
                     a[j++] = i -->  a[1] = 0
                     [0, 0, 3, 0, 2, 3, 0, 3]
                     j = 2

                     j < counts[i] --> 2 < 2 ? F
           AS: i = 1
                 IS: j < counts[i] ---> 2 < 2 ? F
           AS: i = 2
                 IS: j < counts[i] --> 2 < 4 ? T
                     a[j++] = i -->  a[2] = 2
                     [0, 0, 2, 0, 2, 3, 0, 3]
                     j = 3

                     j < counts[i] --> 3 < 4 ? T
                     a[j++] = i -->  a[3] = 2
                     [0, 0, 2, 2, 2, 3, 0, 3]
                     j = 4


         */




            /*
            oder
            a[j] = i;
            j++;

             */

        // AS-> äußere Schleife
        // IS-> innere Schleife
        // [2, 2, 4, 7, 7, 8] --> counts
        /*
        j = 0
            AS: i = 0
                 IS: j < counts[i] --> 0 < 2 ? T
                     a[j++] = i -->  a[0] = 0
                     [0, 5, 3, 0, 2, 3, 0, 3]
                     j = 1

                     j < counts[i] --> 1 < 2 ? T
                     a[j++] = i -->  a[1] = 0
                     [0, 0, 3, 0, 2, 3, 0, 3]
                     j = 2

                     j < counts[i] --> 2 < 2 ? F
           AS: i = 1
                 IS: j < counts[i] ---> 2 < 2 ? F
           AS: i = 2
                 IS: j < counts[i] --> 2 < 4 ? T
                     a[j++] = i -->  a[2] = 2
                     [0, 0, 2, 0, 2, 3, 0, 3]
                     j = 3

                     j < counts[i] --> 3 < 4 ? T
                     a[j++] = i -->  a[3] = 2
                     [0, 0, 2, 2, 2, 3, 0, 3]
                     j = 4


         */

        return a;


    }

    public static void main(String[] args){
        int[] a = {0, 3, 2, 4, 3, 1};
        a = countingSort(a);
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]);
            if(i != a.length-1){
                System.out.print(", ");
            }
        }
    }
}
