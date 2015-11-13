package com.example.adam.sprzedawca.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.adam.sprzedawca.Activity.DodajTowarActivity;
import com.example.adam.sprzedawca.Adapter.TowaryRowAdapter;
import com.example.adam.sprzedawca.Model.Towar;
import com.example.adam.sprzedawca.R;

import java.util.ArrayList;
import java.util.List;

public class TowaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout llLayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_towary, container, false);
        // Of course you will want to faActivity and llLayout in the class and not this method to access them in the rest of
        // the class, just initialize them here

        // Content of previous onCreate() here
        ListView lista = (ListView) llLayout.findViewById(R.id.listView_Towary);

        FloatingActionButton floatingActionButton = (FloatingActionButton) llLayout.findViewById(R.id.floatingActionButton_Towary);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(faActivity, DodajTowarActivity.class);
                startActivity(intent);
            }
        });

        List<Towar> towary = Towar.dajWszystkie(faActivity.getApplicationContext());
        if(towary!=null) {
            TowaryRowAdapter adapter = new TowaryRowAdapter(faActivity.getApplicationContext(), R.layout.row_towary, towary);
            lista.setAdapter(adapter);
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
