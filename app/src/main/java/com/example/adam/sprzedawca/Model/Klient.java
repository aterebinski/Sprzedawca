package com.example.adam.sprzedawca.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.adam.sprzedawca.Db.DbHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam on 2015-10-03.
 */
public class Klient implements Parcelable{
    private Integer id;
    private String nazwa;
    private String adres;
    private String miejscowosc;
    private String kod;
    private String nip;
    private String regon;
    private String telefon;
    private Integer del;

    public final static int REQUEST_CODE_DODAJ_KLIENTA = 1;
    public final static int REQUEST_CODE_USUN_KLIENTOW = 2;
    public final static int REQUEST_CODE_EDYTUJ_KLIENTA = 11;
    public final static int RESULT_CODE_OK = 1;


    public Klient(String nazwa, String adres, String miejscowosc, String kod, String nip, String regon, String telefon) {
        this.id=0;
        this.nazwa = nazwa;
        this.adres = adres;
        this.miejscowosc = miejscowosc;
        this.kod = kod;
        this.nip = nip;
        this.regon = regon;
        this.telefon = telefon;
        this.del = 0;
    }

    public Klient() {

    }

    protected Klient(Parcel in) {
        id = in.readInt();
        nazwa = in.readString();
        adres = in.readString();
        miejscowosc = in.readString();
        kod = in.readString();
        nip = in.readString();
        regon = in.readString();
        telefon = in.readString();
        del = in.readInt();
    }

    public static final Creator<Klient> CREATOR = new Creator<Klient>() {
        @Override
        public Klient createFromParcel(Parcel in) {
            return new Klient(in);
        }

        @Override
        public Klient[] newArray(int size) {
            return new Klient[size];
        }
    };

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

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
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
        wartosci.put("del",getDel());

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
        wartosci.put("del",getDel());
        db.update("klienci", wartosci, "id=?", args);
    }

//    public static void kasujKlienta(Context context, Integer id){
//        DbHelper dbHelper = DbHelper.getDbHelper(context);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String[] args = {""+id};
////        db.delete("klienci", "id=?", args);
//        ContentValues values = new ContentValues();
//        values.put("del",1);
//        db.update("zamowienie",values,"id=?",args);
//    }

    public void kasujKlienta(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args = {""+this.getId()};
//        db.delete("klienci", "id=?", args);
        ContentValues values = new ContentValues();
        values.put("del",1);
        db.update("klienci",values,"id=?",args);
    }

    public static void kasujWszystkie(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete("klienci", "", null);
        ContentValues values = new ContentValues();
        values.put("del",1);
        db.update("klienci",values,"",null);
    }

    public static List<Klient> dajWszystkie(Context context){

        List<Klient> klienci = new LinkedList<Klient>();
        String[] kolumny = {"id","nazwa","adres","miejscowosc","kod","nip","regon","telefon","del"};

        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("klienci", kolumny, "del=0", null, null, null, null);
        while (cursor.moveToNext()){
            Klient klient = new Klient();
            klient.setId(cursor.getInt(0));
            klient.setNazwa(cursor.getString(1));
            klient.setAdres(cursor.getString(2));
            klient.setMiejscowosc(cursor.getString(3));
            klient.setKod(cursor.getString(4));
            klient.setNip(cursor.getString(5));
            klient.setRegon(cursor.getString(6));
            klient.setTelefon(cursor.getString(7));
            klient.setDel(cursor.getInt(8));
            klienci.add(klient);
        }

        cursor.close();

        return klienci;
    }

    public void wczytajKlienta(Context context, Integer id){
//        Klient klient = new Klient();
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] kolumny = {"id","nazwa","adres","miejscowosc","kod","nip","regon","telefon","del"};
        String[] args = {""+id};

        Cursor cursor = db.query("klienci",kolumny,"id = ? , del = 0",args, null, null, null);
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
            setNazwa(cursor.getString(1));
            setAdres(cursor.getString(2));
            setMiejscowosc(cursor.getString(3));
            setKod(cursor.getString(4));
            setNip(cursor.getString(5));
            setRegon(cursor.getString(6));
            setTelefon(cursor.getString(7));
            setDel(cursor.getInt(8));
        }

//        return klient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nazwa);
        dest.writeString(adres);
        dest.writeString(miejscowosc);
        dest.writeString(kod);
        dest.writeString(nip);
        dest.writeString(regon);
        dest.writeString(telefon);
        dest.writeInt(del);
    }
}
