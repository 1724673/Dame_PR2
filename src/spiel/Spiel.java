package spiel;

import spiel.farbe.FarbEnum;
import spiel.historie.Historie;
import spiel.spielbrett.Spielbrett;
import spiel.spielbrett.spielfeld.Spielfeld;
import spiel.spieler.Spieler;

import java.util.ArrayList;
import java.util.List;

public class Spiel {

    private Spieler spielerAmZug;
    private Spielbrett spielbrett;
    private Spieler[] spielerListe = new Spieler[2];
    private Historie historie;

    public Spiel() {
        this.spielbrett = new Spielbrett();
    }

    public void addSpieler(String spielerName, FarbEnum farbe) {
        if (farbe == FarbEnum.SCHWARZ) this.spielerListe[1] = new Spieler(spielerName, farbe);
        else this.spielerListe[0] = new Spieler(spielerName, farbe);
    }

    public String getSpielerAmZug() {
        return this.spielerAmZug.getName();
    }

    public Spieler[] getSpielerListe() {
        return spielerListe;
    }

    public void starte() {
        if (spielerListe[0] == null || spielerListe[1] == null) return;
        this.spielerAmZug = this.spielerListe[1]; // 0 = WEISS, 1 = SCHWARZ
    }

    public boolean hatGewonnen(String spielerName) {
        return false;
    }

    public void ziehe(String[] felder) {

    }

    public String[] getErlaubteZuege() {

        FarbEnum farbeAmZug = this.spielerAmZug.getFarbe();

        return getErlaubteZuegeLokal(farbeAmZug).toArray(String[]::new);
    }

    private List<String> getErlaubteZuegeLokal(FarbEnum farbeAmZug) {

        if (farbeAmZug == FarbEnum.WEISS) {
            return getWeisseZugmoeglichkeiten(this.spielbrett);
        } else return getSchwarzeZugmoeglichkeiten(this.spielbrett);

    }

    private List<String> getSchwarzeZugmoeglichkeiten(Spielbrett aktuellesSpielbrett) {

        List<String> zugmoeglichkeiten = new ArrayList<>();
        Spielfeld[][] aktuelleSpielfelder = aktuellesSpielbrett.getSpielfelder();

        // Bauern ziehen immer nur vorwärts und diagonal -> schwarz steht immer unten -> verringere Reihe

        for (int reihe = 0; reihe < 8; reihe++) {
            for (int spalte = 0; spalte < 8; spalte++) {
                if (null != aktuelleSpielfelder[reihe][spalte].getSpielfigur()
                        && aktuelleSpielfelder[reihe][spalte].getSpielfigur().getFarbe() == FarbEnum.SCHWARZ &&
                        !aktuelleSpielfelder[reihe][spalte].getSpielfigur().isIstDame()) {
                    // pruefe Nachbarn von lokaler X Position x+1 und y-1 && y+1
                    if (reihe < 8) {

                        String vonId = aktuelleSpielfelder[reihe][spalte].getId() + "[" +
                                aktuelleSpielfelder[reihe][spalte].getSpielfigur().getFarbe() + "]";

                        if (spalte == 0) {
                            if (null == aktuelleSpielfelder[reihe + 1][spalte + 1].getSpielfigur())
                                zugmoeglichkeiten.add(vonId + " nach " + aktuelleSpielfelder[reihe + 1][spalte + 1].getId());
                        } else if (spalte == 7) {
                            if (null == aktuelleSpielfelder[reihe + 1][spalte - 1].getSpielfigur())
                                zugmoeglichkeiten.add(vonId + " nach " + aktuelleSpielfelder[reihe + 1][spalte - 1].getId());
                        } else {
                            if (null == aktuelleSpielfelder[reihe + 1][spalte + 1].getSpielfigur())
                                zugmoeglichkeiten.add(vonId + " nach " + aktuelleSpielfelder[reihe + 1][spalte + 1].getId());
                            if (null == aktuelleSpielfelder[reihe + 1][spalte - 1].getSpielfigur())
                                zugmoeglichkeiten.add(vonId + " nach " + aktuelleSpielfelder[reihe + 1][spalte - 1].getId());
                        }
                    }
                }
            }
        }

        return zugmoeglichkeiten;

    }

    private List<String> getWeisseZugmoeglichkeiten(Spielbrett aktuellesSpielbrett) {

        List<String> zugmoeglichkeiten = new ArrayList<>();
        Spielfeld[][] aktuelleSpielfelder = aktuellesSpielbrett.getSpielfelder();

        // Bauern ziehen immer nur vorwärts und diagonal -> weiss steht immer unten -> erhöhe Reihe

        for (int reihe = 0; reihe < 8; reihe++) {
            for (int spalte = 0; spalte < 8; spalte++) {
                if (null != aktuelleSpielfelder[reihe][spalte].getSpielfigur()
                        && aktuelleSpielfelder[reihe][spalte].getSpielfigur().getFarbe() == FarbEnum.WEISS &&
                        !aktuelleSpielfelder[reihe][spalte].getSpielfigur().isIstDame()) {
                    // pruefe Nachbarn von lokaler X Position x+1 und y-1 && y+1
                    if (reihe > 0) {

                        String vonId = aktuelleSpielfelder[reihe][spalte].getId() + "[" +
                                aktuelleSpielfelder[reihe][spalte].getSpielfigur().getFarbe() + "]";

                        if (spalte == 0) {
                            if (null == aktuelleSpielfelder[reihe - 1][spalte + 1].getSpielfigur())
                                zugmoeglichkeiten.add(vonId + " nach " + aktuelleSpielfelder[reihe - 1][spalte + 1].getId());
                        } else if (spalte == 7) {
                            if (null == aktuelleSpielfelder[reihe - 1][spalte - 1].getSpielfigur())
                                zugmoeglichkeiten.add(vonId + " nach " + aktuelleSpielfelder[reihe - 1][spalte - 1].getId());
                        } else {
                            if (null == aktuelleSpielfelder[reihe - 1][spalte + 1].getSpielfigur())
                                zugmoeglichkeiten.add(vonId + " nach " + aktuelleSpielfelder[reihe - 1][spalte + 1].getId());
                            if (null == aktuelleSpielfelder[reihe - 1][spalte - 1].getSpielfigur())
                                zugmoeglichkeiten.add(vonId + " nach " + aktuelleSpielfelder[reihe - 1][spalte - 1].getId());
                        }
                    }
                }
            }
        }

        return zugmoeglichkeiten;

    }

    public String getHistorie() {
        return null;
    }

    public String getStatus() {
        return null;
    }
}
