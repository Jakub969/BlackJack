package sk.uniza.fri.blackjack;

import sk.uniza.fri.NespravnaSumaException;
import sk.uniza.fri.animacie.AnimaciaCisloDva;
import sk.uniza.fri.animacie.AnimaciaCisloJedna;
import sk.uniza.fri.animacie.AnimaciaCisloTri;
import sk.uniza.fri.skolskeTriedy.Obrazok;
import sk.uniza.fri.skolskeTriedy.Platno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * Trieda ktorá sa stará o celkový priebeh hry a vyhodnocuje vítaza
 *
 * 19. 5. 2022 - 20:24
 *
 * @author jakub
 */

public class Krupier {
    private boolean hraZacala;
    private boolean krupierStoji;
    private Balicek balicek;
    private ArrayList<Karta> kartyNaPloche;
    private int stredX;
    private int skore;
    private int pocetEs;
    private Obrazok novaHra;
    private boolean hracJeVitaz;
    private boolean krupierJeVitaz;
    private final HashMap<String, Obrazok> kartyBalicek;
    private int suma;
    private int vklad;
    private boolean zaevidovanaStavka;
    private boolean allin;

    /**
     * Premieša balícek a uvíta hráča
     */
    public Krupier() {
        this.balicek = new Balicek();
        this.premiesajBalicek();
        this.kartyNaPloche = new ArrayList<>();
        this.kartyBalicek = new HashMap<>();
        this.hraZacala = false;
        this.zaevidovanaStavka = false;
        System.out.println("Vytaj v hre BlackJack!");
        System.out.println("Ak chceš začať hrať tak musíš najskôr vložiť peniaze");
        this.vlozVklad();
        this.vykonajAnimaciu();
        new SetUp();
        this.stredX = 50;
        this.skore = 0;
        this.pocetEs = 0;
        this.krupierStoji = false;
        this.hracJeVitaz = false;
        this.krupierJeVitaz = false;
    }

