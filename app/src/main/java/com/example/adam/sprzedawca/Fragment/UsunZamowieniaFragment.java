package com.example.adam.sprzedawca.Fragment;

import android.app.AlertDialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.adam.sprzedawca.Db.DbHelper;
import com.example.adam.sprzedawca.Model.Zamowienie;


/**
 * Created by Adam on 2015-11-25.
 */
public class UsunZamowieniaFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Usunięcie wszystkich zamówień!");
        builder.setMessage("Czy na pewno chcesz usunąć wszystkie zamówienia?. Ta operacja jest nieodwracalna.");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Zamowienie.kasujWszystkie(getActivity().getApplicationContext());
                dismiss();
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        Dialog dialog = builder.create();
        return dialog;
    }

}
