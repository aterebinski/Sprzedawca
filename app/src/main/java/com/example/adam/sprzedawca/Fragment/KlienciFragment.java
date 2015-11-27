package com.example.adam.sprzedawca.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
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
import android.widget.Toast;

import com.example.adam.sprzedawca.Activity.DodajKlientaActivity;
import com.example.adam.sprzedawca.Adapter.KlienciRowAdapter;
import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.R;

import java.util.ArrayList;
import java.util.List;

public class KlienciFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout llLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_klienci, container, false);
        // Of course you will want to faActivity and llLayout in the class and not this method to access them in the rest of
        // the class, just initialize them here

        // Content of previous onCreate() here
        ArrayList<String> sKlienci = new ArrayList<>();

        final ListView listView = (ListView) llLayout.findViewById(R.id.listView_Klienci);

        final List<Klient> klienci = Klient.dajWszystkie(super.getActivity().getApplicationContext());

        FloatingActionButton floatingActionButton = (FloatingActionButton) llLayout.findViewById(R.id.floatingActionButton_Klienci);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(faActivity.getApplicationContext(), DodajKlientaActivity.class);
                startActivity(i);
                listView.deferNotifyDataSetChanged();
            }
        });

        if (klienci != null) {
            final KlienciRowAdapter adapter = new KlienciRowAdapter(faActivity.getApplicationContext(), R.layout.row_klienci, klienci);
            listView.setAdapter(adapter);


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