    /**
     * Metóda ktorá kontroluje vklad od hráča
     */
    private void vlozVklad() {
        System.out.println("Sem zadaj sumu ktorú chceš vložiť: ");
        Scanner scanner = new Scanner(System.in);
        try {
            this.suma = scanner.nextInt();
            if (this.suma > 20) {
                throw new NespravnaSumaException("Neverím že si taký bohatý.");
            } else if (this.suma < 5) {
                System.out.println("Minimálny vklad je 5€, ešte ti chýba " + (5 - this.suma) + "€.");
                this.vlozVklad();
            } else {
                System.out.println("Suma úspešne vložena!");
                System.out.println("Prajem príjemne hranie.");
                System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("Vstup musí byť int(celé čislo).");
            System.out.println();
            this.vlozVklad();
        } catch (NespravnaSumaException e) {
            System.out.println(e.getMessage());
            System.out.println("Suma musí byť menšia ako 20€.");
            System.out.println();
            this.vlozVklad();
        }
        scanner.close();
    }

    /**
     * Premiesa balícek
     */
    public void premiesajBalicek() {
        Random random = new Random();
        for (int j = 0; j < this.balicek.getBalicek().size(); j++) {
            int indexNahodnejKarty = random.nextInt(this.balicek.getBalicek().size());
            Karta nahodnaKarta = this.balicek.getBalicek().get(indexNahodnejKarty);
            this.balicek.getBalicek().remove(indexNahodnejKarty);
            this.balicek.getBalicek().add(0, nahodnaKarta);
        }
    }

    /**
     * Zobere kartu z balícka a polozí ju
     * @param stredX x-ová súradnica karty
     * @param stredY y-ová súradnica karty
     * @return vráti konkrétnu kartu
     */
    public Karta zoberKartu(int stredX, int stredY) {
        Obrazok obrazokKarty = this.balicek.getBalicek().get(0).getObrazok();
        Karta karta = this.balicek.getBalicek().get(0);
        this.balicek.getBalicek().remove(0);
        obrazokKarty.zmenVelkost(75, 150);
        obrazokKarty.zmenPolohu(stredX, stredY);
        obrazokKarty.zobraz();
        this.kartyNaPloche.add(karta);
        int nahoda = new Random().nextInt(1, 5);
        Obrazok obrazok = new Obrazok("karty-zhora/karta"  + nahoda + ".jpg");
        String nazovObrazka = "karty-zhora/karta" + nahoda + ".jpg";
        obrazok.zmenVelkost(75, 150);
        obrazok.zmenPolohu(655, 105);
        obrazok.zobraz();
        this.kartyBalicek.put(nazovObrazka, obrazok);
        return karta;
    }

    /**
     * Metóda ktorá kontroluje skóre
     * @param skoreHraca aktuálne skóre hráca
     * @param skoreKrupiera aktuálne skóre krupiéra
     * @param hracStoji hrac ukoncil svoj tah
     */
    public void kontrolaSkore(int skoreHraca, int skoreKrupiera, boolean hracStoji) {
        if (skoreHraca > 21) {
            System.out.println("Prehral si, tvoje skore prekrocilo 21. Aktualne skore: " + skoreHraca);
            if (!this.allin) {
                this.suma -= this.vklad;
            } else {
                this.suma = 0;
            }
            System.out.println("Aktualna suma: " + this.suma + "€");
            System.out.println();
            this.krupierJeVitaz = true;
            this.krupierStoji = true;
            this.kontrolaSumy();
            this.hratZnova();
        } else if (skoreKrupiera > 21) {
            System.out.println("Vyhral si, krupier prekrocil skore 21, tvoje skore: " + skoreHraca + ", skore krupiera: " + skoreKrupiera);
            if (!this.allin) {
                this.suma += this.vklad;
            } else {
                this.suma += this.vklad;
                this.vklad = this.suma;
            }
            System.out.println("Aktualna suma: " + this.suma + "€");
            System.out.println();
            this.hracJeVitaz = true;
            this.krupierStoji = true;
            this.hratZnova();
        } else if (skoreHraca > skoreKrupiera && hracStoji && this.krupierStoji) {
            System.out.println("Vyhral si, mas vacsie skore ako krupier, tvoje skore: " + skoreHraca + ", skore krupiera: " + skoreKrupiera);
            if (!this.allin) {
                this.suma += this.vklad;
            } else {
                this.suma += this.vklad;
                this.vklad = this.suma;
            }
            System.out.println("Aktualna suma: " + this.suma + "€");
            System.out.println();
            this.hracJeVitaz = true;
            this.krupierStoji = true;
            this.hratZnova();
        } else if (skoreHraca < skoreKrupiera && hracStoji && this.krupierStoji) {
            System.out.println("Prehral si, krupier ma vacsie skore ako ty, tvoje skore: " + skoreHraca + ", skore krupiera: " + skoreKrupiera);
            if (!this.allin) {
                this.suma -= this.vklad;
            } else {
                this.suma = 0;
            }
            System.out.println("Aktualna suma: " + this.suma + "€");
            System.out.println();
            this.krupierJeVitaz = true;
            this.krupierStoji = true;
            this.kontrolaSumy();
            this.hratZnova();
        } else if (skoreHraca == skoreKrupiera) {
            System.out.println("Remíza, nikto nevyhral");
            System.out.println("Aktualna suma: " + this.suma + "€");
            System.out.println();
            this.krupierJeVitaz = true;
            this.hracJeVitaz = true;
            this.krupierStoji = true;
            this.hratZnova();
        }
    }

    /**
     * Kontroluje či suma hráča je nulová
     */
    private void kontrolaSumy() {
        if (this.suma <= 0) {
            Platno.dajPlatno().zatvorPlatno();
        }
    }


    /**
     * Náhodne vykoná animáciu
     */
    public void vykonajAnimaciu() {
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                AnimaciaCisloJedna animaciaCisloJedna = new AnimaciaCisloJedna();
                animaciaCisloJedna.vykonaj();
                break;
            case 1:
                AnimaciaCisloDva animaciaCisloDva = new AnimaciaCisloDva();
                animaciaCisloDva.vykonaj();
                break;
            case 2:
                AnimaciaCisloTri animaciaCisloTri = new AnimaciaCisloTri();
                animaciaCisloTri.vykonaj();
                break;
        }
    }

