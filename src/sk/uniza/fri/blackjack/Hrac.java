package sk.uniza.fri.blackjack;

import sk.uniza.fri.skolskeTriedy.Manazer;
import sk.uniza.fri.skolskeTriedy.Obrazok;
import sk.uniza.fri.tlacidla.TlacidloNacitania;
import sk.uniza.fri.tlacidla.Stavka10;
import sk.uniza.fri.tlacidla.Stavka15;
import sk.uniza.fri.tlacidla.Stavka20;
import sk.uniza.fri.tlacidla.Stavka5;
import sk.uniza.fri.tlacidla.StavkaAllIn;
import sk.uniza.fri.tlacidla.TlacidloHraca;
import sk.uniza.fri.tlacidla.TlacidloKrupiera;
import sk.uniza.fri.tlacidla.TlacidloNovejHry;
import sk.uniza.fri.tlacidla.TlacidloStavky;
import sk.uniza.fri.tlacidla.TlacidloUlozenia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Trieda ktorá reaguje na vstupy hráča
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */

public class Hrac extends Krupier {
    private int stredX;
    private int skore;
    private int pocetEs;
    private boolean hracStoji;
    private Obrazok stavka;
    private ArrayList<Obrazok> stavky;
    private boolean moznostNovejStavky;

    /**
     * Zobrazí prislušne tlačidla a nastaví atributy
     */
    public Hrac() {
        this.tlacidloTahaj();
        this.tlacidloStoj();
        this.tlacidloVstavit();
        this.obrazokUlozenia();
        this.obrazokNacitania();
        this.stredX = 50;
        this.skore = 0;
        this.pocetEs = 0;
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
        this.hracStoji = false;
        this.stavky = new ArrayList<>();
    }

    /**
     * Zobrazí tlačidlo stavit
     */
    private void tlacidloVstavit() {
        this.stavka = new Obrazok("gesta/bet.png");
        this.stavka.zmenVelkost(100, 100);
        this.stavka.zmenPolohu(250, 537);
        this.stavka.zobraz();
    }

    /**
     * Zobrazí tlačidlo tahaj.
     */
    private void tlacidloTahaj() {
        Obrazok obrazok = new Obrazok("gesta/hit.gif");
        obrazok.zmenVelkost(100, 100);
        obrazok.zmenPolohu(375, 537);
        obrazok.zobraz();
    }
    /**
     * Zobrazí tlačidlo stoj.
     */
    private void tlacidloStoj() {
        Obrazok obrazok = new Obrazok("gesta/stand.png");
        obrazok.zmenVelkost(100, 100);
        obrazok.zmenPolohu(500, 537);
        obrazok.zobraz();
    }

