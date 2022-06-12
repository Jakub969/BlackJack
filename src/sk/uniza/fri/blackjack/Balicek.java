package sk.uniza.fri.blackjack;

import sk.uniza.fri.skolskeTriedy.Obrazok;

import java.util.ArrayList;

/**
 *
 * Trieda ktorá obsahuje 52 kariet
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public class Balicek {
    private ArrayList<Karta> karty;

    /**
     * Konštruktor vygeneruje balicek
     */
    public Balicek() {
        this.karty = new ArrayList<>();
        this.generujBalicek();
    }

    /**
     * Metóda generuje balícek
     */
    public void generujBalicek() {
        for (int i = 1; i < 14; i++) {
            int hodnota;
            if (i > 10) {
                hodnota = 10;
            } else if (i == 1) {
                hodnota = 11;
            } else {
                hodnota = i;
            }
            if (i != 1) {
                this.karty.add(new Karta(TypKarty.KRIZ, "kriz" + i, hodnota, new Obrazok("karty/kriz" + i + ".jpg")));
                this.karty.add(new Karta(TypKarty.KAROVA, "karova" + i, hodnota, new Obrazok("karty/karova" + i + ".jpg")));
                this.karty.add(new Karta(TypKarty.PIKA, "pika" + i, hodnota, new Obrazok("karty/pika" + i + ".jpg")));
                this.karty.add(new Karta(TypKarty.SRDCE, "srdce" + i, hodnota, new Obrazok("karty/srdce" + i + ".jpg")));
            } else {
                this.karty.add(new Eso(TypKarty.KRIZ, "kriz" + i, hodnota, new Obrazok("karty/kriz" + i + ".jpg")));
                this.karty.add(new Eso(TypKarty.KAROVA, "karova" + i, hodnota, new Obrazok("karty/karova" + i + ".jpg")));
                this.karty.add(new Eso(TypKarty.PIKA, "pika" + i, hodnota, new Obrazok("karty/pika" + i + ".jpg")));
                this.karty.add(new Eso(TypKarty.SRDCE, "srdce" + i, hodnota, new Obrazok("karty/srdce" + i + ".jpg")));
            }
        }
    }

    /**
     * Vráti balícek s kartami
     * @return balicek
     */
    public ArrayList<Karta> getBalicek() {
        return this.karty;
    }

    /**
     * Nastaví balicek podla parametra
     * @param balicek balicek ktorý chceme nastaviť
     */
    public void nastavBalicek(ArrayList<Karta> balicek) {
        this.karty = balicek;
    }
}
