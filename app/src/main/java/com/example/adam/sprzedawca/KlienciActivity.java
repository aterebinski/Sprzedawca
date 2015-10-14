package com.example.adam.sprzedawca;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class KlienciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klienci);
        String tekst = "";

        TextView tv = (TextView) findViewById(R.id.textView1);

        //przez kursory
//        String[] kolumny = {"nazwa","adres","miejscowosc","kod","nip","regon","telefon"};
//
//        DbHelper dbHelper = DbHelper.getDbHelper(this.getApplicationContext());
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor kursor = db.query("klienci",kolumny,null,null,null,null,null);
//        while (kursor.moveToNext()){
//            int id = kursor.getInt(0);
//            String nazwa = kursor.getString(0);
//            String adres = kursor.getString(1);
//            String miejscowosc = kursor.getString(2);
//            String kod = kursor.getString(3);
//            String nip = kursor.getString(4);
//            String regon = kursor.getString(5);
//            String telefon = kursor.getString(6);
//            tekst=tekst+"\n"+id+"|"+nazwa+"|"+adres+"|"+miejscowosc+"|"+kod+"|"+nip+"|"+regon+"|";
//        }
//        tv.setText(tv.getText() + tekst);

        //obiektowo
        List<Klient> klienci = Klient.dajWszystkie(getApplicationContext());
        for (Klient klient:klienci){
            tekst=tekst+"\n"+klient.getNazwa()+" | "+klient.getAdres()+" | "+klient.getMiejscowosc()+" | "+klient.getKod()+" | "+
                    klient.getNip()+" | "+klient.getRegon()+" | "+klient.getTelefon();
        }
        tv.setText(tekst);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_klienci, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