    /**
     * Manazer podla toho kde hrac klikne vyhodnoti suradnice
     * @param x suradnica kde sa nachadza x
     * @param y suradnica kde sa nachadza y
     */
    public void vyberSuradnice(int x, int y) {
        TlacidloHraca tlacidloHraca = new TlacidloHraca();
        TlacidloKrupiera tlacidloKrupiera = new TlacidloKrupiera();
        TlacidloNovejHry tlacidloNovejHry = new TlacidloNovejHry();
        TlacidloStavky tlacidloStavky = new TlacidloStavky();
        Stavka5 stavka5 = new Stavka5();
        Stavka10 stavka10 = new Stavka10();
        Stavka15 stavka15 = new Stavka15();
        Stavka20 stavka20 = new Stavka20();
        StavkaAllIn stavkaAllIn = new StavkaAllIn();
        TlacidloUlozenia tlacidloUlozenia = new TlacidloUlozenia();
        TlacidloNacitania tlacidloNacitania = new TlacidloNacitania();
        if (tlacidloHraca.obsahujeSuradnice(x, y) && !this.hracStoji && super.isHraZacala()) {
            this.stavka.skry();
            this.moznostNovejStavky = false;
            Karta karta = this.zoberKartu(this.stredX, 400);
            this.stredX += 100;
            this.skore += karta.getHodnota();
            if (this.skore > 21 && karta.jeEso()) {
                this.skore = this.skore - 10;
            } else if (this.skore < 21 && karta.jeEso()) {
                this.pocetEs++;
            } else if (this.skore > 21 && !karta.jeEso() && this.pocetEs > 0) {
                this.skore = this.skore - (10 * this.pocetEs);
                this.pocetEs = 0;
            }
            System.out.println("Skore Hraca: " + this.skore);
            super.kontrolaSkore(this.skore, super.getSkore(), this.hracStoji);
            if (this.skore > 21) {
                this.hracStoji = true;
            }
        } else if (tlacidloKrupiera.obsahujeSuradnice(x, y) && !this.hracStoji && super.isHraZacala()) {
            this.hracStoji = true;
            this.moznostNovejStavky = false;
            this.stavka.skry();
            while (super.getSkore() < 17 && (!super.isKrupierJeVitaz() || !super.isHracJeVitaz() && super.isKrupierStoji())) {
                Karta karta = super.zoberKartu(super.getStredX(), 150);
                super.setStredX(super.getStredX() + 100);
                super.setSkore(super.getSkore() + karta.getHodnota());
                if (super.getSkore() > 21 && karta.jeEso()) {
                    super.setSkore(super.getSkore() - 10);
                } else if (super.getSkore() < 21 && karta.jeEso()) {
                    super.setPocetEs(super.getPocetEs() + 1);
                } else if (super.getSkore() > 21 && !karta.jeEso() && super.getPocetEs() > 0) {
                    super.setSkore(super.getSkore() - (10 * super.getPocetEs()));
                    super.setPocetEs(0);
                }
                System.out.println("Skore Krupiera: " + super.getSkore());
                if (super.getSkore() >= 17) {
                    super.setKrupierStoji(true);
                }
                super.kontrolaSkore(this.skore, super.getSkore(), this.hracStoji);
            }
        } else if (tlacidloNovejHry.obsahujeSuradnice(x, y) && (super.isHracJeVitaz() || super.isKrupierJeVitaz())) {
            super.novaHra();
            this.hracStoji = false;
            this.skore = 0;
            this.pocetEs = 0;
            super.getNovaHra().skry();
            this.stredX = 50;
            this.stavka.zobraz();
            this.moznostNovejStavky = true;
        } else if (tlacidloStavky.obsahujeSuradnice(x, y) && (!super.isHraZacala() || this.moznostNovejStavky)) {
            this.stavka.skry();
            this.moznostNovejStavky = false;
            System.out.println("Akú sumu chceš staviť ?");
            System.out.println("- 5€");
            System.out.println("- 10€");
            System.out.println("- 15€");
            System.out.println("- 20€");
            System.out.println("- All in");
            System.out.println();
            this.stavky = new ArrayList<>();
            this.stavky.add(this.obrazok5());
            this.stavky.add(this.obrazok10());
            this.stavky.add(this.obrazok15());
            this.stavky.add(this.obrazok20());
            this.stavky.add(this.obrazokAllIn());
            super.setEvidovanieStavky(true);
        } else if (tlacidloHraca.obsahujeSuradnice(x, y) && !super.isHraZacala()) {
            System.out.println("Ak chceš začať hru musíš si stavit");
            System.out.println();
        } else if (tlacidloKrupiera.obsahujeSuradnice(x, y) && !super.isHraZacala()) {
            System.out.println("Ak chceš začať hru musíš si stavit");
            System.out.println();
        } else if (stavka5.obsahujeSuradnice(x, y) && super.isZaevidovanaStavka()) {
            super.zaevidujStavku("5");
            this.vypniStavky();
        } else if (stavka10.obsahujeSuradnice(x, y) && super.isZaevidovanaStavka() && super.getSuma() >= 10) {
            super.zaevidujStavku("10");
            this.vypniStavky();
        } else if (stavka15.obsahujeSuradnice(x, y) && super.isZaevidovanaStavka() && super.getSuma() >= 15) {
            super.zaevidujStavku("15");
            this.vypniStavky();
        } else if (stavka20.obsahujeSuradnice(x, y) && super.isZaevidovanaStavka() && super.getSuma() >= 20) {
            super.zaevidujStavku("20");
            this.vypniStavky();
        } else if (stavkaAllIn.obsahujeSuradnice(x, y) && super.isZaevidovanaStavka()) {
            super.zaevidujStavku("allin");
            this.vypniStavky();
        } else if (stavka10.obsahujeSuradnice(x, y) && super.isZaevidovanaStavka() && super.getSuma() < 10) {
            System.out.println("Na túto stávku nemáš dostatok penazí. Potrebuješ vyhrať ešte " + (10 - super.getSuma() + "€"));
            System.out.println();
        } else if (stavka15.obsahujeSuradnice(x, y) && super.isZaevidovanaStavka() && super.getSuma() < 15) {
            System.out.println("Na túto stávku nemáš dostatok penazí. Potrebuješ vyhrať ešte " + (15 - super.getSuma() + "€"));
            System.out.println();
        } else if (stavka20.obsahujeSuradnice(x, y) && super.isZaevidovanaStavka() && super.getSuma() < 20) {
            System.out.println("Na túto stávku nemáš dostatok penazí. Potrebuješ vyhrať ešte " + (20 - super.getSuma() + "€"));
            System.out.println();
        } else if (tlacidloUlozenia.obsahujeSuradnice(x, y)) {
            this.ulozHru();
        } else if (tlacidloNacitania.obsahujeSuradnice(x, y)) {
            this.nacitajHru();
        }
    }

