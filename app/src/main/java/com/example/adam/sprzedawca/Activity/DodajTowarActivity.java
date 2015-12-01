package com.example.adam.sprzedawca.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.adam.sprzedawca.Model.Towar;
import com.example.adam.sprzedawca.R;

public class DodajTowarActivity extends AppCompatActivity {

    Towar towar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_towar);

        final EditText fieldNazwa = (EditText) findViewById(R.id.editText_addTowar_nazwa);
        final EditText fieldCena = (EditText) findViewById(R.id.editText_addTowar_cena);
        final EditText fieldDostepne = (EditText) findViewById(R.id.editText_addTowar_dostepne);
        final EditText fieldRegal = (EditText) findViewById(R.id.editText_addTowar_regal);
        final EditText fieldPolka = (EditText) findViewById(R.id.editText_addTowar_polka);
        Button buttonZatwierdz = (Button) findViewById(R.id.button_addTowar_zatwierdz);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nazwa = fieldNazwa.getText().toString();
                Float cena = Float.parseFloat(fieldCena.getText().toString());
                Float dostepne = Float.parseFloat(fieldDostepne.getText().toString());
                Integer regal = Integer.parseInt(fieldRegal.getText().toString());
                Integer polka = Integer.parseInt(fieldPolka.getText().toString());

                towar = new Towar(nazwa,cena,dostepne,regal,polka);
//                towar.dodajTowar(getApplicationContext());
                Intent intent = new Intent();
                intent.putExtra("towar",towar);
                setResult(Towar.RESULT_CODE_OK,intent);
//                Log.e("Polki i regaly","Polka="+polka+", regal="+regal+" | Obiekt Towar: Polka:"+towar.getPolka()+", regal: "+towar.getRegal());
                finish();
            }
        };
        buttonZatwierdz.setOnClickListener(onClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dodaj_towar, menu);
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
