package spiel.historie;

import spiel.historie.spielzug.Spielzug;

import java.util.ArrayList;
import java.util.List;

public class Historie {

    private List<Spielzug> historie = new ArrayList<>();

    public void addSpielzug(String vonFeldId, String nachFeldId) {
        this.historie.add(new Spielzug(vonFeldId, nachFeldId));
    }

}
