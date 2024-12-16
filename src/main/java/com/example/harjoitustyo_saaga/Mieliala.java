package com.example.harjoitustyo_saaga;

import java.io.Serializable;

/** Luokka kuvastaa yhta mielialapaivakirjan postausta.
 * Sisaltaa pvm:n, paivittaisen tunnetilan, seka tarvittavat lisatiedot
 * @author Saaga Kovapohja
 * @version 1.0 2023/03/20
 */
public class Mieliala implements Serializable {
    /**
     * paivamaara merkkijonona.
     */
    private String pvm;
    /**
     * tunnetila kokonaislukuna.
     */
    private int tunne;
    /**
     * lisatiedot merkkijonona.
     */
    private String lisatieto;

    /**
     * Parametriton alustaja mielialapostaukselle
     * Luo tyhjan postauksen
     */
    public Mieliala() {
    }
    /**
     * Mielialapostaus perustiedoilla.
     * @param pvm String postauksen paivamaara
     * @param tunne int paivittainen tunnetila
     * @param lisatieto String tunnetilaan liittyvat lisatiedot
     */
    public Mieliala(String pvm, int tunne, String lisatieto) {
        this.setPvm(pvm);
        this.setTunne(tunne);
        this.setLisatieto(lisatieto);
    }

    /**
     * Palauttaa paivamaaran
     * @return String pvm
     */
    public String getPvm() {
        return pvm;
    }

    /**
     * Asettaa paivamaaran
     * @param pvm String
     */
    public void setPvm(String pvm) {
        this.pvm = pvm;
    }

    /**
     * Palauttaa tunnetilan
     * @return int tunne
     */
    public int getTunne() {
        return tunne;
    }

    /**
     * Asettaa tunnetilan kokonaislukuna
     * @param tunne int
     */
    public void setTunne(int tunne) {
        this.tunne = tunne;
    }

    /**
     * Palauttaa lisatiedot
     * @return String lisatieto
     */
    public String getLisatieto() {
        return lisatieto;
    }

    /**
     * Asettaa lisatiedot merkkijonona
     * @param lisatieto String
     */
    public void setLisatieto(String lisatieto) {
        this.lisatieto = lisatieto;
    }

    /**
     * Palauttaa luokasta luodun olion tiedot sopivana merkkijonona.
     * Tunne muuttujasta tehdaan merkkijono vastine,
     * jotta saadaan palautettua tunnetilan arvo merkkijonona kokonaisluvun sijasta.
     * @return String
     */
    public String toString() {
        String sTunne = null;
        if (tunne == 1) {
            sTunne = "Iloinen";
        } else if (tunne == 2) {
            sTunne = "Hyvä";
        } else if (tunne == 3) {
            sTunne = "Neutraali";
        } else if (tunne == 4) {
            sTunne = "Surullinen";
        } else if (tunne == 5) {
            sTunne = "Vihainen";
        }
        return "Pvm: " + pvm + "\nTunne: " + sTunne
                + "\nLisätiedot: " + lisatieto + "\n\n";
    }

    /**
     * Testipaaohjelma
     * @param args ei parametreja kaytossa
     */
    public static void main(String[] args) {
        Mieliala olo1 = new Mieliala("15.03.2023",3,"neutraali päivä, olo ei ole huono eikä hyvä");
        Mieliala olo2 = new Mieliala("16.03.2023",1,"kiva päivä, näin kavereita ja oli hauskaa");
        Mieliala olo3 = new Mieliala();
        olo3.setPvm("17.03.2023");
        olo3.setTunne(5);
        olo3.setLisatieto("Väsyttää, en nukkunut kunnolla");

        System.out.println("Pvm: " + olo1.getPvm()+ '\n' + "Mieliala: " + olo1.getTunne() + "\n" + "Lisätietoja: " + olo1.getLisatieto());
        System.out.println("\nPvm: " + olo2.getPvm()+ '\n' + "Mieliala: " + olo2.getTunne() + "\n" + "Lisätietoja: " + olo2.getLisatieto());
        System.out.println("\nPvm: " + olo3.getPvm()+ '\n' + "Mieliala: " + olo3.getTunne() + "\n" + "Lisätietoja: " + olo3.getLisatieto());

    }
}
