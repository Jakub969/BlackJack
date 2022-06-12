package sk.uniza.fri.tlacidla;

import sk.uniza.fri.interfejs.ITlacidlo;

/**
 * Trieda ktorá vracia určitý druh stávky
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public class Stavka20 implements ITlacidlo {
    /**
     * Správa ktorá zisti či kliknutie myši obsahovalo súradnice
     * @param x x-ová súradnica
     * @param y y-ová súradnica
     * @return vráti boolean či sa myš po kliknutí nachádzala na súradniciach
     */
    @Override
    public boolean obsahujeSuradnice(int x, int y) {
        return x >= 100 && x < 150 && y >= 500 && y < 550;
    }
    /**
     * Vráti názov daného tlačidla
     * @return nazov tlačidla
     */
    @Override
    public String getNazov() {
        return "20€ stávka";
    }
    /**
     * Vráti popis daného tlačidla
     * @return popis tlačidla
     */
    @Override
    public String getPopis() {
        return "Tlacidlo ktoré po stlacení staví 20€";
    }
}
