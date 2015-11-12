package com.example.adam.sprzedawca.Old;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.adam.sprzedawca.Activity.DodajKlientaActivity;
import com.example.adam.sprzedawca.Activity.DodajTowarActivity;
import com.example.adam.sprzedawca.Activity.DodajZamowienieActivity;
import com.example.adam.sprzedawca.Fragment.KlienciFragment;
import com.example.adam.sprzedawca.R;
import com.example.adam.sprzedawca.Fragment.TowaryFragment;
import com.example.adam.sprzedawca.Fragment.ZamowieniaFragment;

public class SprzedawcaActivity_old extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sprzedawca);

        Button btnZamowienia = (Button) findViewById(R.id.button_lista_zamowien);
        View.OnClickListener lZamowienia = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SprzedawcaActivity_old.this,ZamowieniaFragment.class);
                startActivity(i);
            }
        };
        btnZamowienia.setOnClickListener(lZamowienia);

        Button btnKlienci = (Button) findViewById(R.id.button_lista_klientow);
        View.OnClickListener lKlienci = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SprzedawcaActivity_old.this, KlienciFragment.class);
                startActivity(i);
            }
        };
        btnKlienci.setOnClickListener(lKlienci);

        Button btnTowary = (Button) findViewById(R.id.button_lista_towarow);
        View.OnClickListener lTowary = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SprzedawcaActivity_old.this,TowaryFragment.class);
                startActivity(i);
            }
        };
        btnTowary.setOnClickListener(lTowary);

        Button btnDodajZamowienie = (Button) findViewById(R.id.button_dodaj_zamowienie);
        View.OnClickListener lDodajZamowienie = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SprzedawcaActivity_old.this, DodajZamowienieActivity.class);
                startActivity(i);
            }
        };
        btnDodajZamowienie.setOnClickListener(lDodajZamowienie);

        Button btnDodajKlienta = (Button) findViewById(R.id.button_dodaj_klienta);
        View.OnClickListener lDodajKlienta = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SprzedawcaActivity_old.this,DodajKlientaActivity.class);
                startActivity(i);
            }
        };
        btnDodajKlienta.setOnClickListener(lDodajKlienta);

        Button btnDodajTowar = (Button) findViewById(R.id.button_dodaj_towar);
        View.OnClickListener lDodajTowar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SprzedawcaActivity_old.this,DodajTowarActivity.class);
                startActivity(i);
            }
        };
        btnDodajTowar.setOnClickListener(lDodajTowar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sprzedawca, menu);
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
