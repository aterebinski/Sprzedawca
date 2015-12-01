package com.example.adam.sprzedawca.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.Model.Towar;
import com.example.adam.sprzedawca.Model.Zamowienie;
import com.example.adam.sprzedawca.R;

import java.util.List;

public class DodajZamowienieActivity extends AppCompatActivity {
    private Klient klient;
    private Towar towar;
    private Zamowienie zamowienie;

    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zamowienie);

        klient=null;
        towar=null;

        final EditText fieldSztuk = (EditText)findViewById(R.id.editText_addZamowienie_sztuk);
        final EditText fieldCena = (EditText)findViewById(R.id.editText_addZamowienie_cena);
        final DatePicker fieldData = (DatePicker)findViewById(R.id.datePicker);
        Button btnWybierzKlienta = (Button)findViewById(R.id.bt_wybierzKlienta);
        Button btnWybierzTowar = (Button)findViewById(R.id.bt_wybierzTowar);
        Button btnZatwierdz = (Button)findViewById(R.id.button_addZamowienie_zatwierdz);

        btnWybierzKlienta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WybierzKlientaActivity.class);
                startActivityForResult(intent,requestCode);
            }
        });

        btnWybierzTowar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WybierzTowarActivity.class);
                startActivityForResult(intent,requestCode);
            }
        });

        List<Towar> towary = Towar.dajWszystkie(getApplicationContext());
        List<Klient> klienci = Klient.dajWszystkie(getApplicationContext());

        btnZatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(towar==null){
                    Toast.makeText(DodajZamowienieActivity.this, "Nie wybrałeś towaru", Toast.LENGTH_SHORT).show();
                }else if (klient==null){
                    Toast.makeText(DodajZamowienieActivity.this, "Nie wybrałeś klienta", Toast.LENGTH_SHORT).show();
                }else{
                    zamowienie = new Zamowienie();
                    zamowienie.setTowar_id(towar.getId());
                    zamowienie.setTowar_name(towar.getNazwa());
                    zamowienie.setKlient_id(klient.getId());
                    zamowienie.setKlient_name(klient.getNazwa());
                    zamowienie.setCena(Float.parseFloat(fieldCena.getText().toString()));
                    zamowienie.setSztuk(Float.parseFloat(fieldSztuk.getText().toString()));
                    zamowienie.setData(showDate(fieldData.getDayOfMonth(), fieldData.getMonth(), fieldData.getYear()));
//                    zamowienie.dodajZamowienie(getApplicationContext());
                    Intent intent = new Intent();
                    intent.putExtra("zamowienie",zamowienie);
                    setResult(Zamowienie.RESULT_CODE_OK,intent);
//                    Log.e("Towar","Obiekt Towar id:"+towar.getId()+", nazwa:"+towar.getNazwa());
                    finish();
                }
            }
        });


    }

    //ponizej: obsluga przyjscia wynikow wyboru towaru lub klienta
    //(wybor za pomoca przycikow "wybor")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //pobranie klienta ktory jest obiektem Parcelable
        if(data.getExtras().getParcelable("klient")!=null){
            klient = data.getExtras().getParcelable("klient");
            TextView tvKlient = (TextView)findViewById(R.id.tV_wybranyKlient);
            tvKlient.setText(klient.getNazwa());
        }

        //pobranie towaru ktory jest obiektem Parcelable
        if(data.getExtras().getParcelable("towar")!=null){
            towar = data.getExtras().getParcelable("towar");
            TextView tvTowar = (TextView)findViewById(R.id.tV_wybranyTowar);
            tvTowar.setText(towar.getNazwa());
        }
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

    private String showDate(int day, int month, int year) {
        return ""+day+"-"+(month+1)+"-"+year;

    }
}
