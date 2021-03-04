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

public class LengthFragment extends ConverterFragment {
    public Map<String, Float> lengthMap = new HashMap<String, Float>();


    public static LengthFragment newInstance() {

        Bundle args = new Bundle();

        LengthFragment fragment = new LengthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_length, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
        initMap();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, new ArrayList<>(lengthMap.keySet()));
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        clearButton.setOnClickListener(v -> {
            clearEditText();
        });

        convertButton.setOnClickListener(v -> {
            float value = convert(getFromValue(), lengthMap.get(spinnerFrom.getSelectedItem().toString()), lengthMap.get(spinnerTo.getSelectedItem().toString()));
            editTextTo.setText(Float.toString(value));
        });
    }

    private void initMap() {
        {
            {
                lengthMap.put(getString(R.string.centimeter_name), 0.01f);
                lengthMap.put(getString(R.string.meter_name), 1f);
                lengthMap.put(getString(R.string.kilometer_name), 1000f);
                lengthMap.put(getString(R.string.inch_name), 0.0254f);
                lengthMap.put(getString(R.string.mile_name), 1609.34f);
                lengthMap.put(getString(R.string.yard_name), 0.9144f);
                lengthMap.put(getString(R.string.foot_name), 0.3048f);
            }
        }
        ;
    }


}
