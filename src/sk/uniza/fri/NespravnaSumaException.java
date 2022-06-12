package sk.uniza.fri;

/**
 * Trieda ktorá vytvára špecialnu výnimku
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */
public class NespravnaSumaException extends Exception {
    /**
     * Vyhodí výnimku ak je suma nesprávna
     * @param message vráti daný text
     */
    public NespravnaSumaException(String message) {
        super(message);
    }
}
