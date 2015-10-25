package com.example.adam.sprzedawca;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TowaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_towary);

        ListView lista = (ListView) findViewById(R.id.listView_Towary);
//        TextView tv = (TextView) findViewById(R.id.textView_test);

        List<Towar> towary = Towar.dajWszystkie(getApplicationContext());
        ArrayList<String> stringTowary = new ArrayList<>();
        if(towary.size()>0) {
            for (Towar towar : towary) {
                stringTowary.add(towar.getNazwa()+", cena: "+towar.getCena()+" zł, dostępne: "+towar.getDostepne()+"; położenie(regał/półka):"+towar.getRegal()+"/"+towar.getPolka());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_towary, R.id.textView_row_towary, stringTowary);

        lista.setAdapter(adapter);

        //przez kursory
//        String[] kolumny = {"id","nazwa","cena","dostepne","regal","polka"};
//        String tekst = "";
//
//        DbHelper dbHelper = DbHelper.getDbHelper(this.getApplicationContext());
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor kursor = db.query("towary",kolumny,null,null,null,null,null);
//        while (kursor.moveToNext()){
//            int id = kursor.getInt(0);
//            String nazwa = kursor.getString(1);
//            Float cena = kursor.getFloat(2);
//            Float dostepne = kursor.getFloat(3);
//            Integer regal = kursor.getInt(4);
//            Integer polka = kursor.getInt(5);
//            tekst=tekst+"\n"+id+"|"+nazwa+"|"+cena+"|"+dostepne+"|"+regal+"|"+polka+"|";
//        }
//        tv.setText(tv.getText() + tekst);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_towary, menu);
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
