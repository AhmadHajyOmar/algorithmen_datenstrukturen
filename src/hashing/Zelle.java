package hashing;

public class Zelle {
    String inhalt; // Beachten Sie: inhalt ist sowohl der Schluesselwert, als auch der komplette zugehörige Dateneintrag
    Zelle next;

    // Konstruktor:
    Zelle (String el) {
        inhalt = el;
        next = null;
    }
    Zelle (String el, Zelle z) {
        inhalt = el;
        next = z;
    }
}
