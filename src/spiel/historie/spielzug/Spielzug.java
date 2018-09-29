package spiel.historie.spielzug;

public class Spielzug {

    private final String vonFeldId;
    private final String nachFeldId;

    public Spielzug(String vonFeldId, String nachFeldId) {
        this.vonFeldId = vonFeldId;
        this.nachFeldId = nachFeldId;
    }
}
