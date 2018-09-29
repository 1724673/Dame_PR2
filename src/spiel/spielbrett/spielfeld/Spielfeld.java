package spiel.spielbrett.spielfeld;

import spiel.farbe.FarbEnum;
import spiel.spielbrett.spielfeld.spielfigur.Spielfigur;

public class Spielfeld {

    private final String id;
    private final FarbEnum farbe;
    private Spielfigur spielfigur;

    public Spielfeld(String id, FarbEnum farbe) {
        this.id = id;
        this.farbe = farbe;
        this.spielfigur = null;
    }

    public Spielfigur getSpielfigur() {
        return spielfigur;
    }

    public void setSpielfigur(Spielfigur spielfigur) {
        this.spielfigur = spielfigur;
    }

    public String getId() {
        return id;
    }

    public String getFarbe() {
        return this.farbe.toString();
    }
}
