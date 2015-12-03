package com.example.adam.sprzedawca.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.adam.sprzedawca.Activity.DodajKlientaActivity;
import com.example.adam.sprzedawca.Activity.EdytujKlientaActivity;
import com.example.adam.sprzedawca.Adapter.KlienciRowAdapter;
import com.example.adam.sprzedawca.Db.SortKlientByName;
import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.R;

import java.util.ArrayList;
import java.util.List;

public class KlienciFragment extends Fragment {

    KlienciRowAdapter adapter;
    ListView listView;
    List<Klient> klienci;
    Klient editedKlient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout llLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_klienci, container, false);
        // Of course you will want to faActivity and llLayout in the class and not this method to access them in the rest of
        // the class, just initialize them here

        // Content of previous onCreate() here
        ArrayList<String> sKlienci = new ArrayList<>();

        listView = (ListView) llLayout.findViewById(R.id.listView_Klienci);

        klienci = Klient.dajWszystkie(super.getActivity().getApplicationContext());

        FloatingActionButton floatingActionButton = (FloatingActionButton) llLayout.findViewById(R.id.floatingActionButton_Klienci);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(faActivity.getApplicationContext(), DodajKlientaActivity.class);
                startActivityForResult(i, Klient.REQUEST_CODE_DODAJ_KLIENTA);
//                listView.deferNotifyDataSetChanged();
            }
        });

        adapter = new KlienciRowAdapter(faActivity.getApplicationContext(), R.layout.row_klienci, klienci);
        adapter.sort(new SortKlientByName());
        listView.setAdapter(adapter);

        if (klienci != null) {

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(faActivity);
                    builder.setTitle("Usunięcie klienta");
                    builder.setMessage("Czy chcesz aby wybrany klient został usunięty? Operacja jest nieodwracalna.");
                    builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Klient klient = klienci.get(position);
                            klient.kasujKlienta(faActivity.getApplicationContext());
                            adapter.remove(adapter.getItem(position));
                            adapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return false;
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    editedKlient = klienci.get(position);
                    Intent intent = new Intent(faActivity.getApplicationContext(), EdytujKlientaActivity.class);
                    intent.putExtra("klient",editedKlient);
                    startActivityForResult(intent, Klient.REQUEST_CODE_EDYTUJ_KLIENTA);
                }
            });
        }

        // Don't use this method, it's handled by inflater.inflate() above :
        // setContentView(R.layout.activity_layout);

        // The FragmentActivity doesn't contain the layout directly so we must use our instance of     LinearLayout :
//        llLayout.findViewById(R.id.someGuiElement);
        // Instead of :
        // findViewById(R.id.someGuiElement);
        return llLayout; // We must return the loaded Layout
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        switch (requestCode){
            case Klient.REQUEST_CODE_DODAJ_KLIENTA:
                if((resultCode==Klient.RESULT_CODE_OK)&&(data.getExtras().getParcelable("klient")!=null)){
                    Klient klient = (Klient)data.getExtras().get("klient");
                    klient.dodajKlienta(faActivity);
                    //dodanie klienta do adaptera i odswiezenie listView
                    adapter.add(klient);
                    adapter.sort(new SortKlientByName());
                    adapter.notifyDataSetChanged();
                }
                break;
            case Klient.REQUEST_CODE_EDYTUJ_KLIENTA:
                if((resultCode==Klient.RESULT_CODE_OK)&&(data.getExtras().getParcelable("klient")!=null)){
                    Klient klient = (Klient)data.getExtras().get("klient");
                    klient.aktualizujKlienta(faActivity);
                    //poprawienie klienta w adapterze i odswiezenie listView
                    int pos = adapter.getPosition(editedKlient);
                    adapter.remove(editedKlient);
                    adapter.insert(klient, pos);

                    adapter.sort(new SortKlientByName());

                    adapter.notifyDataSetChanged();
                }
                break;

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
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
