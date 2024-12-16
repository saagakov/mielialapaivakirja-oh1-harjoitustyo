package com.example.harjoitustyo_saaga;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

/**
 * Mielialapaivakirjan kayttoliittyma, joka kasittelee
 * mieliala luokkaa. Kayttoliittymalla voidaan lisata postaukseen
 * tarvittavat tiedot (pvm, tunnetila, lisatiedot), seka
 * tallentaa ne tiedostoon ja lukea tiedostosta postaushistoria.
 * @author Saaga Kovapohja
 * @version 1.0
 */
public class MielialaPaivakirja extends Application {
    /**
     * Arraylist mieliala luokasta luoduille olioille.
     */
    private ArrayList<Mieliala> mielialat = new ArrayList<Mieliala>();
    /**
     * Tiedosto minne mielialapostaukset tallennetaan.
     */
    private File tiedosto = new File("mielialat.dat");
    /**
     * Label, joka ohjeistaa kayttajaa antamaan paivamaaran.
     */
    private final Label lbPvm = new Label("Anna pvm:  ");
    /**
     * Label, joka pyytaa kayttajaa antamaan lisatietoja
     */
    private final Label lbTiedot = new Label("Lisätietoja:  ");
    /**
     * Tekstikentta, mihin postauksen paivamaara syotetaan.
     */
    private final TextField tfPvm = new TextField();
    /**
     * Tekstialue postauksen lisatiedoille
     */
    private final TextArea taTiedot = new TextArea();
    /**
     * Tekstialue jossa naytetaan paivakirjahistoria
     */
    private final TextArea taHistoria = new TextArea();
    /**
     * ToggleGroup RadioButtoneille
     */
    private final ToggleGroup ryhma = new ToggleGroup();
    /**
     * RadioButton tunteelle iloinen (1).
     */
    private final RadioButton rb1 = new RadioButton("Iloinen");
    /**
     * RadioButton tunteelle hyva (2).
     */
    private final RadioButton rb2 = new RadioButton("Hyvä");
    /**
     * RadioButton tunteelle neutraali (3).
     */
    private final RadioButton rb3 = new RadioButton("Neutraali");
    /**
     * RadioButton tunteelle surullinen (4).
     */
    private final RadioButton rb4 = new RadioButton("Surullinen");
    /**
     * RadioButton tunteelle vihainen (5).
     */
    private final RadioButton rb5 = new RadioButton("Vihainen");
    /**
     * Nappi, joka tallentaa postaukseen annetut tiedot ensiksi oliona ArrayListiin
     * ja sitten ArrayListin sisallon tiedostoon
     */
    private final Button btTallenna = new Button("Tallenna");
    /**
     * Nappi, joka lukee tiedoston, ja asettaa tiedoston sisallon
     * nakyville tekstialueelle (taHistoria)
     */
    private final Button btHistoria = new Button("Näytä historia: ");
    /**
     * Nappi, joka lukee tiedoston, ja laskee siita tunne muuttujan keskiarvon,
     * seka asettaa sen nakyville seuraavaan tekstikenttaan
     */
    private final Button btAvg = new Button("Keskiarvo mieliala: ");
    /**
     * Tekstikentta keskimaaraiselle tunnetilalle
     */
    private final TextField tfAvg = new TextField();
    /**
     * Ohjelmaikkunan ulkoasun, kaynnistyksen seka
     * toiminnallisuuden maarittely
     */
    @Override
    public void start(Stage stage) throws IOException {
        //muokataan tekstikenttien/-alueiden kokoja
        tfPvm.setPrefWidth(80);
        taTiedot.setPrefHeight(100);
        taHistoria.setPrefWidth(600);
        taHistoria.setPrefHeight(100);
        tfAvg.setPrefWidth(80);

        //luodaan ScrollPanet tekstialueille
        ScrollPane rulla1 = new ScrollPane(taTiedot);
        ScrollPane rulla2 = new ScrollPane(taHistoria);

        //HBox, jotta saadaan pvm label ja tekstikenttä
        //nätisti vierekkäin, sekä muokataan sen asettelua
        HBox hbPvm = new HBox(lbPvm, tfPvm);
        hbPvm.setPadding(new Insets(10,10,10,10));

        //Sama homma lisätiedoille
        HBox hbTiedot = new HBox(lbTiedot, rulla1);
        hbTiedot.setPadding(new Insets(10,10,10,10));


        //Hbox, jotta radiobuttonit saadaan vierekkäin
        //sekä asetellaan ne hyvin
        HBox hbNapit = new HBox(rb1,rb2,rb3,rb4,rb5);
        hbNapit.setPadding(new Insets(5,10,10,25));
        hbNapit.setSpacing(43);

        //Lisätään radiobuttonit ToggleGroup:iin
        rb1.setToggleGroup(ryhma);
        rb2.setToggleGroup(ryhma);
        rb3.setToggleGroup(ryhma);
        rb4.setToggleGroup(ryhma);
        rb5.setToggleGroup(ryhma);

        // HBox tallenna napille ja asetellaan se oikeaan reunaan
        HBox hbTallenna = new HBox(btTallenna);
        hbTallenna.setPadding(new Insets(0,0,0,500));

        // oma gridpane paneeli keskiarvon napille ja teksikentälle
        GridPane gpKeskiarvo = new GridPane();
        gpKeskiarvo.addRow(0,btAvg,tfAvg);
        gpKeskiarvo.setHgap(10);


        //luodaan "pää"paneeli
        GridPane paneeli = new GridPane();
        paneeli.setAlignment(Pos.CENTER);
        //lisätään rakoja solujen väliin
        paneeli.setHgap(10);
        paneeli.setVgap(10);

        //lisätään solmut ja muut paneelit
        paneeli.addRow(0, hbPvm);
        paneeli.addRow(2, hbNapit);
        paneeli.addRow(3, hbTiedot);
        paneeli.addRow(4, hbTallenna);
        paneeli.addRow(5, btHistoria);
        paneeli.addRow(6, rulla2);
        paneeli.addRow(7, gpKeskiarvo);

        try {
            //Liitetään kuva tunnetiloista,
            // ja määritellään sen koko sopivaksi
            ImageView ivTunteet = new ImageView(new Image("C:/Users/saaga/OneDrive/Työpöytä/ohjelmointi 2/ohjelmat/HarjoitusTyo_Saaga/tunteet.png"));
            ivTunteet.setFitWidth(500);
            ivTunteet.setFitHeight(100);
            // HBox kuvalle
            HBox hbTunteet =new HBox(ivTunteet);
            // lisätään kuva osaksi GridPane paneelia
            paneeli.addRow(1, hbTunteet);
        } catch (RuntimeException e) {
            System.out.println("Virhe, kuvan tiedostopolku on luultavasti väärin. Suoritetaan ohjelma ilman kuvaa.");
        }

        //asetetaan tekstialueet muokkaamattomiksi
        taHistoria.setEditable(false);
        tfAvg.setEditable(false);

        //lisätään napeille toiminnallisuutta
        btTallenna.setOnAction(e -> tallennaMieliala());
        btHistoria.setOnAction(e -> naytaHistoria());
        btAvg.setOnAction(e -> naytaKeskiarvo());

        Scene scene = new Scene(paneeli, 700, 600);
        stage.setTitle("Mielialapäiväkirja");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodi luo kayttajan syotteesta mieliala olion, tallentaa sen arraylist:iin,
     * joka taas tallennetaan tiedostoon
     */
    private void tallennaMieliala() {
        String tunne = ((RadioButton)ryhma.getSelectedToggle()).getText();

        //oliota luodessa tunteen pitää olla kokonaislukuna merkkijonon sijasta
        int tunneArvo = 0;
        if (tunne == "Iloinen") {
            tunneArvo = 1;
        } else if (tunne == "Hyvä") {
            tunneArvo = 2;
        } else if (tunne == "Neutraali") {
            tunneArvo = 3;
        } else if (tunne == "Surullinen") {
            tunneArvo = 4;
        } else if (tunne == "Vihainen") {
            tunneArvo = 5;
        }

        Mieliala mieliala = new Mieliala(tfPvm.getText(), tunneArvo, taTiedot.getText());
        mielialat.add(mieliala);

        try {
            FileOutputStream tiedosto = new FileOutputStream("mielialat.dat");
            ObjectOutputStream olioVirta = new ObjectOutputStream(tiedosto);
            olioVirta.writeObject(mielialat);
            System.out.println("kirjoitus onnistui");
            tiedosto.close();
            olioVirta.close();
        } catch (IOException ex) {
            System.out.println("Virhe");
        }
        //asetetaan kaikki tekstikentät ja radiobuttonit tyhjiksi
        //tallennuksen jälkeen
        tfPvm.setText("");
        taTiedot.setText("");
        if (rb1.isSelected()) {
            rb1.setSelected(false);
        } else if (rb2.isSelected()) {
            rb2.setSelected(false);
        } else if (rb3.isSelected()) {
            rb3.setSelected(false);
        } else if (rb3.isSelected()) {
            rb3.setSelected(false);
        } else if (rb4.isSelected()) {
            rb4.setSelected(false);
        } else if (rb5.isSelected()) {
            rb5.setSelected(false);
        }
    }

    /**
     * Metodi lukee tiedot tiedostosta
     */
    private void lueTiedosto() {
        if (tiedosto.exists()) {
            try {
                FileInputStream tiedosto = new FileInputStream("mielialat.dat");
                ObjectInputStream olioVirta = new ObjectInputStream(tiedosto);
                mielialat = (ArrayList<Mieliala>) olioVirta.readObject();
                System.out.println("Tiedoston lukeminen onnistui");
                tiedosto.close();
                olioVirta.close();
            } catch (EOFException e) {
                System.err.println("Virhe, tiedoston lopun ohi yritettiin lukea");
            } catch (IOException e) {
                System.err.println("Virhe, tiedosto löydetty, mutta lukeminen päättyi");
            } catch (ClassNotFoundException e) {
                System.err.println("Virhe, ei löytänyt sarjallistettua luokkaa");
            }
        } else {
            System.out.println("Tiedostoa ei löydy.");
        }
    }

    /**
     * Metodi hyoduntaa lueTiedosto() metodia, ja asettaa paivakirja historian nakyville tekstialueelle
     */
    private void naytaHistoria() {
        taHistoria.setText("");
        tfAvg.setText("");
        lueTiedosto();
        for (Mieliala mieliala : mielialat) {
            taHistoria.appendText(mieliala.toString());
        }
    }

    /**
     * Metodi hyodyntaa lueTiedosto() metodia ja laskee sen tiedoista tunne muuttujan keskiarvon,
     * ja asettaa taman keskiarvon nakyville
     */
    private void naytaKeskiarvo() {
        double laskuri = 0;
        double summa = 0;
        double keskiarvo = 0;

        lueTiedosto();
        for (Mieliala mieliala : mielialat) {
            summa += mieliala.getTunne();
            laskuri += 1;
        }
        keskiarvo = Math.round(summa/laskuri);
        String kaTunne = null;
        if (keskiarvo == 1) {
            kaTunne = "Iloinen";
        } else if (keskiarvo == 2) {
            kaTunne = "Hyvä";
        } else if (keskiarvo == 3) {
            kaTunne = "Neutraali";
        } else if (keskiarvo == 4) {
            kaTunne = "Surullinen";
        } else if (keskiarvo == 5) {
            kaTunne = "Vihainen";
        }
        tfAvg.setText(kaTunne);
    }

    /**
     * Metodi, joka kaynnistaa ohjelman
     * @param args ei parametreja kaytossa
     */
    public static void main(String[] args) {
        launch();
    }
}


