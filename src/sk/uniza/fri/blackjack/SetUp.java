package sk.uniza.fri.blackjack;

import sk.uniza.fri.skolskeTriedy.Obrazok;

/**
 *
 * Trieda ktorá zobrazí mena krupiera a hraca
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public class SetUp {

    /**
     * Na plátne zobrazí mena hráca a krupiera
     */
    public SetUp() {
        Obrazok obrazok1 = new Obrazok("karty-zhora/krupier.png");
        obrazok1.zmenPolohu(285, 70);
        obrazok1.zmenVelkost(200, 50);
        obrazok1.zobraz();
        Obrazok obrazok2 = new Obrazok("Karty-zhora/hrac.png");
        obrazok2.zmenPolohu(185, 320);
        obrazok2.zmenVelkost(125, 50);
        obrazok2.zobraz();
    }
}
