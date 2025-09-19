package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class EditCityFragment extends DialogFragment {
    private City city;
    private AddCityFragment.AddCityDialogListener listener;

    public EditCityFragment(City city) {
        this.city = city;
    }

    public EditCityFragment() {} 

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityFragment.AddCityDialogListener) {
            listener = (AddCityFragment.AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);

        EditText cityNameInput = view.findViewById(R.id.edit_text_city_text);
        EditText provinceInput = view.findViewById(R.id.edit_text_province_text);

        if (city != null) {
            cityNameInput.setText(city.getName());
            provinceInput.setText(city.getProvince());
        }

        builder.setView(view)
                .setTitle(city == null ? "Add City" :"Edit City")
                .setPositiveButton("OK", (dialog, which) -> {
                    String name = cityNameInput.getText().toString();
                    String province = provinceInput.getText().toString();

                    if (city != null) {
                        city.setName(name);
                        city.setProvince(province);
                        listener.addCity(city);
                    } else {
                        listener.addCity(new City(name, province));
                    }
                })
                .setNegativeButton("Cancel", null);

        return builder.create();
    }
}
