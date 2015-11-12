package com.example.adam.sprzedawca.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam on 2015-10-26.
 */
public class KlienciRowAdapter extends ArrayAdapter<Klient> {
    List<Klient> klienci = new LinkedList<>();
    Context context;
    int layoutResourceId;

    public KlienciRowAdapter(Context context, int layoutResourceId, List<Klient> klienci) {
        super(context, layoutResourceId, klienci);
        this.context = context;
        this.layoutResourceId=layoutResourceId;
        this.klienci=klienci;
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView - konkretny wiersz w widoku wiersza
     * @param parent - kontrolka listy w widoku głównym
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(layoutResourceId,parent,false);
        TextView mainTextView = (TextView)rowView.findViewById(R.id.textView_row_klienci);
        TextView subTextView = (TextView)rowView.findViewById(R.id.textView_row_klienci_2);
        TextView sub2TextView = (TextView)rowView.findViewById(R.id.textView_row_klienci_3);
        Klient klient = new Klient();
        klient = getItem(position);

        mainTextView.setText(klient.getNazwa());
        subTextView.setText(" ul."+klient.getAdres()+", "+klient.getKod()+" "+klient.getMiejscowosc());
        sub2TextView.setText("tel.:"+klient.getTelefon()+"  |  NIP: "+klient.getNip()+"  |  REGON: "+klient.getRegon());
        return rowView;
//        return super.getView(position, convertView, parent);
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     */

    @Override
    public Klient getItem(int position) {
        Klient klient = new Klient();
        klient = klienci.get(position);
        return klient;

//        return super.getItem(position);
    }
}
