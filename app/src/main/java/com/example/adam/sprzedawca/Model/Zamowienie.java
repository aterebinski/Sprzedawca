package com.example.adam.sprzedawca.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.adam.sprzedawca.Db.DbHelper;

import java.util.List;

/**
 * Created by Adam on 2015-10-03.
 */
public class Zamowienie {
    private Integer id = null;
    private Integer klient_id;
    private String klient_name;
    private Integer towar_id;
    private String towar_name;
    private String data;
    private Float sztuk;
    private Float cena;

    public Zamowienie(Integer klient_id, Integer towar_id, String data, Float sztuk, Float cena) {
        this.klient_id = klient_id;
        this.towar_id = towar_id;
        this.data = data;
        this.sztuk = sztuk;
        this.cena = cena;
    }

    public Zamowienie() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getKlient_name() {
        return klient_name;
    }

    public void setKlient_name(String klient_name) {
        this.klient_name = klient_name;
    }

    public String getTowar_name() {
        return towar_name;
    }

    public void setTowar_name(String towar_name) {
        this.towar_name = towar_name;
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

    public void dodajZamowienie(Context context){
        ContentValues wartosci = new ContentValues();
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        wartosci.put("klient_id", getKlient_id());
        wartosci.put("towar_id", getTowar_id());
        wartosci.put("data", getData());
        wartosci.put("sztuk", getSztuk());
        wartosci.put("cena", getCena());
        db.insertOrThrow("zamowienia", null, wartosci);
    }

    public void aktualizujZamowienie(Context context){
        ContentValues wartosci = new ContentValues();
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args = {""+getId()};

        wartosci.put("klient_id",getKlient_id());
        wartosci.put("towar_id",getTowar_id());
        wartosci.put("data",getData());
        wartosci.put("sztuk",getSztuk());
        wartosci.put("cena",getCena());
        db.update("zamowienia", wartosci, "id=?", args);
    }

    public void kasujZamowienie(Context context, Integer id){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args = {""+id};
        db.delete("zamowienia", "id=?", args);
    }

    public static List<Zamowienie> dajWszystkie(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Zamowienie> zamowienia = null;
        String[] kolumny = {"id","klient_id", "towar_id", "data","sztuk","cena"};
        String[] klient_kolumny = {"nazwa"};
        String[] klient_args = null;
        String[] towar_kolumny = {"nazwa"};
        String[] towar_args = null;

        Cursor cursor = db.query("zamowienie",kolumny,null, null, null, null, null );
        while(cursor.moveToNext()){
            klient_args[0] = Integer.toString(cursor.getInt(1));
            Cursor cursor_klient = db.query("klient",klient_kolumny,"id = ?", null, null, null, null );
            towar_args[0] = Integer.toString(cursor.getInt(2));
            Cursor cursor_towar = db.query("towar",klient_kolumny,"id = ?", null, null, null, null );
            Zamowienie zamowienie = new Zamowienie();
            zamowienie.setId(cursor.getInt(0));
            zamowienie.setKlient_id(cursor.getInt(1));
            zamowienie.setTowar_id(cursor.getInt(2));
            zamowienie.setData(cursor.getString(0));
            zamowienie.setSztuk(cursor.getFloat(0));
            zamowienie.setCena(cursor.getFloat(1));
            zamowienie.setKlient_name(cursor_klient.getString(0));
            zamowienie.setTowar_name(cursor_towar.getString(0));
            zamowienia.add(zamowienie);
        }
        return zamowienia;
    }

    public void wczytajZamowienie(Context context, Integer id){
//        Zamowienie zamowienie = new Zamowienie();
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] kolumny = {"id","klient_id", "towar_id", "data","sztuk","cena"};
        String[] args = {""+id};

        Cursor cursor = db.query("zamowienia",kolumny,"id=?",args,null,null,null);
        if (cursor != null) {
            setId(cursor.getInt(0));
            setKlient_id(cursor.getInt(1));
            setTowar_id(cursor.getInt(2));
            setData(cursor.getString(0));
            setSztuk(cursor.getFloat(0));
            setCena(cursor.getFloat(1));

//            zamowienie.setId(cursor.getInt(0));
//            zamowienie.setKlient_id(cursor.getInt(1));
//            zamowienie.setTowar_id(cursor.getInt(2));
//            zamowienie.setData(cursor.getString(0));
//            zamowienie.setSztuk(cursor.getFloat(0));
//            zamowienie.setCena(cursor.getFloat(1));
        }

//        return zamowienie;
    }
}
