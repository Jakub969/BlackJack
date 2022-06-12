package sk.uniza.fri.blackjack;

import sk.uniza.fri.skolskeTriedy.Obrazok;

/**
 *
 * Trieda ktorá dedí informácie od karty ale do metódy jeEso vráti true
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public class Eso extends Karta {

    /**
     * Nastavia sa hodnoty podla parametrov
     * @param typKarty typ esa
     * @param nazovKarty nazov esa
     * @param hodnota hodnota esa
     * @param obrazokKarty obrazok esa
     */
    public Eso(TypKarty typKarty, String nazovKarty, int hodnota, Obrazok obrazokKarty) {
        super(typKarty, nazovKarty, hodnota, obrazokKarty);
    }

    /**
     * Vráti informáciu že je karta je eso
     * @return je eso
     */
    @Override
    public boolean jeEso() {
        return true;
    }
}
