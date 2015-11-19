package com.example.adam.sprzedawca.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adam.sprzedawca.Adapter.KlienciRowAdapter;
import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.R;

import java.util.List;

public class WybierzKlientaActivity extends AppCompatActivity {

    List<Klient> klienci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybierz_klienta);

        ListView listView = (ListView)findViewById(R.id.lV_wybierz_klienta);
        klienci = Klient.dajWszystkie(getApplicationContext());

        if(klienci!=null){
            KlienciRowAdapter adapter = new KlienciRowAdapter(getApplicationContext(),R.layout.row_klienci,klienci);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Klient klient = klienci.get(position);
                Intent intent = new Intent();
                intent.putExtra("klient",klient);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
