package sk.uniza.fri.blackjack;

import sk.uniza.fri.skolskeTriedy.Obrazok;

/**
 *
 * Trieda ktorá obsahuje informácie o karte
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public class Karta {


    private final Obrazok obrazok;
    private final TypKarty typKarty;
    private final int hodnota;
    private final String nazov;

    /**
     * Nastavy atributy na dané hodnoty podla parametrov
     * @param typKarty typ karty
     * @param nazovKarty nazov karty
     * @param hodnota hodnota karty
     * @param obrazokKarty obrazok karty
     */
    public Karta(TypKarty typKarty, String nazovKarty, int hodnota, Obrazok obrazokKarty) {
        this.typKarty = typKarty;
        this.hodnota = hodnota;
        this.nazov = nazovKarty;
        this.obrazok = obrazokKarty;
    }

    /**
     * Vráti názov karty
     * @return názov karty
     */
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Vráti hodnotu karty
     * @return hodnota karty
     */
    public int getHodnota() {
        return this.hodnota;
    }

    /**
     * Vráti či daná karta je eso
     * @return je eso
     */
    public boolean jeEso() {
        return false;
    }

    /**
     * Vráti typ karty
     * @return typ karty
     */
    public TypKarty getTypKarty() {
        return this.typKarty;
    }

    /**
     * Vráti obrázok ktorý zodpovedá karte
     * @return obrázok karty
     */
    public Obrazok getObrazok() {
        return this.obrazok;
    }
}
