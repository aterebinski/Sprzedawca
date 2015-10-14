package com.example.adam.sprzedawca;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Adam on 2015-10-03.
 */
public class Towar {
    private Integer id;
    private String nazwa;
    private Float cena;
    private Float dostepne; //ilosc dostepnych sztuk w magazynie
    private Integer regal;
    private Integer polka;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public Float getDostepne() {
        return dostepne;
    }

    public void setDostepne(Float dostepne) {
        this.dostepne = dostepne;
    }

    public Integer getRegal() {
        return regal;
    }

    public void setRegal(Integer regal) {
        this.regal = regal;
    }

    public Integer getPolka() {
        return polka;
    }

    public void setPolka(Integer polka) {
        this.polka = polka;
    }

    public void dodajTowar(DbHelper dbHelper, String nazwa, Float cena, String dostepne, String regal, String polka){
        ContentValues wartosci = new ContentValues();
        wartosci.put("nazwa", nazwa);
        wartosci.put("cena",cena);
        wartosci.put("dostepne",dostepne);
        wartosci.put("regal",regal);
        wartosci.put("polka",polka);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insertOrThrow("towary", null, wartosci);
    }


}
