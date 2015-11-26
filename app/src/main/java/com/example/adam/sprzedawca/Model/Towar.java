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
public class Towar implements Parcelable{
    private Integer id;
    private String nazwa;
    private Float cena;
    private Float dostepne; //ilosc dostepnych sztuk w magazynie
    private Integer regal;
    private Integer polka;
    private Integer del;

    public Towar(String nazwa, Float cena, Float dostepne, Integer regal, Integer polka) {
        this.id=null;
        this.nazwa = nazwa;
        this.cena = cena;
        this.dostepne = dostepne;
        this.regal = regal;
        this.polka = polka;
        this.del = 0;
    }

    public Towar() {
    }

    protected Towar(Parcel in) {
        id = in.readInt();
        nazwa = in.readString();
        cena = in.readFloat();
        dostepne = in.readFloat();
        regal = in.readInt();
        polka = in.readInt();
        del = in.readInt();
    }

    public static final Creator<Towar> CREATOR = new Creator<Towar>() {
        @Override
        public Towar createFromParcel(Parcel in) {
            return new Towar(in);
        }

        @Override
        public Towar[] newArray(int size) {
            return new Towar[size];
        }
    };

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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public void dodajTowar(Context context){
        ContentValues wartosci = new ContentValues();
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        wartosci.put("nazwa", getNazwa());
        wartosci.put("cena",getCena());
        wartosci.put("dostepne",getDostepne());
        wartosci.put("regal",getRegal());
        wartosci.put("polka",getPolka());
        wartosci.put("del",getDel());

        db.insertOrThrow("towary", null, wartosci);
    }

    public void aktualizujTowar(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args = {""+getId()};
        ContentValues wartosci = new ContentValues();
        wartosci.put("nazwa",getNazwa());
        wartosci.put("cena",getCena());
        wartosci.put("dostepne",getDostepne());
        wartosci.put("regal",getRegal());
        wartosci.put("polka",getPolka());
        wartosci.put("del",getDel());
        db.update("towary", wartosci, "id=?", args);
    }

    public void kasujTowar(Context context, Integer id){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args = {""+id};
//        db.delete("towary", "id=?", args);
        ContentValues values = new ContentValues();
        values.put("del",1);
        db.update("towary", values, "id=?", args);
    }

    public static void kasujWszystkie(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete("towary", "", null);
        ContentValues values = new ContentValues();
        values.put("del",1);
        db.update("towary",values,"",null);
    }

    public static List<Towar> dajWszystkie (Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Towar> towary = new LinkedList<Towar>();

        String[] kolumny = {"id","nazwa","cena","dostepne","regal","polka","del"};
        Cursor kursor = db.query("towary",kolumny,"del=0",null,null,null,null);
        while (kursor.moveToNext()){
            Towar towar = new Towar();

//            towar.setId(kursor.getInt(0));
//            towar.setNazwa(kursor.getString(0));
//            towar.setCena(kursor.getFloat(0));
//            towar.setDostepne(kursor.getFloat(1));
//            towar.setRegal(kursor.getInt(1));
//            towar.setPolka(kursor.getInt(2));

            towar.setId(kursor.getInt(0));
            towar.setNazwa(kursor.getString(1));
            towar.setCena(kursor.getFloat(2));
            towar.setDostepne(kursor.getFloat(3));
            towar.setRegal(kursor.getInt(4));
            towar.setPolka(kursor.getInt(5));
            towar.setDel(kursor.getInt(6));
            towary.add(towar);
        }

        kursor.close();

        return towary;
    }

    public void wczytajTowar(Context context, Integer id){
//        Towar towar = new Towar();
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] kolumny = {"id","nazwa","cena","dostepne","regal","polka","del"};
        String[] args = {""+id};

        Cursor kursor = db.query("towary",kolumny,"id=?",args,null, null, null);
        if (kursor != null) {
//            setId(kursor.getInt(0));
//            setNazwa(kursor.getString(0));
//            setCena(kursor.getFloat(0));
//            setDostepne(kursor.getFloat(1));
//            setRegal(kursor.getInt(1));
//            setPolka(kursor.getInt(2));

            setId(kursor.getInt(0));
            setNazwa(kursor.getString(1));
            setCena(kursor.getFloat(2));
            setDostepne(kursor.getFloat(3));
            setRegal(kursor.getInt(4));
            setPolka(kursor.getInt(5));
            setDel(kursor.getInt(6));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nazwa);
        dest.writeFloat(cena);
        dest.writeFloat(dostepne);
        dest.writeInt(regal);
        dest.writeInt(polka);
        dest.writeInt(del);
    }
}
