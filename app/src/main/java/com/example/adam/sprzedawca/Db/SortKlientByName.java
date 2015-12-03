package com.example.adam.sprzedawca.Db;

import com.example.adam.sprzedawca.Model.Klient;

import java.util.Comparator;

/**
 * Created by Adam on 2015-12-01.
 */
public class SortKlientByName implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        Klient p1 = (Klient) o1;
        Klient p2 = (Klient) o2;
        return p1.getNazwa().compareTo(p2.getNazwa());
    }
}
