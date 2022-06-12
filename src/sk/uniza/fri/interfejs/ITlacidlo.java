package sk.uniza.fri.interfejs;

/**
 *
 * Interface ktorý sa používa pri tlačidlách
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public interface ITlacidlo {

    /**
     * Správa ktorá zisti či kliknutie myši obsahovalo súradnice
     * @param x x-ová súradnica
     * @param y y-ová súradnica
     * @return vráti boolean či sa myš po kliknutí nachádzala na súradniciach
     */
    boolean obsahujeSuradnice(int x, int y);

    /**
     * Vráti názov daného tlačidla
     * @return nazov tlačidla
     */
    String getNazov();

    /**
     * Vráti popis daného tlačidla
     * @return popis tlačidla
     */
    String getPopis();

}
