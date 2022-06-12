package sk.uniza.fri.blackjack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniza.fri.skolskeTriedy.Obrazok;

/**
 * Test ktorý zistuje či karta nie je eso
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
class KartaTest {

    private Karta karta;
    private Eso eso;

    /**
     * Vytvorí testovaciu kartu a eso
     */
    @BeforeEach
    void setUp() {
        Obrazok karova5 = new Obrazok("karty/karova5.jpg");
        this.karta = new Karta(TypKarty.KAROVA, "karova5", 5, karova5);
        Obrazok srdce1 = new Obrazok("karty/srdce1.jpg");
        this.eso = new Eso(TypKarty.SRDCE, "srdce1", 11, srdce1);
    }

    /**
     * Testuje ci eso ma vlastnost ze je eso
     */
    @Test
    void jeEso() {
        Assertions.assertSame(true, this.eso.jeEso());
    }

    /**
     * Testuje ze karta nie je eso
     */
    @Test
    void nieJeEso() {
        Assertions.assertSame(false, this.karta.jeEso());
    }
}