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

import com.example.adam.sprzedawca.Model.Towar;
import com.example.adam.sprzedawca.R;

public class EdytujTowarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_towar);

        Bundle bundle = getIntent().getExtras();
        final Towar towar = bundle.getParcelable("towar");

        final EditText etNazwa = (EditText)findViewById(R.id.editText_editTowar_nazwa);
        final EditText etCena = (EditText)findViewById(R.id.editText_editTowar_cena);
        final EditText etDostepne = (EditText)findViewById(R.id.editText_editTowar_dostepne);
        final EditText etRegal = (EditText)findViewById(R.id.editText_editTowar_regal);
        final EditText etPolka = (EditText)findViewById(R.id.editText_editTowar_polka);
        Button btnZatwierdz = (Button)findViewById(R.id.button_editTowar_zatwierdz);

        etNazwa.setText(towar.getNazwa());
        etCena.setText(towar.getCena().toString());
        etDostepne.setText(towar.getDostepne().toString());
        etRegal.setText(towar.getRegal().toString());
        etPolka.setText(towar.getPolka().toString());

        btnZatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                towar.setNazwa(etNazwa.getText().toString());
                towar.setCena(Float.parseFloat(etCena.getText().toString()));
                towar.setDostepne(Float.parseFloat(etDostepne.getText().toString()));
                towar.setRegal(Integer.parseInt(etRegal.getText().toString()));
                towar.setPolka(Integer.parseInt(etPolka.getText().toString()));

                Intent intent = new Intent();
                intent.putExtra("towar",towar);
                setResult(Towar.RESULT_CODE_OK,intent);
                finish();
            }
        });



    }

}
