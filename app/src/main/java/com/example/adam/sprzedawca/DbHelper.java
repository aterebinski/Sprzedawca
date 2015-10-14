package com.example.adam.sprzedawca;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Adam on 2015-10-03.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper instance = null;
    private static String dbName = "sprzedawca.db";

    private DbHelper(Context context) {
        super(context, dbName, null, 1);
    }

    public static DbHelper getDbHelper(Context context){
        if(instance==null){
            instance = new DbHelper(context.getApplicationContext());
        }
        return instance;
    }

//    public DbHelper(Context context) {
//        super(context, "sprzedawca.db", null, 1);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table klienci(" +
                "id integer primary key autoincrement," +
                "nazwa text," +
                "adres text," +
                "miejscowosc text," +
                "kod text," +
                "nip text," +
                "regon text," +
                "telefon text);");

        db.execSQL("create table towary(" +
                "id integer primary key autoincrement," +
                "nazwa text," +
                "cena real," +
                "dostepne real," +
                "regal integer," +
                "polka integer);");
        db.execSQL("create table zamowienie(" +
                "id integer primary key autoincrement," +
                "klient_id integer," +
                "towar_id integer," +
                "data text," +
                "sztuk real," +
                "cena real);");

        Log.d("Dodano bazę","");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*public void dodajKlienta(String nazwa, String adres, String miejscowosc, String kod, String nip, String regon, String telefon){

        ContentValues wartosci = new ContentValues();
        wartosci.put("nazwa", nazwa );
        wartosci.put("adres", adres);
        wartosci.put("miejscowosc", miejscowosc);
        wartosci.put("kod",kod);
        wartosci.put("nip",nip);
        wartosci.put("regon",regon);
        wartosci.put("telefon", telefon);
        SQLiteDatabase db = getWritableDatabase();
        db.insertOrThrow("klienci", null, wartosci);
    }

    public void dodajTowar(String nazwa, Float cena, String dostepne, String regal, String polka){
        ContentValues wartosci = new ContentValues();
        wartosci.put("nazwa", nazwa);
        wartosci.put("cena",cena);
        wartosci.put("dostepne",dostepne);
        wartosci.put("regal",regal);
        wartosci.put("polka",polka);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow("towary", null, wartosci);
    }

    public void dodajZamowienie(Integer klient_id, Integer towar_id, String data, Float sztuk, Float cena){
        ContentValues wartosci = new ContentValues();
        wartosci.put("klient_id", klient_id);
        wartosci.put("towar_id", towar_id);
        wartosci.put("data", data);
        wartosci.put("sztuk", sztuk);
        wartosci.put("cena", cena);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow("zamowienia",null, wartosci);
    }*/
}
