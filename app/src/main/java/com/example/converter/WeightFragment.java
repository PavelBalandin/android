package com.example.converter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeightFragment extends ConverterFragment {

    private Map<String, Float> weightMap = new HashMap<String, Float>();

    public static WeightFragment newInstance() {

        Bundle args = new Bundle();

        WeightFragment fragment = new WeightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
        intitMap();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, new ArrayList<>(weightMap.keySet()));
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        clearButton.setOnClickListener(v -> {
            clearEditText();
        });

        convertButton.setOnClickListener(v -> {
            float value = convert(getFromValue(), weightMap.get(spinnerFrom.getSelectedItem().toString()), weightMap.get(spinnerTo.getSelectedItem().toString()));
            editTextTo.setText(Float.toString(value));
        });
    }

    public void intitMap() {
        weightMap.put(getString(R.string.gram_name), 0.001f);
        weightMap.put(getString(R.string.kilogram_name), 1f);
        weightMap.put(getString(R.string.ton_name), 1000f);
        weightMap.put(getString(R.string.carat_name), 0.0002f);
        weightMap.put(getString(R.string.pound_name), 0.4535f);
        weightMap.put(getString(R.string.pood_name), 16.3807f);
    }
}
