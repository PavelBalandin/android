package com.example.converter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ConverterFragment extends Fragment {

    protected Spinner spinnerFrom;
    protected Spinner spinnerTo;
    protected EditText editTextFrom;
    protected EditText editTextTo;
    protected Button convertButton;
    protected Button clearButton;

    protected float convert(float value, float rateForm, float rateTo) {
        return value * rateForm / rateTo;
    }

    protected void initComponents(View view) {
        spinnerFrom = view.findViewById(R.id.spinner_from);
        spinnerTo = view.findViewById(R.id.spinner_to);
        convertButton = view.findViewById(R.id.convert_button);
        clearButton = view.findViewById(R.id.clear_button);
        editTextFrom = view.findViewById(R.id.edit_text_from);
        editTextTo = view.findViewById(R.id.edit_text_to);
    }

    protected void clearEditText() {
        editTextFrom.setText("");
        editTextTo.setText("");
    }

    protected float getFromValue() {
        float value = 0;
        try {
            value = Float.parseFloat(editTextFrom.getText().toString());
        } catch (Exception exception) {
            incorrectValueMassage();
        }

        return value;
    }

    protected void incorrectValueMassage() {
        Toast.makeText(getContext(), "Incorrect value!", Toast.LENGTH_SHORT).show();
    }


}
