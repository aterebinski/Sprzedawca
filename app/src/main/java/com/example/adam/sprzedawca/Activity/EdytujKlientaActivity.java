package com.example.adam.sprzedawca.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.Model.Towar;
import com.example.adam.sprzedawca.R;

public class EdytujKlientaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_klienta);


        Bundle bundle = getIntent().getExtras();
        final Klient klient = bundle.getParcelable("klient");

        final EditText etNazwa = (EditText)findViewById(R.id.editText_editKlient_nazwa);
        final EditText etUlica = (EditText)findViewById(R.id.editText_editKlient_ulica);
        final EditText etMiejscowosc = (EditText)findViewById(R.id.editText_editKlient_miejscowosc);
        final EditText etKod = (EditText)findViewById(R.id.editText_editKlient_kod);
        final EditText etNip = (EditText)findViewById(R.id.editText_editKlient_nip);
        final EditText etRegon = (EditText)findViewById(R.id.editText_editKlient_regon);
        final EditText etTelefon = (EditText)findViewById(R.id.editText_editKlient_telefon);
        Button btnZatwierdz = (Button)findViewById(R.id.button_editKlient_Zatwierdz);

        etNazwa.setText(klient.getNazwa());
        etUlica.setText(klient.getAdres());
        etMiejscowosc.setText(klient.getMiejscowosc());
        etKod.setText(klient.getKod());
        etNip.setText(klient.getNip());
        etRegon.setText(klient.getRegon());
        etTelefon.setText(klient.getTelefon());

        btnZatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klient.setNazwa(etNazwa.getText().toString());
                klient.setAdres(etUlica.getText().toString());
                klient.setMiejscowosc(etMiejscowosc.getText().toString());
                klient.setKod(etKod.getText().toString());
                klient.setNip(etNip.getText().toString());
                klient.setRegon(etRegon.getText().toString());
                klient.setTelefon(etTelefon.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("klient",klient);
                setResult(Towar.RESULT_CODE_OK,intent);
                finish();
            }
        });

    }

}
