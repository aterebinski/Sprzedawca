package com.example.adam.sprzedawca.Activity;

import android.app.Dialog;

import android.app.Fragment;
import android.app.FragmentManager;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adam.sprzedawca.Fragment.KlienciFragment;
import com.example.adam.sprzedawca.Fragment.TowaryFragment;
import com.example.adam.sprzedawca.Fragment.UsunKlientowFragment;
import com.example.adam.sprzedawca.Fragment.UsunTowaryFragment;
import com.example.adam.sprzedawca.Fragment.UsunZamowieniaFragment;
import com.example.adam.sprzedawca.Fragment.ZamowieniaFragment;
import com.example.adam.sprzedawca.R;

public class SprzedawcaActivity extends AppCompatActivity {

//    private final int DIALOG_USUN_ZAMOWIENIA = 1;
//    private final int DIALOG_USUN_KLIENTOW = 2;
//    private final int DIALOG_USUN_TOWARY = 3;
//    private final int DIALOG_USUN_ZAMOWIENIE = 4;
//    private final int DIALOG_USUN_KLIENTA = 5;
//    private final int DIALOG_USUN_TOWAR = 6;


    private String[] sLista = {"Zamówienia","Klienci","Towary"};
    private ListView mDrawerList = null;
    private DrawerLayout mDrawerLayout = null;
    private ArrayAdapter adapter;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sprzedawca);

        mTitle = mDrawerTitle = getTitle();


        mDrawerList = (ListView)findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        adapter = new ArrayAdapter(getApplicationContext(),R.layout.drawer_list_item,R.id.tV_drawer_item,sLista);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        );


        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /* Called whenever we call invalidateOptionsMenu() */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sprzedawca, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        FragmentManager fragmentManager = getFragmentManager();

        switch (item.getItemId()){
            case R.id.usun_zamowienia:
                UsunZamowieniaFragment usunZamowieniaFragment = new UsunZamowieniaFragment();
                usunZamowieniaFragment.show(fragmentManager,"Usun zamowienia");
                break;
            case R.id.usun_klientow:
                UsunKlientowFragment usunKlientowFragment = new UsunKlientowFragment();
                usunKlientowFragment.show(fragmentManager,"Usun klientow");
                break;
            case R.id.usun_towary:
                UsunTowaryFragment usunTowaryFragment = new UsunTowaryFragment();
                usunTowaryFragment.show(fragmentManager,"Usun towary");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Pass the event to ActionBarDrawerToggle, if it returns
//        // true, then it has handled the app icon touch event
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        // Handle your other action bar items...
//
//        return super.onOptionsItemSelected(item);
//    }


    private void selectItem(int position){
        Fragment fragment = null;
        switch (position){
            case 0: //zamowienia
                fragment = new ZamowieniaFragment();
                break;
            case 1: //klienci
                fragment = new KlienciFragment();
                break;
            case 2: //towary
            default:
                fragment = new TowaryFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(sLista[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }

        // Highlight the selected item, update the title, and close the drawer
//        mDrawerList.setItemChecked(position, true);
//        setTitle(sLista[position]);
//        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        try {
            getSupportActionBar().setTitle(mTitle);
        } catch (Exception e) {
            Log.e("SprzedawcaActivity","Nie ma obiektu ActionBar");
        }

    }
}
