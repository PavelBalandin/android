package com.example.converter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TemperatureFragment extends ConverterFragment {
    private static final float MINIMUM_CELSIUS = -273.15f;
    private static final float MINIMUM_KELVIN = 0;
    private static final float MINIMUM_FAHRENHEIT = -459.67f;

    public Map<String, Float> temperatureMap = new HashMap<String, Float>() {{
        put("kelvin", 1f);
        put("celsius", 1f);
        put("fahrenheit", 1f);
    }};

    public static TemperatureFragment newInstance() {

        Bundle args = new Bundle();

        TemperatureFragment fragment = new TemperatureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, new ArrayList<>(temperatureMap.keySet()));
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        clearButton.setOnClickListener(v -> {
            clearEditText();
        });

        convertButton.setOnClickListener(v -> {
            float value = tC(getFromValue(), spinnerFrom.getSelectedItem().toString(), spinnerTo.getSelectedItem().toString());
            if (value != -1000)
                editTextTo.setText(Float.toString(value));
            else
                editTextTo.setText("0.0");
        });

    }


    public float tC(float value, String rateForm, String rateTo) {
        switch (rateForm) {
            case "celsius": {
                if (value >= MINIMUM_CELSIUS) {
                    if (rateTo.equals("kelvin"))
                        return value + 273.15f;
                    else if (rateTo.equals("fahrenheit"))
                        return (value * 9 / 5) + 32;
                } else {
                    incorrectValueMassage();
                    return -1000;
                }
                return value;
            }
            case "kelvin": {
                if (value >= MINIMUM_KELVIN) {
                    if (rateTo.equals("celsius"))
                        return value - 273.15f;
                    else if (rateTo.equals("fahrenheit"))
                        return (value - 273.15f) * 9 / 5 + 32;
                } else {
                    incorrectValueMassage();
                    return -1000;
                }
                return value;
            }
            case "fahrenheit": {
                if (value >= MINIMUM_FAHRENHEIT) {
                    if (rateTo.equals("celsius"))
                        return (value - 32) * 5 / 9;
                    else if (rateTo.equals("kelvin"))
                        return (value - 32) * 5 / 9 + 273.15f;
                } else {
                    incorrectValueMassage();
                    return -1000;
                }
                return value;

            }

            default:
                return value;

        }

    }

    @Override
    public float getFromValue() {
        float value = -1000;
        try {
            value = Float.parseFloat(editTextFrom.getText().toString());
        } catch (Exception exception) {
            incorrectValueMassage();
        }

        return value;
    }
}