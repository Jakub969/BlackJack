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
public class NovaAnimaciaTri extends InicializaciaAnimacie implements IPrikaz {
    /**
     * Vykoná určitý druh animácie
     */
    @Override
    public void vykonaj() {
        ArrayList<Obrazok> obrazokArrayList = super.getObrazoks();
        int i = 20;
        int j = 10;
        for (Obrazok obrazok : obrazokArrayList) {
            obrazok.zmenVelkost(75, 150);
            obrazok.zmenPolohu(675, 100);
            obrazok.zobraz();
            obrazok.pomalyPosunVodorovne(675 - i);
            obrazok.pomalyPosunZvisle(100 + j);
            i = i + 20;
            j = j + 10;
        }
        for (Obrazok obrazok : obrazokArrayList) {
            obrazok.skry();
        }
        obrazokArrayList.clear();
    }
}
