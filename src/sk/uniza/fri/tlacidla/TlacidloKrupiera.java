package sk.uniza.fri.tlacidla;

import sk.uniza.fri.interfejs.ITlacidlo;

/**
 * Trieda ktorá vráti určitý druh udalosti
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public class TlacidloKrupiera implements ITlacidlo {
    /**
     * Správa ktorá zisti či kliknutie myši obsahovalo súradnice
     * @param x x-ová súradnica
     * @param y y-ová súradnica
     * @return vráti boolean či sa myš po kliknutí nachádzala na súradniciach
     */
    @Override
    public boolean obsahujeSuradnice(int x, int y) {
        return x >= 450 && x < 550 && y >= 487 && y < 587;
    }
    /**
     * Vráti názov daného tlačidla
     * @return nazov tlačidla
     */
    @Override
    public String getNazov() {
        return "Tlacidlo stoj";
    }
    /**
     * Vráti popis daného tlačidla
     * @return popis tlačidla
     */
    @Override
    public String getPopis() {
        return "Tlacidlo ktoré po stlačení nedovolí hráčovi tahať dalšie karty, krupiér ťahá svoje karty";
    }
}
