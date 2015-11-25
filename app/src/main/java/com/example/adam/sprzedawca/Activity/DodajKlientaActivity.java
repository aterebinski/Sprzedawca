package com.example.adam.sprzedawca.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.R;

public class DodajKlientaActivity extends AppCompatActivity {

    Klient klient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_klienta);

        final EditText fieldNazwa = (EditText)findViewById(R.id.editText_addKlient_nazwa);
        final EditText fieldAdres = (EditText)findViewById(R.id.editText_addKlient_ulica);
        final EditText fieldMiejscowosc = (EditText)findViewById(R.id.editText_addKlient_miejscowosc);
        final EditText fieldKod = (EditText)findViewById(R.id.editText_addKlient_kod);
        final EditText fieldNIP = (EditText)findViewById(R.id.editText_addKlient_nip);
        final EditText fieldREGON = (EditText)findViewById(R.id.editText_addKlient_regon);
        final EditText fieldTelefon = (EditText)findViewById(R.id.editText_addKlient_telefon);


        final Button btnDodaj = (Button)findViewById(R.id.button_addKlient_Zatwierdz);
        final View.OnClickListener lZatwierdz = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klient = new Klient(fieldNazwa.getText().toString(), fieldAdres.getText().toString(), fieldMiejscowosc.getText().toString(), fieldKod.getText().toString(), fieldNIP.getText().toString(),
                        fieldREGON.getText().toString(), fieldTelefon.getText().toString());
                klient.dodajKlienta(getApplicationContext());
                finish();
            }
        };
        btnDodaj.setOnClickListener(lZatwierdz);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dodaj_klienta, menu);
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
