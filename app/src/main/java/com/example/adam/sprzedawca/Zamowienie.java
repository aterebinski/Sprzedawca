package com.example.adam.sprzedawca;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Text;

/**
 * Created by Adam on 2015-10-03.
 */
public class Zamowienie {
    private Integer klient_id;
    private Integer towar_id;
    private String data;
    private Float sztuk;
    private Float cena;


    public Integer getKlient_id() {
        return klient_id;
    }

    public void setKlient_id(Integer klient_id) {
        this.klient_id = klient_id;
    }

    public Integer getTowar_id() {
        return towar_id;
    }

    public void setTowar_id(Integer towar_id) {
        this.towar_id = towar_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getSztuk() {
        return sztuk;
    }

    public void setSztuk(Float sztuk) {
        this.sztuk = sztuk;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public void dodajZamowienie(DbHelper dbHelper, Integer klient_id, Integer towar_id, String data, Float sztuk, Float cena){
        ContentValues wartosci = new ContentValues();
        wartosci.put("klient_id", klient_id);
        wartosci.put("towar_id", towar_id);
        wartosci.put("data", data);
        wartosci.put("sztuk", sztuk);
        wartosci.put("cena", cena);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insertOrThrow("zamowienia",null, wartosci);
    }
}