    /**
     * Nacita hru
     */
    private void nacitajHru() {
        File suborPozicie = new File("saveVBlackJacku.blackjack");
        try (DataInputStream streamPozicie = new DataInputStream(new FileInputStream(suborPozicie))) {
            this.nacitajUdaje(streamPozicie);
        } catch (IOException e) {
            System.out.println("Tento load sa nepodaril");
        }
    }

    /**
     * Nacita udaje o hre
     * @param streamPozicie zapisuje sa do binárnej sústavy
     * @throws IOException vynimka ktora sa zachytí
     */
    private void nacitajUdaje(DataInputStream streamPozicie) throws IOException {
        //krupier vycisti plochu
        super.pripravLoad();
        //nacitanie kariet na ploche
        int pocetKariet = streamPozicie.readInt();
        ArrayList<Karta> karty = new ArrayList<>();
        this.nacitavanieBalicka(streamPozicie, karty, pocetKariet);
        // nacitanie balicka
        ArrayList<Karta> balicek = new ArrayList<>();
        int pocetKarietVBalicku = streamPozicie.readInt();
        this.nacitavanieBalicka(streamPozicie, balicek, pocetKarietVBalicku);
        Balicek balicek1 = new Balicek();
        balicek1.nastavBalicek(balicek);
        super.setKartyNaPloche(karty);
        super.setBalicek(balicek1);
        // nacitanie hraca
        this.skore = streamPozicie.readInt();
        this.stredX = streamPozicie.readInt();
        this.pocetEs = streamPozicie.readInt();
        this.hracStoji = streamPozicie.readBoolean();
        this.moznostNovejStavky = streamPozicie.readBoolean();
        // nacitanie krupiera
        super.setSkore(streamPozicie.readInt());
        super.setStredX(streamPozicie.readInt());
        super.setPocetEs(streamPozicie.readInt());
        super.setEvidovanieStavky(streamPozicie.readBoolean());
        super.setHraZacala(streamPozicie.readBoolean());
        super.setHracJeVitaz(streamPozicie.readBoolean());
        super.setKrupierJeVitaz(streamPozicie.readBoolean());
        super.setAllin(streamPozicie.readBoolean());
        super.setKrupierStoji(streamPozicie.readBoolean());
        super.setSuma(streamPozicie.readInt());
        super.setVklad(streamPozicie.readInt());
        super.nacitajLoad();
        this.upravPlochu();
        System.out.println();
        System.out.println("Load prebehol úspešne!");
        System.out.println();
        System.out.println("Aktualne skore hráča: " + this.skore);
        System.out.println("Aktualne skore krupiera: " + super.getSkore());
        System.out.println("Aktualna suma ktorú si dosiahol: " + super.getSuma());
        System.out.println("Aktualna stávka ktorú máš zvolenú: " + super.getVklad());
    }