    /**
     * Vráti stredX
     * @return stredX
     */
    public int getStredX() {
        return this.stredX;
    }

    /**
     * Nastaví stredX
     * @param stredX hodnota streduX
     */
    public void setStredX(int stredX) {
        this.stredX = stredX;
    }

    /**
     * Nastaví skóre
     * @param skore hodnota skóre
     */
    public void setSkore(int skore) {
        this.skore = skore;
    }

    /**
     * Vráti skóre
     * @return skóre
     */
    public int getSkore() {
        return this.skore;
    }

    /**
     * Vráti pocet es
     * @return pocet es
     */
    public int getPocetEs() {
        return this.pocetEs;
    }

    /**
     * nastaví pocet es
     * @param pocetEs pocet es
     */
    public void setPocetEs(int pocetEs) {
        this.pocetEs = pocetEs;
    }

    /**
     * Nastaví krupiera či stojí
     * @param stoji hodnota či stojí
     */
    public void setKrupierStoji(boolean stoji) {
        this.krupierStoji = stoji;
    }

    /**
     * Zobrazí tlačidlo novej hry
     */
    private void hratZnova() {
        this.novaHra = new Obrazok("gesta/novaHra.png");
        this.novaHra.zmenVelkost(150, 100);
        this.novaHra.zmenPolohu(650, 537);
        this.novaHra.zobraz();
    }

    /**
     * Spustí novú hru
     */
    protected void novaHra() {
        if (this.hracJeVitaz || this.krupierJeVitaz) {
            System.out.flush();
            this.balicek.getBalicek().clear();
            this.skore = 0;
            this.krupierStoji = false;
            this.krupierJeVitaz = false;
            this.hracJeVitaz = false;
            this.pocetEs = 0;
            this.stredX = 50;
            this.novaHra.skry();
            for (Karta karta : this.kartyNaPloche) {
                karta.getObrazok().skry();
            }
            this.kartyBalicek.clear();
            this.kartyNaPloche.clear();
            this.balicek.generujBalicek();
            this.premiesajBalicek();
            this.novaHra.skry();
            //this.novaAnimacia();
        }
    }

    /**
     * Vráti či je hráč víťaz
     * @return hrac je vitaz
     */
    public boolean isHracJeVitaz() {
        return this.hracJeVitaz;
    }

    /**
     * Vráti či je krupier víťaz
     * @return krupier je vitaz
     */
    public boolean isKrupierJeVitaz() {
        return this.krupierJeVitaz;
    }

    /**
     * Zaeviduje stávku od hráča
     * @param kolko suma
     */
    protected void zaevidujStavku(String kolko) {
        this.allin = kolko.equals("allin");
        switch (kolko) {
            case "5":
                this.vklad = 5;
                break;
            case "10":
                this.vklad = 10;
                break;
            case "15":
                this.vklad = 15;
                break;
            case "20":
                this.vklad = 20;
                break;
            case "allin":
                this.vklad = this.suma;
                break;
        }
        this.zaevidovanaStavka = false;
        this.hraZacala = true;
        System.out.println("Stávka " + this.vklad + "€");

    }

    /**
     * Nastaví evidovanu stávku
     * @param evidovanieStavky zaeviduje stávku
     */
    protected void setEvidovanieStavky(boolean evidovanieStavky) {
        this.zaevidovanaStavka = evidovanieStavky;
    }

    /**
     * Vráti či hra začala
     * @return hra začala
     */
    public boolean isHraZacala() {
        return this.hraZacala;
    }

    /**
     * Vráti či je zaevidovaná stávka
     * @return zaevidovaná stávka
     */
    public boolean isZaevidovanaStavka() {
        return this.zaevidovanaStavka;
    }

