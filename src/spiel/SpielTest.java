package spiel;

import org.junit.Before;
import org.junit.Test;
import spiel.farbe.FarbEnum;

import static org.junit.Assert.assertEquals;

public class SpielTest {

    protected Spiel spiel;

    @Before
    public void setUp() {
        this.spiel = new Spiel();
    }

    @Test
    public void weisseSpielerfarbeHinzufuegenFunktioniert() {
        this.spiel.addSpieler("Weiss", FarbEnum.WEISS);
        assertEquals("WEISS", this.spiel.getSpielerListe()[0].getFarbe().toString());
        assertEquals(null, this.spiel.getSpielerListe()[1]);
    }

    @Test
    public void schwarzeSpielerfarbeHinzufuegenFunktioniert() {
        this.spiel.addSpieler("Schwarz", FarbEnum.SCHWARZ);
        assertEquals("SCHWARZ", this.spiel.getSpielerListe()[1].getFarbe().toString());
        assertEquals(null, this.spiel.getSpielerListe()[0]);
    }

    @Test
    public void beideSpielerfarbenHinzufuegenFunktioniert() {
        this.spiel.addSpieler("Weiss", FarbEnum.WEISS);
        this.spiel.addSpieler("Schwarz", FarbEnum.SCHWARZ);

        assertEquals("WEISS", this.spiel.getSpielerListe()[0].getFarbe().toString());
        assertEquals("SCHWARZ", this.spiel.getSpielerListe()[1].getFarbe().toString());
    }

    @Test
    public void erlaubteZuegeFuerStartSituationKorrekt() {
        this.spiel.addSpieler("weiss", FarbEnum.WEISS);
        this.spiel.addSpieler("schwarz", FarbEnum.SCHWARZ);

        this.spiel.starte();

        spiel.getAlleZugmöglichkeiten();

    }
}