    /**
     * Po nacitani sa upraví plocha
     */
    private void upravPlochu() {
        if (this.moznostNovejStavky) {
            this.stavka.zobraz();
        } else {
            this.stavka.skry();
        }
        if (super.isZaevidovanaStavka()) {
            for (Obrazok obrazok : this.stavky) {
                obrazok.zobraz();
            }
        } else {
            for (Obrazok obrazok : this.stavky) {
                obrazok.skry();
            }
        }
    }

    /**
     * Metóda na nacitavanie kariet zo suboru
     * @param streamPozicie nacitavanie z binarnej sústavy
     * @param balicek balicek ktorý sa nacíta
     * @param pocetKarietVBalicku pocet kariet v balicku
     * @throws IOException vyhodi vynimku
     */
    private void nacitavanieBalicka(DataInputStream streamPozicie, ArrayList<Karta> balicek, int pocetKarietVBalicku) throws IOException {
        for (int i = 0; i < pocetKarietVBalicku; i++) {
            if (streamPozicie.readBoolean()) {
                TypKarty typKarty = TypKarty.valueOf(streamPozicie.readUTF());
                String nazov = streamPozicie.readUTF();
                Obrazok obrazok = new Obrazok("karty/" + nazov + ".jpg");
                Eso eso = new Eso(typKarty, nazov, streamPozicie.readInt(), obrazok);
                balicek.add(eso);
            } else {
                TypKarty typKarty = TypKarty.valueOf(streamPozicie.readUTF());
                String nazov = streamPozicie.readUTF();
                Obrazok obrazok = new Obrazok("karty/" + nazov + ".jpg");
                Karta karta = new Karta(typKarty, nazov, streamPozicie.readInt(), obrazok);
                balicek.add(karta);
            }
        }
    }

    /**
     * Ulozi hru
     */
    private void ulozHru() {
        File suborPozicie = new File("saveVBlackJacku.blackjack");
        try (DataOutputStream streamPozicie = new DataOutputStream(new FileOutputStream(suborPozicie))) {
            this.ulozUdaje(streamPozicie);
        } catch (IOException e) {
            System.out.println("Tento save sa nepodaril");
        }
    }

    /**
     * Ulozi udaje o hre do binarneho suboru
     * @param streamPozicie uklada binarne
     * @throws IOException vyhodi vynimku
     */
    private void ulozUdaje(DataOutputStream streamPozicie) throws IOException {
        // ulozenie kariet na ploche
        streamPozicie.writeInt(super.getKartyNaPloche().size());
        for (Karta karta : super.getKartyNaPloche()) {
            streamPozicie.writeBoolean(karta.jeEso());
            streamPozicie.writeUTF(String.valueOf(karta.getTypKarty()));
            streamPozicie.writeUTF(karta.getNazov());
            streamPozicie.writeInt(karta.getHodnota());
        }
        //ulozenie balicka
        Balicek balicek = super.getBalicek();
        streamPozicie.writeInt(balicek.getBalicek().size());
        for (Karta karta : balicek.getBalicek()) {
            streamPozicie.writeBoolean(karta.jeEso());
            streamPozicie.writeUTF(String.valueOf(karta.getTypKarty()));
            streamPozicie.writeUTF(karta.getNazov());
            streamPozicie.writeInt(karta.getHodnota());
        }
        //ulozenie stavu hraca
        streamPozicie.writeInt(this.skore);
        streamPozicie.writeInt(this.stredX);
        streamPozicie.writeInt(this.pocetEs);
        streamPozicie.writeBoolean(this.hracStoji);
        streamPozicie.writeBoolean(this.moznostNovejStavky);
        //ulozenie stavu krupiera
        streamPozicie.writeInt(super.getSkore());
        streamPozicie.writeInt(super.getStredX());
        streamPozicie.writeInt(super.getPocetEs());
        streamPozicie.writeBoolean(super.isZaevidovanaStavka());
        streamPozicie.writeBoolean(super.isHraZacala());
        streamPozicie.writeBoolean(super.isHracJeVitaz());
        streamPozicie.writeBoolean(super.isKrupierJeVitaz());
        streamPozicie.writeBoolean(super.isAllin());
        streamPozicie.writeBoolean(super.isKrupierStoji());
        streamPozicie.writeInt(super.getSuma());
        streamPozicie.writeInt(super.getVklad());
        System.out.println();
        System.out.println("Save prebehol uspešne!");
        System.out.println();
    }

