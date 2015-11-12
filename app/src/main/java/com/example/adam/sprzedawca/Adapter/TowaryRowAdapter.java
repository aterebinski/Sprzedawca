package com.example.adam.sprzedawca.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adam.sprzedawca.Model.Towar;
import com.example.adam.sprzedawca.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam on 2015-10-28.
 */
public class TowaryRowAdapter extends ArrayAdapter<Towar> {
    Context context;
    int layoutResourceId;
    List<Towar> towary = new LinkedList<>();

    public TowaryRowAdapter(Context context, int layoutResourceId, List<Towar> towary) {
        super(context, layoutResourceId, towary);
        this.context=context;
        this.layoutResourceId= layoutResourceId;
        this.towary=towary;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutResourceId,parent,false);

        TextView mainTextView = (TextView)view.findViewById(R.id.textView_row_towary);
        TextView subTextView = (TextView)view.findViewById(R.id.textView_row_towary2);
        TextView sub2TextView = (TextView) view.findViewById(R.id.textView_row_towary3);

        mainTextView.setText(getItem(position).getNazwa());
        subTextView.setText("Cena: "+getItem(position).getCena()+" zł | Dostępne: "+getItem(position).getDostepne());
        sub2TextView.setText("Regał: "+getItem(position).getRegal()+", Półka: "+getItem(position).getPolka());
        return view;
    }

    @Override
    public Towar getItem(int position) {
        Towar towar;
        towar = towary.get(position);
        return towar;
    }
}
