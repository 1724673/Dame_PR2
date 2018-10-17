package spiel;

import spiel.farbe.FarbEnum;
import spiel.historie.Historie;
import spiel.spielbrett.Spielbrett;
import spiel.spielbrett.spielfeld.Spielfeld;
import spiel.spieler.Spieler;

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


    public Spiel() {
        this.spielbrett = new Spielbrett();
        this.istSpielGestartet = false;
        this.istSpielZuEnde = false;
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
    public Map<String,List<String>> getAlleZugmöglichkeiten(){
        Map<String,List<String>> map = new HashMap<>();
        System.out.println(this.spielbrett.printSpielfeld());
        for(int x = 0;x<8;x++){
            for(int y =((7-x)%2); y<8;y=y+2){
                if((this.spielbrett.getSpielfelder()[x][y].getSpielfigur()!=null) &&this.spielbrett.getSpielfelder()[x][y].getSpielfigur().getFarbe() == this.spielerAmZug.getFarbe()){
                    List<String> out= new ArrayList<>();
                    out.addAll(this.getZugmöglichkeitenFuerX(x,y,this.spielbrett.getSpielfelder()[x][y].getSpielfigur().isIstDame()));
                    if(out.size()!= 0){
                        map.put(x+" "+y,out);
                    }
                }
            }
        }
        for(String v:map.keySet()){
            System.out.println("VON: " +v);
            for(String n:map.get(v)){
                System.out.println("NACH: " +n);
            }
        };
        return null;
    }
    public List<String> getZugmöglichkeitenFuerX(int x,int y,boolean istDame){
        List<String> out= new ArrayList<>();
        int vorne = 1;
        if(this.spielerAmZug.getFarbe() == FarbEnum.WEISS){
            vorne = -1;
        }
        if(this.spielbrett.getSpielfelder()[x][y].getSpielfigur() != null){
            if(x+vorne >=0 && x+vorne <=7 && y+1 >=0 &&y+1<=7) {
                if (this.spielbrett.getSpielfelder()[x + vorne][y + 1].getSpielfigur() == null) {
                    out.add((x + vorne) + " " + (y + 1));
                }
            }
            if(x+vorne >=0 && x+vorne <=7 && y-1 >=0 &&y-1<=7) {
                if (this.spielbrett.getSpielfelder()[x + vorne][y - 1].getSpielfigur() == null) {
                    out.add((x + vorne) + " " + (y - 1));
                }
            }
        }
        if(x+2*vorne >=0 && x+2*vorne <=7 && y+2 >=0 &&y+2<=7) {
            if ((this.spielbrett.getSpielfelder()[x + vorne][y + 1].getSpielfigur() != null) &&
                    (!this.spielbrett.getSpielfelder()[x + vorne][y + 1].getSpielfigur().getFarbe().equals(this.spielerAmZug.getFarbe())) &&
                    (this.spielbrett.getSpielfelder()[x + (2 * vorne)][y + 2].getSpielfigur() == null)) {
                List<String> toAdd= new ArrayList<>();
                toAdd = this.getZugmöglichkeitenFuerX((x + 2 * vorne), (y + 2), false);
                if(toAdd.size()==0){
                    out.add((x + 2 * vorne)+ " "+(y + 2));
                }else{
                    out.addAll(toAdd);
                }
            }
        }
        if(x+2*vorne >=0 && x+2*vorne <=7 && y-2 >=0 &&y-2<=7) {
            if ((this.spielbrett.getSpielfelder()[x + vorne][y - 1].getSpielfigur() != null) &&
                    (!this.spielbrett.getSpielfelder()[x + vorne][y - 1].getSpielfigur().getFarbe().equals(this.spielerAmZug.getFarbe())) &&
                    (this.spielbrett.getSpielfelder()[x + (2 * vorne)][y - 2].getSpielfigur() == null)) {
                List<String> toAdd= new ArrayList<>();
                toAdd = this.getZugmöglichkeitenFuerX((x + 2 * vorne), (y - 2), false);
                if(toAdd.size()==0){
                    out.add((x + 2 * vorne)+ " "+(y - 2));
                }else{
                    out.addAll(toAdd);
                }
            }
        }
        return out;
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
    public boolean getSpielGestartet(){return this.istSpielGestartet;}
    public boolean getIstSpielZuEde(){return this.istSpielZuEnde;}
    public int getAnzahlSpieler(){
        if(this.spielerListe[0] == null){
            return 0;
        } else if((this.spielerListe[1] == null)){
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
