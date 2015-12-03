package com.example.adam.sprzedawca.Db;

import com.example.adam.sprzedawca.Model.Zamowienie;

import java.util.Comparator;

/**
 * Created by Adam on 2015-12-01.
 */
public class SortZamowienieByDate implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        Zamowienie p1 = (Zamowienie) o1;
        Zamowienie p2 = (Zamowienie) o2;
        return p1.getData().compareTo(p2.getData());
    }
}
