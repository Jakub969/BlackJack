package sk.uniza.fri.animacie;

import sk.uniza.fri.skolskeTriedy.Obrazok;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Trieda ktorá pred vykonaním animácie vytvorí obrázky kariet
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public class InicializaciaAnimacie {

    private ArrayList<Obrazok> obrazoks;


    /**
     * Konštruktor inicializuje inicializaciu
     */
    public InicializaciaAnimacie() {
        this.obrazoks = new ArrayList<>();
        this.inicializacia();
    }

    /**
     * Vytvorí sa Array-list obrazkov s kartami otočeními smerom dole
     */
    public void inicializacia() {
        ArrayList<Obrazok> obrazkyKariet = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Obrazok obrazokKarty = new Obrazok("karty-zhora/karta" + new Random().nextInt(1, 5) + ".jpg");
            obrazkyKariet.add(obrazokKarty);
        }
        this.obrazoks = obrazkyKariet;
    }

    /**
     * Vráti Array-list obrázkov
     * @return obrázky
     */
    public ArrayList<Obrazok> getObrazoks() {
        return this.obrazoks;
    }
}
