package com.example.adam.sprzedawca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam on 2015-10-03.
 */
public class Klient {
    private Integer id = null;
    private String nazwa;
    private String adres;
    private String miejscowosc;
    private String kod;
    private String nip;
    private String regon;
    private String telefon;

    public Klient(String nazwa, String adres, String miejscowosc, String kod, String nip, String regon, String telefon) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.miejscowosc = miejscowosc;
        this.kod = kod;
        this.nip = nip;
        this.regon = regon;
        this.telefon = telefon;
    }

    public Klient() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void dodajKlienta(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues wartosci = new ContentValues();
        wartosci.put("nazwa", getNazwa() );
        wartosci.put("adres", getAdres());
        wartosci.put("miejscowosc", getMiejscowosc());
        wartosci.put("kod",getKod());
        wartosci.put("nip",getNip());
        wartosci.put("regon",getRegon());
        wartosci.put("telefon", getTelefon());

        db.insertOrThrow("klienci", null, wartosci);
    }

    public void aktualizujKlienta(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args={""+getId()};
        ContentValues wartosci = new ContentValues();

        wartosci.put("nazwa",getNazwa());
        wartosci.put("adres", getAdres());
        wartosci.put("miejscowosc", getMiejscowosc());
        wartosci.put("kod",getKod());
        wartosci.put("nip",getNip());
        wartosci.put("regon",getRegon());
        wartosci.put("telefon", getTelefon());
        db.update("klienci", wartosci, "id=?", args);
    }

    public void kasujKlienta(Context context, Integer id){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args = {""+id};
        db.delete("klienci", "id=?", args);
    }

    public static List<Klient> dajWszystkie(Context context){

        List<Klient> klienci = new LinkedList<Klient>();
        String[] kolumny = {"id","nazwa","adres","miejscowosc","kod","nip","regon","telefon"};

        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("klienci", kolumny, null, null, null, null, null);
        while (cursor.moveToNext()){
            Klient klient = new Klient();
            klient.setId(cursor.getInt(0));
            klient.setNazwa(cursor.getString(0));
            klient.setAdres(cursor.getString(1));
            klient.setMiejscowosc(cursor.getString(2));
            klient.setKod(cursor.getString(3));
            klient.setNip(cursor.getString(4));
            klient.setRegon(cursor.getString(5));
            klient.setTelefon(cursor.getString(6));
            klienci.add(klient);
        }

        return klienci;
    }

    public void wczytajKlienta(Context context, Integer id){
//        Klient klient = new Klient();
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] kolumny = {"id","nazwa","adres","miejscowosc","kod","nip","regon","telefon"};
        String[] args = {""+id};

        Cursor cursor = db.query("klienci",kolumny,"id = ?",args, null, null, null);
        if (cursor != null) {
//            klient.setId(cursor.getInt(0));
//            klient.setNazwa(cursor.getString(0));
//            klient.setAdres(cursor.getString(1));
//            klient.setMiejscowosc(cursor.getString(2));
//            klient.setKod(cursor.getString(3));
//            klient.setNip(cursor.getString(4));
//            klient.setRegon(cursor.getString(5));
//            klient.setTelefon(cursor.getString(6));

            setId(cursor.getInt(0));
            setNazwa(cursor.getString(0));
            setAdres(cursor.getString(1));
            setMiejscowosc(cursor.getString(2));
            setKod(cursor.getString(3));
            setNip(cursor.getString(4));
            setRegon(cursor.getString(5));
            setTelefon(cursor.getString(6));
        }

//        return klient;
    }


}
