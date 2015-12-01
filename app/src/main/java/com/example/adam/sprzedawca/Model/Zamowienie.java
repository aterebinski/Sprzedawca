package com.example.adam.sprzedawca.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.adam.sprzedawca.Db.DbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2015-10-03.
 */
public class Zamowienie implements Parcelable {
    private Integer id;
    private Integer klient_id;
    private String klient_name;
    private Integer towar_id;
    private String towar_name;
    private String data;
    private Float sztuk;
    private Float cena;
    private Integer del;

    public final static int REQUEST_CODE_DODAJ_ZAMOWIENIE = 5;
    public final static int REQUEST_CODE_USUN_ZAMOWIENIA = 6;
    public final static int RESULT_CODE_OK = 1;

    public Zamowienie(Integer id, Integer klient_id, String klient_name, Integer towar_id, String towar_name, String data, Float sztuk, Float cena, Integer del) {
        this.id = id;
        this.klient_id = klient_id;
        this.klient_name = klient_name;
        this.towar_id = towar_id;
        this.towar_name = towar_name;
        this.data = data;
        this.sztuk = sztuk;
        this.cena = cena;
        this.del = del;
    }

    public Zamowienie(String klient_name, Integer towar_id, String towar_name, String data, Float sztuk, Float cena) {
        this.id = 0;
        this.klient_id = klient_id;
        this.klient_name = klient_name;
        this.towar_id = towar_id;
        this.towar_name = towar_name;
        this.data = data;
        this.sztuk = sztuk;
        this.cena = cena;
        this.del = 0;
    }

    public Zamowienie(Integer klient_id, Integer towar_id, String data, Float sztuk, Float cena) {
        this.id = 0;
        this.klient_id = klient_id;
        this.klient_name = "";
        this.towar_id = towar_id;
        this.towar_name = "";
        this.data = data;
        this.sztuk = sztuk;
        this.cena = cena;
        this.del = 0;
    }

    public Zamowienie() {
        this.id=0;
        this.del = 0;
    }

    protected Zamowienie(Parcel in) {
        id = in.readInt();
        klient_id = in.readInt();
        klient_name = in.readString();
        towar_id = in.readInt();
        towar_name = in.readString();
        data = in.readString();
        sztuk = in.readFloat();
        cena = in.readFloat();
        del = in.readInt();
    }

    public static final Creator<Zamowienie> CREATOR = new Creator<Zamowienie>() {
        @Override
        public Zamowienie createFromParcel(Parcel in) {
            return new Zamowienie(in);
        }

        @Override
        public Zamowienie[] newArray(int size) {
            return new Zamowienie[size];
        }
    };

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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
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
        wartosci.put("del",getDel());
        db.insertOrThrow("zamowienie", null, wartosci);
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
        wartosci.put("del",getDel());
        db.update("zamowienie", wartosci, "id=?", args);
    }

//    public static void kasujZamowienie(Context context, Integer id){
//        DbHelper dbHelper = DbHelper.getDbHelper(context);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String[] args = {""+id};
////        db.delete("zamowienie", "id=?", args);
//        ContentValues values = new ContentValues();
//        values.put("del",1);
//        db.update("zamowienie", values, "id=?", args);
//    }

    public void kasujZamowienie(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args = {""+this.getId()};
//        db.delete("zamowienie", "id=?", args);
        ContentValues values = new ContentValues();
        values.put("del",1);
        db.update("zamowienie", values, "id=?", args);
    }

    public static void kasujWszystkie(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete("zamowienie", "", null);
        ContentValues values = new ContentValues();
        values.put("del",1);
        db.update("zamowienie",values,"",null);
    }

    public static List<Zamowienie> dajWszystkie(Context context){
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
        String[] kolumny = {"id","klient_id", "towar_id", "data","sztuk","cena","del"};
        String[] klient_kolumny = {"id","nazwa"};
        String[] klient_args = new String[1];
        String[] towar_kolumny = {"id","nazwa"};
        String[] towar_args = new String[1];

        Cursor cursor = db.query("zamowienie",kolumny,"del=0", null, null, null, null );
//        Cursor cursor = db.query("zamowienie",kolumny,null, null, null, null, null );
        while(cursor.moveToNext()){
            Log.e("co tam","co tam");
            klient_args[0] = Integer.toString(cursor.getInt(1));
            Cursor cursor_klient = db.query("klienci",klient_kolumny,"id = ?", klient_args, null, null, null );
            cursor_klient.moveToFirst();
            towar_args[0] = Integer.toString(cursor.getInt(2));
//            Log.e("Towar", "Obiekt Towar id:" + towar.getId() + ", nazwa:" + towar.getNazwa());
            Cursor cursor_towar = db.query("towary",towar_kolumny,"id = ?", towar_args, null, null, null );
            cursor_towar.moveToFirst();
            Zamowienie zamowienie = new Zamowienie();
            zamowienie.setId(cursor.getInt(0));
            zamowienie.setKlient_id(cursor.getInt(1));
            zamowienie.setTowar_id(cursor.getInt(2));
            zamowienie.setData(cursor.getString(3));
            zamowienie.setSztuk(cursor.getFloat(4));
            zamowienie.setCena(cursor.getFloat(5));
            zamowienie.setKlient_name(cursor_klient.getString(1));
            zamowienie.setTowar_name(cursor_towar.getString(1));
            zamowienie.setDel(cursor.getInt(6));
            zamowienia.add(zamowienie);
        }
        return zamowienia;
    }

    public void wczytajZamowienie(Context context, Integer id){
//        Zamowienie zamowienie = new Zamowienie();
        DbHelper dbHelper = DbHelper.getDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] kolumny = {"id","klient_id", "towar_id", "data","sztuk","cena","del"};
        String[] args = {""+id};

        Cursor cursor = db.query("zamowienie",kolumny,"id=?",args,null,null,null);
        if (cursor != null) {
            setId(cursor.getInt(0));
            setKlient_id(cursor.getInt(1));
            setTowar_id(cursor.getInt(2));
            setData(cursor.getString(3));
            setSztuk(cursor.getFloat(4));
            setCena(cursor.getFloat(5));
            setDel(cursor.getInt(6));

//            zamowienie.setId(cursor.getInt(0));
//            zamowienie.setKlient_id(cursor.getInt(1));
//            zamowienie.setTowar_id(cursor.getInt(2));
//            zamowienie.setData(cursor.getString(0));
//            zamowienie.setSztuk(cursor.getFloat(0));
//            zamowienie.setCena(cursor.getFloat(1));
        }

//        return zamowienie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(klient_id);
        dest.writeString(klient_name);
        dest.writeInt(towar_id);
        dest.writeString(towar_name);
        dest.writeString(data);
        dest.writeFloat(sztuk);
        dest.writeFloat(cena);
        dest.writeInt(del);

    }

}
