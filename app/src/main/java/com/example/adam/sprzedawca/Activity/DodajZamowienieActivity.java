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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DodajZamowienieActivity extends AppCompatActivity {
    private Klient klient;
    private Towar towar;
    private Zamowienie zamowienie;
    EditText fieldCena;
    EditText fieldSztuk;
    DatePicker fieldData;

    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zamowienie);

        klient=null;
        towar=null;


        fieldSztuk = (EditText)findViewById(R.id.editText_addZamowienie_sztuk);
        fieldCena = (EditText)findViewById(R.id.editText_addZamowienie_cena);
        fieldData = (DatePicker)findViewById(R.id.datePicker);
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

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){ //jesli to edytowanie a nie dodawanie to jest obiekt budle przekazywany przez parcel
//            Log.e("Zamowienie", "Edytowanie!!!!!!!!!!!!!!");
            zamowienie = bundle.getParcelable("zamowienie");
                        Log.e("Zamowienie", "Edytowanie!!!!!!!! Towar.id="+zamowienie.getTowar_id()+". Klient.id="+zamowienie.getKlient_id());
            towar = new Towar();
            towar.wczytajTowar(getApplicationContext(),zamowienie.getTowar_id());
            klient = new Klient();
            klient.wczytajKlienta(getApplicationContext(),zamowienie.getKlient_id());
            fieldCena.setText(zamowienie.getCena().toString());
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date date = dateFormat.parse(zamowienie.getData());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                fieldData.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),null);
            } catch (ParseException e) {
                e.printStackTrace();
            }

//            int dzien = getDay(zamowienie.getData());
//            int miesiac = getMonth(zamowienie.getData());
//            int rok = getYear(zamowienie.getData());

            fieldSztuk.setText(zamowienie.getSztuk().toString());

            TextView tvKlient = (TextView)findViewById(R.id.tV_wybranyKlient);
            tvKlient.setText(zamowienie.getKlient_name());
            TextView tvTowar = (TextView)findViewById(R.id.tV_wybranyTowar);
            tvTowar.setText(zamowienie.getTowar_name());
        }

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

    private int getDay(String date)
    {
        String dzien = date.substring(0,1);
        Log.e("Dzien","Dzien =>"+dzien+"<");
        return Integer.parseInt(dzien);
    }

    private int getMonth(String date)
    {
        String miesiac = date.substring(3,4);
        Log.e("Dzien","Miesiac =>"+miesiac+"<");
        return Integer.parseInt(miesiac);
    }

    private int getYear(String date)
    {
        String rok = date.substring(6,9);
        Log.e("Rok","Rok =>"+rok+"<");
        return Integer.parseInt(rok);
    }
}
