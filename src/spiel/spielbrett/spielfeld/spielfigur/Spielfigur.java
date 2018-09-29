package spiel.spielbrett.spielfeld.spielfigur;

import spiel.farbe.FarbEnum;

public class Spielfigur {

    private final FarbEnum farbe;
    private boolean istDame;

    public Spielfigur(FarbEnum farbe, boolean istDame) {
        this.farbe = farbe;
        this.istDame = istDame;
    }

    public FarbEnum getFarbe() {
        return farbe;
    }

    public boolean isIstDame() {
        return istDame;
    }
}
