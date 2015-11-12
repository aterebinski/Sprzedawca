package com.example.adam.sprzedawca.Old;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.adam.sprzedawca.Adapter.KlienciRowAdapter;
import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.R;

import java.util.ArrayList;
import java.util.List;

public class KlienciActivity_old extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_klienci);
//        String tekst = "";

        ArrayList<String> sKlienci= new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.listView_Klienci);

//        TextView tv = (TextView) findViewById(R.id.textView1);


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
            String sKlient;
//            tekst=tekst+"\n"+klient.getNazwa()+" | "+klient.getAdres()+" | "+klient.getMiejscowosc()+" | "+klient.getKod()+" | "+
//                    klient.getNip()+" | "+klient.getRegon()+" | "+klient.getTelefon();
            sKlient = klient.getNazwa()+" - ulica: "+klient.getAdres()+", "+klient.getKod()+" "+klient.getMiejscowosc()+", NIP: "+
                    klient.getNip()+", REGON: "+klient.getRegon()+", telefon: "+klient.getTelefon();
            sKlienci.add(sKlient);
        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row_klienci,R.id.textView_row_klienci,sKlienci);
        KlienciRowAdapter adapter = new KlienciRowAdapter(this,R.layout.row_klienci,klienci);
        listView.setAdapter(adapter);
//        tv.setText(tekst);
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
