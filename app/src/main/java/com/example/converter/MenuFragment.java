package com.example.converter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    public Button menuButton1;
    public Button menuButton2;
    public Button menuButton3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuButton1 = view.findViewById(R.id.temperature_button);
        menuButton2 = view.findViewById(R.id.weight_button);
        menuButton3 = view.findViewById(R.id.length_button);

        menuButton1.setOnClickListener(v -> {
            menuHandler(new TemperatureFragment());
        });

        menuButton2.setOnClickListener(v -> {
            menuHandler(new WeightFragment());
        });

        menuButton3.setOnClickListener(v -> {
            menuHandler(new LengthFragment());
        });


    }

    public void menuHandler(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

}
