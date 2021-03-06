package com.example.adam.sprzedawca.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.adam.sprzedawca.Activity.DodajZamowienieActivity;
import com.example.adam.sprzedawca.Adapter.KlienciRowAdapter;
import com.example.adam.sprzedawca.Adapter.ZamowieniaRowAdapter;
import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.Model.Zamowienie;
import com.example.adam.sprzedawca.R;

import java.util.ArrayList;
import java.util.List;

    public class ZamowieniaFragment extends Fragment{

        List<Zamowienie> zamowienia;
        ZamowieniaRowAdapter adapter;
        ListView listView;
        Zamowienie editedZamowienie;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout llLayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_zamowienia, container, false);
        // Of course you will want to faActivity and llLayout in the class and not this method to access them in the rest of
        // the class, just initialize them here

        // Content of previous onCreate() here
        setHasOptionsMenu(true);

        ArrayList<String> sZamownienia= new ArrayList<>();
//        ListView listView = (ListView) super.getActivity().findViewById(R.id.listView_Klienci);
        listView = (ListView) llLayout.findViewById(R.id.lV_Zamowienia);

        FloatingActionButton floatingActionButton = (FloatingActionButton)llLayout.findViewById(R.id.floatingActionButton_Zamowienia);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(faActivity.getApplicationContext(), DodajZamowienieActivity.class);
                startActivityForResult(intent, Zamowienie.REQUEST_CODE_DODAJ_ZAMOWIENIE);
            }
        });

        zamowienia = Zamowienie.dajWszystkie(faActivity.getApplicationContext());

        adapter = new ZamowieniaRowAdapter(faActivity.getApplicationContext(),R.layout.row_zamowienia,zamowienia);
        listView.setAdapter(adapter);
        if(zamowienia!=null) {
//            Log.e("ZamowieniaFragment", "Sa zamowienia!!! ==================================");

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(faActivity);
                    builder.setTitle("Usunięcie zamówienia.");
                    builder.setMessage("Czy na pewno chcesz usunąć to zamówienie? Operacja jest nieodwracalna.");
                    builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Zamowienie zamowienie = zamowienia.get(position);
                            zamowienie.kasujZamowienie(faActivity.getApplicationContext());
                            adapter.remove(adapter.getItem(position));
                            adapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //nic nie rób
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    return true;
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    editedZamowienie = zamowienia.get(position);
                    Intent intent = new Intent(faActivity.getApplicationContext(), DodajZamowienieActivity.class);
                    intent.putExtra("zamowienie", editedZamowienie);
                    startActivityForResult(intent, Zamowienie.REQUEST_CODE_EDYTUJ_ZAMOWIENIE);
                }
            });

        }
//        else{
//            Log.e("ZamowieniaFragment","Brak zamowien!!! ==================================");
//        }

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
                case Zamowienie.REQUEST_CODE_DODAJ_ZAMOWIENIE:
                    if((resultCode==Zamowienie.RESULT_CODE_OK)&&(data.getExtras().getParcelable("zamowienie")!=null)){
                        Zamowienie zamowienie = (Zamowienie)data.getExtras().get("zamowienie");
                        zamowienie.dodajZamowienie(faActivity);
//                        Log.e("ZamowieniaFragment", "ole1");
                        //dodanie klienta do adaptera i odswiezenie listView
                        adapter.add(zamowienie);
//                        Log.e("ZamowieniaFragment", "ole2!!! ==================================");
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case Zamowienie.REQUEST_CODE_EDYTUJ_ZAMOWIENIE:
                    if((resultCode==Zamowienie.RESULT_CODE_OK)&&(data.getExtras().getParcelable("zamowienie")!=null)){
                        Zamowienie zamowienie = (Zamowienie)data.getExtras().get("zamowienie");
                        zamowienie.aktualizujZamowienie(faActivity);
//                        Log.e("ZamowieniaFragment", "ole1");

                        //poprawienie zamowienia w adapterze i odswiezenie listView
                        int pos = adapter.getPosition(editedZamowienie);
                        adapter.remove(editedZamowienie);
                        adapter.insert(zamowienie, pos);
//                        Log.e("ZamowieniaFragment", "ole2!!! ==================================");
                        adapter.notifyDataSetChanged();
                    }
                    break;

            }
            super.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_zamowienia, menu);
//            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            FragmentManager fragmentManager = getFragmentManager();

            switch (item.getItemId()){
                case R.id.usun_zamowienia:
                    ///////Ponizej stara metoda używająca odrębnych Fragmentów do wyświetlania popupów -
//                    UsunZamowieniaFragment usunZamowieniaFragment = new UsunZamowieniaFragment();
//                    usunZamowieniaFragment.show(fragmentManager,"Usun zamowienia");

                    //Nowa wersja:
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Usunięcie wszystkich zamówień!");
                    builder.setMessage("Czy na pewno chcesz usunąć wszystkie zamówienia?. Ta operacja jest nieodwracalna.");
                    builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Zamowienie.kasujWszystkie(getActivity().getApplicationContext());
                            adapter.clear();
                            adapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                    break;
                default:
                    return super.onOptionsItemSelected(item);
            }
            return true;
        }
    }
