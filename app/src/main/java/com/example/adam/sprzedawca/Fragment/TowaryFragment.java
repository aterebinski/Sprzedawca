package com.example.adam.sprzedawca.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.adam.sprzedawca.Activity.DodajTowarActivity;
import com.example.adam.sprzedawca.Activity.EdytujTowarActivity;
import com.example.adam.sprzedawca.Adapter.TowaryRowAdapter;
import com.example.adam.sprzedawca.Db.SortTowarByName;
import com.example.adam.sprzedawca.Model.Towar;
import com.example.adam.sprzedawca.R;

import java.util.ArrayList;
import java.util.List;

public class TowaryFragment extends Fragment {
    ListView lista;
    List<Towar> towary;
    TowaryRowAdapter adapter;
    Towar editedTowar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout llLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_towary, container, false);
        // Of course you will want to faActivity and llLayout in the class and not this method to access them in the rest of
        // the class, just initialize them here

        // Content of previous onCreate() here
        lista = (ListView) llLayout.findViewById(R.id.listView_Towary);
        towary = Towar.dajWszystkie(faActivity.getApplicationContext());

        adapter = new TowaryRowAdapter(faActivity.getApplicationContext(), R.layout.row_towary, towary);
        adapter.sort(new SortTowarByName());
        lista.setAdapter(adapter);

        if (towary != null) {
            lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    final Towar towar = towary.get(position);
                    int towarId = towar.getId();

                    AlertDialog.Builder builder = new AlertDialog.Builder(faActivity);
                    builder.setTitle("Usunięcie towaru");
                    builder.setMessage("Czy chcesz usunąć wybrany towar? Operacja jest nieodwracalna.");
                    builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            towar.kasujTowar(faActivity.getApplicationContext());
                            adapter.remove(adapter.getItem(position));
                            adapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    return true;
                }
            });

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    editedTowar = towary.get(position);
                    Intent intent = new Intent(faActivity.getApplicationContext(), EdytujTowarActivity.class);
                    intent.putExtra("towar", editedTowar);
                    startActivityForResult(intent, Towar.REQUEST_CODE_EDYTUJ_TOWAR);
                }
            });
        }

        FloatingActionButton floatingActionButton = (FloatingActionButton) llLayout.findViewById(R.id.floatingActionButton_Towary);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(faActivity, DodajTowarActivity.class);
                startActivityForResult(intent, Towar.REQUEST_CODE_DODAJ_TOWAR);
//                faActivity.
            }
        });

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
        switch (requestCode) {
            case Towar.REQUEST_CODE_DODAJ_TOWAR:
                if ((resultCode == Towar.RESULT_CODE_OK) && (data.getExtras().getParcelable("towar") != null)) {
                    Towar towar = (Towar) data.getExtras().get("towar");
                    towar.dodajTowar(faActivity);

                    //dodanie towaru do adaptera i odswiezenie listView
                    adapter.add(towar);
                    adapter.sort(new SortTowarByName());
                    adapter.notifyDataSetChanged();
                }
                break;
            case Towar.REQUEST_CODE_EDYTUJ_TOWAR:
                if ((resultCode == Towar.RESULT_CODE_OK) && (data.getExtras().getParcelable("towar") != null)) {
                    Towar towar = (Towar) data.getExtras().get("towar");
                    towar.aktualizujTowar(faActivity);

                    //poprawienie towaru w adapterze i odswiezenie listView
                    int pos = adapter.getPosition(editedTowar);
                    adapter.remove(editedTowar);
                    adapter.insert(towar, pos);

                    adapter.sort(new SortTowarByName());
                    adapter.notifyDataSetChanged();
                }
                break;
            default:


        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_towary, menu);
//        return true;
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

        return super.onOptionsItemSelected(item);
    }
}
