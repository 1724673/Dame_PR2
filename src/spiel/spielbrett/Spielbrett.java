package spiel.spielbrett;

import spiel.farbe.FarbEnum;
import spiel.spielbrett.spielfeld.Spielfeld;
import spiel.spielbrett.spielfeld.spielfigur.Spielfigur;
import spiel.utilities.Helfer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Spielbrett {

    private Spielfeld[][] spielfelder = new Spielfeld[8][8];

    public Spielbrett() {

        for (int reihe = 0; reihe < 8; reihe++) {
            if (reihe % 2 == 0) {
                for (int spalte = 0; spalte < 8; spalte++) {
                    if (spalte % 2 == 0) spielfelder[reihe][spalte] = erstelleWeissesSpielfeld(reihe, spalte);
                    else spielfelder[reihe][spalte] = erstelleSchwarzesSpielfeld(reihe, spalte);
                }
            } else {
                for (int spalte = 0; spalte < 8; spalte++) {
                    if (spalte % 2 == 0) spielfelder[reihe][spalte] = erstelleSchwarzesSpielfeld(reihe, spalte);
                    else spielfelder[reihe][spalte] = erstelleWeissesSpielfeld(reihe, spalte);
                }
            }
        }
    }

    public Spielfeld[][] getSpielfelder() {
        return spielfelder;
    }

    public void intialisiereSpielfiguren() {

        ArrayList<Spielfeld> schwarzeFelder = Arrays.stream(this.spielfelder).flatMap(Arrays::stream)
                .filter(spielfeld -> spielfeld.getFarbe() == FarbEnum.SCHWARZ.toString())
                .collect(Collectors.toCollection(ArrayList::new));

        for (Spielfeld s : schwarzeFelder) {
            int reihe = Helfer.convertBuchstabeInReihe(s.getId().split("")[0]);
            if (reihe <= 3) s.setSpielfigur(new Spielfigur(FarbEnum.SCHWARZ, false));
            if (reihe >= 6 && reihe <= 8) s.setSpielfigur(new Spielfigur(FarbEnum.WEISS, false));
        }

    }

    private Spielfeld erstelleWeissesSpielfeld(int x, int y) {
        return new Spielfeld(Helfer.convertReiheInBuchstabe(x) + "" + (y + 1), FarbEnum.WEISS);
    }

    private Spielfeld erstelleSchwarzesSpielfeld(int x, int y) {
        return new Spielfeld(Helfer.convertReiheInBuchstabe(x) + (y + 1), FarbEnum.SCHWARZ);
    }

    public String printSpielfeld() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.spielfelder[i][j].getSpielfigur() != null && this.spielfelder[i][j]
                        .getSpielfigur().getFarbe().toString().equals("SCHWARZ")) {
                    if (j == 7) sb.append("○").append("\n\n");
                    else sb.append("○").append("\t\t");
                } else if (this.spielfelder[i][j].getSpielfigur() != null && this.spielfelder[i][j]
                        .getSpielfigur().getFarbe().toString().equals("WEISS")) {
                    if (j == 7) sb.append("●").append("\n\n");
                    else sb.append("●").append("\t\t");
                } else {
                    if (this.spielfelder[i][j].getSpielfigur() == null && this.spielfelder[i][j].getFarbe().equals(
                            "SCHWARZ")) {
                        if (j == 7) sb.append("S").append("\n\n");
                        else sb.append("S").append("\t\t");
                    } else {
                        if (j == 7) sb.append("W").append("\n\n");
                        else sb.append("W").append("\t\t");
                    }
                }
            }


        }

        return sb.toString();
    }

    public void deleteAlleSpielfiguren() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.spielfelder[i][j].getSpielfigur() != null) {
                    this.spielfelder[i][j].setSpielfigur(null);
                }
            }
        }
    }

    public void setSpielfigurAufFeld(int reihe, int spalte, FarbEnum farbe, boolean istDame) {
        this.spielfelder[reihe][spalte].setSpielfigur(new Spielfigur(farbe, istDame));
    }

    public void ziehe(String Spielzug[]) {

    }
}