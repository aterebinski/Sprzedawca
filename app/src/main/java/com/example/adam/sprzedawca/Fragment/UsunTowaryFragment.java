package com.example.adam.sprzedawca.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.adam.sprzedawca.Model.Towar;


/**
 * Created by Adam on 2015-11-25.
 */
public class UsunTowaryFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Usunięcie wszystkich towarów!");
        builder.setMessage("Czy na pewno chcesz usunąć wszystkie towary?. Ta operacja jest nieodwracalna.");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Towar.kasujWszystkie(getActivity().getApplicationContext());
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
