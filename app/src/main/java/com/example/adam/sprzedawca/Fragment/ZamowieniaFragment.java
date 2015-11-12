package com.example.adam.sprzedawca.Fragment;


import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.adam.sprzedawca.Adapter.KlienciRowAdapter;
import com.example.adam.sprzedawca.Adapter.ZamowieniaRowAdapter;
import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.Model.Zamowienie;
import com.example.adam.sprzedawca.R;

import java.util.ArrayList;
import java.util.List;

public class ZamowieniaFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout llLayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_zamowienia, container, false);
        // Of course you will want to faActivity and llLayout in the class and not this method to access them in the rest of
        // the class, just initialize them here

        // Content of previous onCreate() here
        ArrayList<String> sZamownienia= new ArrayList<>();
//        ListView listView = (ListView) super.getActivity().findViewById(R.id.listView_Klienci);
        ListView listView = (ListView) llLayout.findViewById(R.id.lV_Zamowienia);

        List<Zamowienie> zamowienia = Zamowienie.dajWszystkie(faActivity.getApplicationContext());

//        for (Zamowienie zamowienie:zamowienia){
//            String sKlient, sTowar, sZamowienie;
//
//
//            sKlient = klient.getNazwa()+" - ulica: "+klient.getAdres()+", "+klient.getKod()+" "+klient.getMiejscowosc()+", NIP: "+
//                    klient.getNip()+", REGON: "+klient.getRegon()+", telefon: "+klient.getTelefon();
//            sZamownienia.add(sKlient);
//        }
        if(zamowienia!=null) {
            ZamowieniaRowAdapter adapter = new ZamowieniaRowAdapter(faActivity.getApplicationContext(),R.layout.row_zamowienia,zamowienia);
            listView.setAdapter(adapter);
        }

        // Don't use this method, it's handled by inflater.inflate() above :
        // setContentView(R.layout.activity_layout);

        // The FragmentActivity doesn't contain the layout directly so we must use our instance of     LinearLayout :
//        llLayout.findViewById(R.id.someGuiElement);
        // Instead of :
        // findViewById(R.id.someGuiElement);
        return llLayout; // We must return the loaded Layout
    }
}
