package com.example.adam.sprzedawca.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.MenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
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
    boolean itemSelected = false;
//    boolean itemZadzwonSelected = false;
//    boolean itemPokazMapeSelected = false;
    Menu menu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout llLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_klienci, container, false);
        // Of course you will want to faActivity and llLayout in the class and not this method to access them in the rest of
        // the class, just initialize them here

        // Content of previous onCreate() here
        setHasOptionsMenu(true);

        ArrayList<String> sKlienci = new ArrayList<>();

        listView = (ListView) llLayout.findViewById(R.id.listView_Klienci);

        //ponizej: Włączenie obsługi zaznaczania poszczególnych elementów ListView (select) w celu wykonywania akcji
        //na poszczególnych rekordach (edytowanie, dzwonienie do klienta, lokalizacja GPS). Aby to zadziałało należało
        //jeszcze dodać w pliku >>row_klienci.xml<< wpis:
        //>>android:background="@drawable/menu_item_background_selector"<<
        //dla layoutu(zmiana koloru tła zaznaczonego rekordu) i wpis
        //>>android:textColor="@drawable/menu_item_text_selector"<<
        //dla poszczególnych TextView (zmiana koloru tekstu zaznaczonego rekordu).
        //Należało także dodać pliki >>menu_item_background_selector.xml<< i >>menu_item_text_selector.xml<<,
        //zawierające konfigurację kolorów tła i tekstu dla klikniętych i nieklikniętych rekordów, do katalogu >>res/drawable<<
        //(za http://stackoverflow.com/questions/10788688/programmatically-select-item-listview-in-android ze zmianami autorstwa Quetina Klein'a
        // z dnia 26-05-2014 19:37 w komentarzach -  należało zamienić w pliku >>menu_item_background_selector.xml<< znaczniki z
        // >>android:color="#0094CE"<< i >>android:color="#0094CE"<< na >>android:drawable="@color/darkblue"<< i >>android:drawable="@color/orange"<<,
        // ponieważ wyskakiwał błąd >>XML Inflate exception<<.
        // Aby działało >>android:drawable="@color/jakis_kolor"<< trzeba było dodać plik >>colors.xml<< z kolorami do katalogu >>res/values<<.
        // W moim przypadku uzupełniłem ten plik wpisami z http://developer.android.com/samples/BasicMediaRouter/res/values/colors.html)
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

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
                            MenuView.ItemView itemEdytuj = (MenuView.ItemView)faActivity.findViewById(R.id.edytuj_klienta);
                            itemEdytuj.setEnabled(true);
                            MenuView.ItemView itemZadzwon = (MenuView.ItemView)faActivity.findViewById(R.id.zadzwon);
                            itemZadzwon.setEnabled(true);

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
                    itemSelected = true;

                    onPrepareOptionsMenu(menu);
                    // lub:
                    //faActivity.invalidateOptionsMenu();
                }
            });

//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    editedKlient = klienci.get(position);
//                    Intent intent = new Intent(faActivity.getApplicationContext(), EdytujKlientaActivity.class);
//                    intent.putExtra("klient",editedKlient);
//                    startActivityForResult(intent, Klient.REQUEST_CODE_EDYTUJ_KLIENTA);
//                }
//            });
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
        inflater.inflate(R.menu.menu_klienci,menu);
        this.menu = menu;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        super.onPrepareOptionsMenu(menu);
        if(itemSelected){
            menu.getItem(0).setEnabled(true);
            menu.getItem(1).setEnabled(true);
            menu.getItem(2).setEnabled(true);
        }
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }


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

        // Handle your other action bar items...
        final FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        Intent intent;
        int position;

        FragmentManager fragmentManager = getFragmentManager();

        switch (item.getItemId()) {
            case R.id.usun_klientow:
                ///////Ponizej stara metoda używająca odrębnych Fragmentów do wyświetlania popupów -
//                UsunKlientowFragment usunKlientowFragment = new UsunKlientowFragment();
//                usunKlientowFragment.show(fragmentManager,"Usun klientow");

                //////Nowa wersja:
                AlertDialog.Builder builder = new AlertDialog.Builder(faActivity);
                builder.setTitle("Usunięcie wszystkich klientów!");
                builder.setMessage("Czy na pewno chcesz usunąć wszystkich klientów?. Ta operacja jest nieodwracalna.");
                builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Klient.kasujWszystkie(getActivity().getApplicationContext());
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
            case R.id.edytuj_klienta:
                position = listView.getCheckedItemPosition();
                editedKlient = klienci.get(position);
                intent = new Intent(faActivity.getApplicationContext(), EdytujKlientaActivity.class);
                intent.putExtra("klient",editedKlient);
                startActivityForResult(intent, Klient.REQUEST_CODE_EDYTUJ_KLIENTA);
                break;
            case R.id.zadzwon:
                position = listView.getCheckedItemPosition();
                editedKlient = klienci.get(position);
                String phoneNr = editedKlient.getTelefon();
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNr));
                startActivity(intent);
                break;
            case R.id.pokaz_mape:
                position = listView.getCheckedItemPosition();
                editedKlient = klienci.get(position);
//                String uri = "geo:0,0?q=Mikołowska+Katowice";
                String uri = "geo:0,0?q="+editedKlient.getAdres()+"+"+editedKlient.getMiejscowosc();
                intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
