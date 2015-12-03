package com.example.adam.sprzedawca.Db;


import com.example.adam.sprzedawca.Model.Towar;
import java.util.Comparator;

/**
 * Created by Adam on 2015-12-01.
 */
public class SortTowarByName implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        Towar p1 = (Towar) o1;
        Towar p2 = (Towar) o2;
        return p1.getNazwa().compareTo(p2.getNazwa());
    }
}
