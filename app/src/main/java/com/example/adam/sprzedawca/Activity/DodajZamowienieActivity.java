package com.example.adam.sprzedawca.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.Model.Towar;
import com.example.adam.sprzedawca.R;

import java.util.List;

public class DodajZamowienieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zamowienie);

        final EditText fieldSztuk = (EditText)findViewById(R.id.editText_addZamowienie_sztuk);
        final EditText fieldCena = (EditText)findViewById(R.id.editText_addZamowienie_cena);
        final EditText fieldData = (EditText)findViewById(R.id.editText_addZamowienie_data);
        Button btnWybierzKlienta = (Button)findViewById(R.id.bt_wybierzKlienta);
        Button btnWybierzTowar = (Button)findViewById(R.id.bt_wybierzTowar);
        Button buttonZatwierdz = (Button)findViewById(R.id.button_addZamowienie_zatwierdz);

        btnWybierzKlienta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WybierzKlientaActivity.class);
                startActivity(intent);
            }
        });

        btnWybierzTowar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WybierzTowarActivity.class);
                startActivity(intent);
            }
        });

        List<Towar> towary = Towar.dajWszystkie(getApplicationContext());
        List<Klient> klienci = Klient.dajWszystkie(getApplicationContext());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dodaj_zamowienie, menu);
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
