package com.example.adam.sprzedawca.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        Spinner fieldKlient = (Spinner)findViewById(R.id.spinner_addZamowienie_klient);
        Spinner fieldTowar = (Spinner)findViewById(R.id.spinner_addZamowienie_towar);
        Button buttonZatwierdz = (Button)findViewById(R.id.button_addZamowienie_zatwierdz);

        List<Towar> towary = Towar.dajWszystkie(getApplicationContext());
        List<Klient> klienci = Klient.dajWszystkie(getApplicationContext());

//        String array_spinner[];
//        array_spinner=new String[5];
//        array_spinner[0]="option 1";
//        array_spinner[1]="option 2";
//        array_spinner[2]="option 3";
//        array_spinner[3]="option 4";
//        array_spinner[4]="option 5";

//        ArrayAdapter<Towar> adapterTowar = new ArrayAdapter<Towar>(getApplicationContext(),R.layout.spinner_row,R.id.textView_spinner_row,towary);
        ArrayAdapter adapterTowar = new ArrayAdapter(getApplicationContext(),R.layout.spinner_row,R.id.textView_spinner_row,towary);
//        ArrayAdapter adapterTowar = new ArrayAdapter(getApplicationContext(),R.layout.spinner_row,R.id.textView_spinner_row,array_spinner);
//        ArrayAdapter<Towar> adapterTowar = new ArrayAdapter<Towar>(getApplicationContext(),R.layout.spinner_row,towary);
        fieldTowar.setAdapter(adapterTowar);

//        ArrayAdapter<Klient> adapterKlient = new ArrayAdapter<Klient>(getApplicationContext(),R.layout.row_towary,klienci);
//        ArrayAdapter<Klient> adapterKlient = new ArrayAdapter<Klient>(getApplicationContext(),R.layout.spinner_row,R.id.textView_spinner_row,klienci);
        ArrayAdapter adapterKlient = new ArrayAdapter(getApplicationContext(),R.layout.spinner_row,R.id.textView_spinner_row,klienci);
        fieldKlient.setAdapter(adapterKlient);

//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Float sztuk = Float.parseFloat(fieldSztuk.getText().toString());
//                Float cena = Float.parseFloat(fieldCena.getText().toString());
//                String data = fieldData.getText().toString();
//
////                Zamowienie zamowienie = new
//            }
//        };
//
//        buttonZatwierdz.setOnClickListener(listener);


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
