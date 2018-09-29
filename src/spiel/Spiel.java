package spiel;

import spiel.farbe.FarbEnum;
import spiel.historie.Historie;
import spiel.spielbrett.Spielbrett;
import spiel.spieler.Spieler;

import java.util.ArrayList;
import java.util.List;

public class Spiel {

    private Spieler spielerAmZug;
    private Spielbrett spielbrett;
    private List<Spieler> spielerListe = new ArrayList<>();
    private Historie historie;

    public void addSpieler(String spielerName, FarbEnum farbe) {
        if (this.spielerListe.size() > 2) return;
        this.spielerListe.add(new Spieler(spielerName, farbe));
    }

    public String getSpielerAmZug() {
        return this.spielerAmZug.getName();
    }

    public void starte() {

    }

    public boolean hatGewonnen(String spielerName) {
        return false;
    }

    public void ziehe(String[] felder) {
    }

    public String[] getErlaubteZuege() {
        return null;
    }

    public String getHistorie() {
        return null;
    }

    public String getStatus() {
        return null;
    }
}