    /**
     * Skryje obrazky stávok
     */
    private void vypniStavky() {
        for (Obrazok obrazok : this.stavky) {
            obrazok.skry();
        }
        this.stavky.clear();
    }

    /**
     * Metóda získana z krupiera
     * @param stredX x-ová súradnica kde sa ma umiesnit karta
     * @param stredY y-ová súradnica kde sa ma ulozit karta
     * @return vráti kartu
     */
    @Override
    public Karta zoberKartu(int stredX, int stredY) {
        return super.zoberKartu(stredX, stredY);
    }


    /**
     * Zobrazí obrázok stavky 5€
     * @return vráti obrázok
     */
    private Obrazok obrazok5() {
        Obrazok obrazok = new Obrazok("karty-zhora/5.png");
        obrazok.zmenVelkost(50, 50);
        obrazok.zmenPolohu(50, 475);
        obrazok.zobraz();
        return obrazok;
    }

    /**
     * Zobrazí obrázok stavky 10€
     * @return vráti obrázok
     */
    private Obrazok obrazok10() {
        Obrazok obrazok = new Obrazok("karty-zhora/10.png");
        obrazok.zmenVelkost(50, 50);
        obrazok.zmenPolohu(125, 475);
        obrazok.zobraz();
        return obrazok;
    }

    /**
     * Zobrazí obrázok stavky 15€
     * @return vráti obrázok
     */
    private Obrazok obrazok15() {
        Obrazok obrazok = new Obrazok("karty-zhora/15.png");
        obrazok.zmenVelkost(50, 50);
        obrazok.zmenPolohu(50, 525);
        obrazok.zobraz();
        return obrazok;
    }

    /**
     * Zobrazí obrázok stavky 20€
     * @return vráti obrázok
     */
    private Obrazok obrazok20() {
        Obrazok obrazok = new Obrazok("karty-zhora/20.png");
        obrazok.zmenVelkost(50, 50);
        obrazok.zmenPolohu(125, 525);
        obrazok.zobraz();
        return obrazok;
    }

    /**
     * Zobrazí obrázok stavky All in
     * @return vráti obrázok
     */
    private Obrazok obrazokAllIn() {
        Obrazok obrazok = new Obrazok("karty-zhora/allin.png");
        obrazok.zmenVelkost(125, 50);
        obrazok.zmenPolohu(85, 575);
        obrazok.zobraz();
        return obrazok;
    }

    /**
     * Zobrazí obrázok stavky ulozenia
     */
    private void obrazokUlozenia() {
        Obrazok obrazok = new Obrazok("gesta/save.png");
        obrazok.zmenVelkost(100, 100);
        obrazok.zmenPolohu(745, 50);
        obrazok.zobraz();
    }

    /**
     * Zobrazí obrázok stavky ulozenia
     */
    private void obrazokNacitania() {
        Obrazok obrazok = new Obrazok("gesta/load.png");
        obrazok.zmenVelkost(100, 100);
        obrazok.zmenPolohu(745, 150);
        obrazok.zobraz();
    }

}