    /**
     * Vráti sumu hráča
     * @return suma
     */
    public int getSuma() {
        return this.suma;
    }

    /**
     * Vráti karty na ploche
     * @return karty
     */
    public ArrayList<Karta> getKartyNaPloche() {
        return this.kartyNaPloche;
    }

    /**
     * Zisti či krupier stojí
     * @return krupier stoji
     */
    public boolean isKrupierStoji() {
        return this.krupierStoji;
    }

    /**
     * Vráti vklad
     * @return vklad
     */
    public int getVklad() {
        return this.vklad;
    }

    /**
     * Zistí či stávka je all in
     * @return all in
     */
    public boolean isAllin() {
        return this.allin;
    }

    /**
     * Vráti či hra začala
     * @param hraZacala hra zacala
     */
    public void setHraZacala(boolean hraZacala) {
        this.hraZacala = hraZacala;
    }

    /**
     * Nastaví či je hráč víťaz
     * @param hracJeVitaz hrac je víťaz
     */
    public void setHracJeVitaz(boolean hracJeVitaz) {
        this.hracJeVitaz = hracJeVitaz;
    }

    /**
     * Nastaví či je krupier víťaz
     * @param krupierJeVitaz krupier je vitaz
     */
    public void setKrupierJeVitaz(boolean krupierJeVitaz) {
        this.krupierJeVitaz = krupierJeVitaz;
    }

    /**
     * Nastaví sumu
     * @param suma velkost sumy
     */
    public void setSuma(int suma) {
        this.suma = suma;
    }

    /**
     * Nastaví vklad
     * @param vklad velkost vkladu
     */
    public void setVklad(int vklad) {
        this.vklad = vklad;
    }

    /**
     * Nastaví all in
     * @param allin all in
     */
    public void setAllin(boolean allin) {
        this.allin = allin;
    }

    /**
     * Vráti balícek
     * @return balicek
     */
    public Balicek getBalicek() {
        return this.balicek;
    }

    /**
     * Pripravi platno na load
     */
    protected void pripravLoad() {
        this.balicek.getBalicek().clear();
        for (Karta karta : this.kartyNaPloche) {
            karta.getObrazok().skry();
        }
        this.kartyNaPloche.clear();
        this.novaHra.skry();
        for (Obrazok value : this.kartyBalicek.values()) {
            value.skry();
        }
        this.kartyBalicek.clear();
    }

    /**
     * Nastaví balícek
     * @param balicek balicek
     */
    public void setBalicek(Balicek balicek) {
        this.balicek = balicek;
    }

    /**
     * Nastaví karty ktoré su na ploche
     * @param kartyNaPloche karty
     */
    public void setKartyNaPloche(ArrayList<Karta> kartyNaPloche) {
        this.kartyNaPloche = kartyNaPloche;
    }

    /**
     * Rozmiestni karty na platno
     */
    protected void nacitajLoad() {
        for (Obrazok value : this.kartyBalicek.values()) {
            value.zobraz();
        }
        int celkoveSkore = 0;
        for (Karta karta : this.kartyNaPloche) {
            celkoveSkore += karta.getHodnota();
        }
        int skoreHraca = celkoveSkore - this.skore;
        int pomocna = 0;
        int stredH = 50;
        int stredK = 50;
        for (Karta karta : this.kartyNaPloche) {
            Obrazok obrazok = karta.getObrazok();
            obrazok.zmenVelkost(75, 150);
            if (pomocna < skoreHraca) {
                obrazok.zmenPolohu(stredH, 400);
                pomocna += karta.getHodnota();
                stredH += 100;
            } else {
                obrazok.zmenPolohu(stredK, 150);
                stredK += 100;
            }
            obrazok.zobraz();
        }
    }

    /**
     * Vráti obrazok novej hry
     * @return nova hra
     */
    public Obrazok getNovaHra() {
        return this.novaHra;
    }
}

