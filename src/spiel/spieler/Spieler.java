package spiel.spieler;

import spiel.farbe.FarbEnum;

public class Spieler {

    private final String name;
    private final FarbEnum farbe;

    public Spieler(String name, FarbEnum farbe) {
        this.name = name;
        this.farbe = farbe;
    }

    public String getName() {
        return name;
    }

    public FarbEnum getFarbe() {
        return farbe;
    }
}
