package com.example.adam.sprzedawca.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adam.sprzedawca.Model.Klient;
import com.example.adam.sprzedawca.Model.Zamowienie;
import com.example.adam.sprzedawca.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam on 2015-10-26.
 */
public class ZamowieniaRowAdapter extends ArrayAdapter<Zamowienie> {
    List<Zamowienie> zamowienia = new LinkedList<>();
    Context context;
    int layoutResourceId;

    public ZamowieniaRowAdapter(Context context, int layoutResourceId, List<Zamowienie> zamowienia) {
        super(context, layoutResourceId, zamowienia);
        this.context = context;
        this.layoutResourceId=layoutResourceId;
        this.zamowienia=zamowienia;
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
        TextView mainTextView = (TextView)rowView.findViewById(R.id.tV_row_zamowienia);
        TextView subTextView = (TextView)rowView.findViewById(R.id.tV_row_zamowienia2);
        TextView sub2TextView = (TextView)rowView.findViewById(R.id.tV_row_zamowienia3);
        Zamowienie zamowienie = new Zamowienie();
        zamowienie = getItem(position);

        mainTextView.setText(zamowienie.getTowar_name()+" - ilość:"+zamowienie.getSztuk()+" , wartość: "+zamowienie.getCena());
        subTextView.setText(zamowienie.getKlient_name());
        sub2TextView.setText(zamowienie.getData());
        return rowView;
//        return super.getView(position, convertView, parent);
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     */

    @Override
    public Zamowienie getItem(int position) {
        Zamowienie zamowienie = new Zamowienie();
        zamowienie = zamowienia.get(position);
        return zamowienie;

//        return super.getItem(position);
    }
}
