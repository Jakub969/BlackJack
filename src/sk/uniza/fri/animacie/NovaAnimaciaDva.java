package sk.uniza.fri.animacie;

import sk.uniza.fri.interfejs.IPrikaz;
import sk.uniza.fri.skolskeTriedy.Obrazok;

import java.util.ArrayList;

/**
 *
 * Trieda ktorá vykoná určití druh animácie
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public class NovaAnimaciaDva extends InicializaciaAnimacie implements IPrikaz {
    /**
     * Vykoná určitý druh animácie
     */
    @Override
    public void vykonaj() {
        ArrayList<Obrazok> obrazokArrayList = super.getObrazoks();
        int i = 20;
        for (Obrazok obrazok : obrazokArrayList) {
            obrazok.zmenPolohu(600, 105);
            obrazok.zmenVelkost(75, 150);
            obrazok.zobraz();
            obrazok.pomalyPosunVodorovne(600 - i);
            i = i + 20;
        }
        for (Obrazok obrazok : obrazokArrayList) {
            obrazok.skry();
        }
        obrazokArrayList.clear();
    }
}
