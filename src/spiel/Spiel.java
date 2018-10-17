package spiel;

import spiel.farbe.FarbEnum;
import spiel.historie.Historie;
import spiel.spielbrett.Spielbrett;
import spiel.spieler.Spieler;
import spiel.utilities.Helfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spiel {

    private Spieler spielerAmZug;
    private Spielbrett spielbrett;
    private Spieler[] spielerListe = new Spieler[2];
    private Historie historie;
    private boolean istSpielGestartet;
    private boolean istSpielZuEnde;
    private List<String> aktuellMoeglicheZuege;

    public Spiel() {
        this.spielbrett = new Spielbrett();
        this.istSpielGestartet = false;
        this.istSpielZuEnde = false;
    }

    public Spielbrett getSpielbrett() {
        return spielbrett;
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
        this.spielerAmZug = this.spielerListe[0]; // 0 = WEISS, 1 = SCHWARZ
        this.spielbrett.intialisiereSpielfiguren();
    }

    public boolean hatGewonnen(String spielerName) {
        return false;
    }

    public void ziehe(String[] felder) {

    }


    public String[] getAlleZugmöglichkeiten() {
        Map<String, List<String>> map = new HashMap<>();
        for (int x = 0; x < 8; x++) {
            for (int y = ((7 - x) % 2); y < 8; y = y + 2) {
                if ((this.spielbrett.getSpielfelder()[x][y].getSpielfigur() != null)
                        && this.spielbrett.getSpielfelder()[x][y].getSpielfigur().getFarbe() == this.spielerAmZug.getFarbe()) {
                    List<String> out = new ArrayList<>();
                    out.addAll(this.getZugmöglichkeitenFuerX(x, y,
                            this.spielbrett.getSpielfelder()[x][y].getSpielfigur().isIstDame()));
                    if (out.size() != 0) {
                        map.put(x + " " + y, out);
                    }
                }
            }
        }

        List<String> formatierteRückgabeListe = new ArrayList<>();

        for (String k : map.keySet()) {
            for (String v : map.get(k)) {
                String reiheVon = Helfer.convertReiheInBuchstabe(Integer.parseInt(k.split(" ")[0]));
                int spalteVon = Integer.parseInt(k.split(" ")[1]) + 1;
                String reiheNach = Helfer.convertReiheInBuchstabe(Integer.parseInt(v.split(" ")[0]));
                int spalteNach = Integer.parseInt(v.split(" ")[1]) + 1;

                formatierteRückgabeListe.add("<" + reiheVon + spalteVon + "-" + reiheNach + spalteNach + ">");

            }
        }

        this.aktuellMoeglicheZuege = formatierteRückgabeListe;

        String[] erlaubteZuege = formatierteRückgabeListe.toArray(new String[0]);
        return erlaubteZuege;
    }

    public List<String> getZugmöglichkeitenFuerX(int x, int y, boolean istDame) {
        List<String> out = new ArrayList<>();
        int vorne = 1;
        if (this.spielerAmZug.getFarbe() == FarbEnum.WEISS) {
            vorne = -1;
        }
        if (this.spielbrett.getSpielfelder()[x][y].getSpielfigur() != null) {
            if (x + vorne >= 0 && x + vorne <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                if (this.spielbrett.getSpielfelder()[x + vorne][y + 1].getSpielfigur() == null) {
                    out.add((x + vorne) + " " + (y + 1));
                }
            }
            if (x + vorne >= 0 && x + vorne <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                if (this.spielbrett.getSpielfelder()[x + vorne][y - 1].getSpielfigur() == null) {
                    out.add((x + vorne) + " " + (y - 1));
                }
            }
        }
        if (x + 2 * vorne >= 0 && x + 2 * vorne <= 7 && y + 2 >= 0 && y + 2 <= 7) {
            if ((this.spielbrett.getSpielfelder()[x + vorne][y + 1].getSpielfigur() != null) &&
                    (!this.spielbrett.getSpielfelder()[x + vorne][y + 1].getSpielfigur().getFarbe().equals(this.spielerAmZug.getFarbe())) &&
                    (this.spielbrett.getSpielfelder()[x + (2 * vorne)][y + 2].getSpielfigur() == null)) {
                List<String> toAdd = new ArrayList<>();
                toAdd = this.getZugmöglichkeitenFuerX((x + 2 * vorne), (y + 2), false);
                if (toAdd.size() == 0) {
                    out.add((x + 2 * vorne) + " " + (y + 2));
                } else {
                    out.addAll(toAdd);
                }
            }
        }
        if (x + 2 * vorne >= 0 && x + 2 * vorne <= 7 && y - 2 >= 0 && y - 2 <= 7) {
            if ((this.spielbrett.getSpielfelder()[x + vorne][y - 1].getSpielfigur() != null) &&
                    (!this.spielbrett.getSpielfelder()[x + vorne][y - 1].getSpielfigur().getFarbe().equals(this.spielerAmZug.getFarbe())) &&
                    (this.spielbrett.getSpielfelder()[x + (2 * vorne)][y - 2].getSpielfigur() == null)) {
                List<String> toAdd = new ArrayList<>();
                toAdd = this.getZugmöglichkeitenFuerX((x + 2 * vorne), (y - 2), false);
                if (toAdd.size() == 0) {
                    out.add((x + 2 * vorne) + " " + (y - 2));
                } else {
                    out.addAll(toAdd);
                }
            }
        }
        return out;
    }

    public boolean getSpielGestartet() {
        return this.istSpielGestartet;
    }

    public boolean getIstSpielZuEde() {
        return this.istSpielZuEnde;
    }

    public int getAnzahlSpieler() {
        if (this.spielerListe[0] == null) {
            return 0;
        } else if ((this.spielerListe[1] == null)) {
            return 1;
        }
        return 2;
    }


    public String getHistorie() {
        return null;
    }

    public String getStatus() {
        return null;
    }
}
