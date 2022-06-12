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
public class AnimaciaCisloTri extends InicializaciaAnimacie implements IPrikaz {
    /**
     * Vykoná určitý druh animácie
     */
    @Override
    public void vykonaj() {
        ArrayList<Obrazok> obrazkyKariet = super.getObrazoks();
        Obrazok obrazok = obrazkyKariet.get(0);
        obrazok.zmenPolohu(700, 150);
        obrazok.zmenVelkost(75, 150);
        obrazok.zobraz();
        obrazkyKariet.remove(0);
        for (Obrazok obrazok1 : obrazkyKariet) {
            obrazok1.zmenPolohu(650, 150);
            obrazok1.zmenVelkost(75, 150);
            obrazok1.zobraz();
            obrazok1.pomalyPosunVodorovne(-50);
            obrazok1.pomalyPosunZvisle(200);
            obrazok1.pomalyPosunVodorovne(100);
            obrazok1.pomalyPosunZvisle(-200);
        }
    }
}
