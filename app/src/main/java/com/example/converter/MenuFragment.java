package com.example.converter;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class MenuFragment extends Fragment {

    public Button menuButton1;
    public Button menuButton2;
    public Button menuButton3;
    private TextView textViewMenu;
    private TextView textViewVersion;

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
        textViewMenu = view.findViewById(R.id.text_menu);
        textViewVersion = view.findViewById(R.id.versionTextView);


        menuButton1.setOnClickListener(v -> {
            menuHandler(new TemperatureFragment());
        });

        menuButton2.setOnClickListener(v -> {
            menuHandler(new WeightFragment());
        });

        menuButton3.setOnClickListener(v -> {
            menuHandler(new LengthFragment());
        });


        view.findViewById(R.id.uaButton).setOnClickListener(v -> {
            Locale locale = new Locale("uk");
            changeLocale(locale);
        });

        view.findViewById(R.id.enButton).setOnClickListener(v -> {
            Locale locale = new Locale("en");
            changeLocale(locale);
        });


    }

    public void menuHandler(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    @SuppressWarnings("deprecation")
    private void changeLocale(Locale locale) {
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getContext().getResources()
                .updateConfiguration(configuration,
                        getContext()
                                .getResources()
                                .getDisplayMetrics());

        menuButton1.setText(R.string.entity_1);
        menuButton2.setText(R.string.entity_2);
        menuButton3.setText(R.string.entity_3);
        textViewVersion.setText(R.string.app_version);
        textViewMenu.setText(R.string.menu);

    }

}
