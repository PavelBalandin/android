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
    public Map<String, Float> lengthMap = new HashMap<String, Float>() {{
        put("centimeter", 0.01f);
        put("meter", 1f);
        put("kilometer", 1000f);
        put("inch", 0.0254f);
        put("mile", 1609.34f);
        put("yard", 0.9144f);
        put("foot", 0.3048f);
    }};


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


}
