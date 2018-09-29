package spiel.spielbrett;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SpielbrettTest {

    protected Spielbrett spielbrett;

    @org.junit.Before
    public void setUp() {
        this.spielbrett = new Spielbrett();
    }

    @Test
    public void spielfeldIst8x8() {
        assertEquals(8, this.spielbrett.getSpielfelder().length);
        assertEquals(8, this.spielbrett.getSpielfelder()[0].length);
        assertEquals(8, this.spielbrett.getSpielfelder()[7].length);
        System.out.println(this.spielbrett.printSpielfeld());
    }

    @Test
    public void spielfeldIdEntsprichtKoordinate() {
        assertEquals("11", this.spielbrett.getSpielfelder()[0][0].getId());
        assertEquals("88", this.spielbrett.getSpielfelder()[7][7].getId());
    }

    @Test
    public void schwarzWeissLayoutKorrekt() {
        // Ungerade Reihen -> W - S
        assertEquals("WEISS", this.spielbrett.getSpielfelder()[0][0].getFarbe());
        assertEquals("WEISS", this.spielbrett.getSpielfelder()[2][0].getFarbe());
        assertEquals("WEISS", this.spielbrett.getSpielfelder()[4][0].getFarbe());
        assertEquals("WEISS", this.spielbrett.getSpielfelder()[6][0].getFarbe());
        assertEquals("SCHWARZ", this.spielbrett.getSpielfelder()[6][7].getFarbe());
        assertEquals("SCHWARZ", this.spielbrett.getSpielfelder()[6][7].getFarbe());
        assertEquals("SCHWARZ", this.spielbrett.getSpielfelder()[6][7].getFarbe());
        assertEquals("SCHWARZ", this.spielbrett.getSpielfelder()[6][7].getFarbe());

        // Gerade Reihen -> S - W
        assertEquals("SCHWARZ", this.spielbrett.getSpielfelder()[1][0].getFarbe());
        assertEquals("SCHWARZ", this.spielbrett.getSpielfelder()[3][0].getFarbe());
        assertEquals("SCHWARZ", this.spielbrett.getSpielfelder()[5][0].getFarbe());
        assertEquals("SCHWARZ", this.spielbrett.getSpielfelder()[7][0].getFarbe());
        assertEquals("WEISS", this.spielbrett.getSpielfelder()[1][7].getFarbe());
        assertEquals("WEISS", this.spielbrett.getSpielfelder()[3][7].getFarbe());
        assertEquals("WEISS", this.spielbrett.getSpielfelder()[5][7].getFarbe());
        assertEquals("WEISS", this.spielbrett.getSpielfelder()[7][7].getFarbe());

    }

    @Test
    public void spielfigurenSindKorrektGesetzt() {
        // Leere Startreihen mit weissen Feldern
        assertNull(this.spielbrett.getSpielfelder()[0][0].getSpielfigur());
        assertNull(this.spielbrett.getSpielfelder()[2][0].getSpielfigur());
        assertNull(this.spielbrett.getSpielfelder()[3][0].getSpielfigur());
        assertNull(this.spielbrett.getSpielfelder()[4][0].getSpielfigur());
        assertNull(this.spielbrett.getSpielfelder()[6][0].getSpielfigur());

        // Startfelder SCHWARZ
        assertEquals("S", this.spielbrett.getSpielfelder()[0][1].getSpielfigur().getFarbe().toString());
        assertEquals("S", this.spielbrett.getSpielfelder()[1][0].getSpielfigur().getFarbe().toString());
        assertEquals("S", this.spielbrett.getSpielfelder()[2][1].getSpielfigur().getFarbe().toString());

        // Startfelder WEISS
        assertEquals("W", this.spielbrett.getSpielfelder()[5][0].getSpielfigur().getFarbe().toString());
        assertEquals("W", this.spielbrett.getSpielfelder()[6][1].getSpielfigur().getFarbe().toString());
        assertEquals("W", this.spielbrett.getSpielfelder()[7][0].getSpielfigur().getFarbe().toString());
    }


}