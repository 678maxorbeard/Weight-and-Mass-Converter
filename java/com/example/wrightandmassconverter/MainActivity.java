package com.example.wrightandmassconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputWeight;
    private Spinner inputUnit;
    private TextView outputWeight;

    private static final String[] UNITS = {"Kilograms", "Grams", "Pounds", "Ounces"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputWeight = findViewById(R.id.input_weight);
        inputUnit = findViewById(R.id.input_unit);
        outputWeight = findViewById(R.id.output_weight);
        Button convertButton = findViewById(R.id.convert_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UNITS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputUnit.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeight();
            }
        });
    }

    private void convertWeight() {
        String inputValue = inputWeight.getText().toString();
        if (inputValue.isEmpty()) {
            outputWeight.setText("Please enter a value.");
            return;
        }

        double weight = Double.parseDouble(inputValue);
        String selectedUnit = inputUnit.getSelectedItem().toString();
        double convertedWeight;

        switch (selectedUnit) {
            case "Kilograms":
                convertedWeight = weight;
                break;
            case "Grams":
                convertedWeight = weight * 1000;
                break;
            case "Pounds":
                convertedWeight = weight * 2.20462;
                break;
            case "Ounces":
                convertedWeight = weight * 35.274;
                break;
            default:
                convertedWeight = weight;
                break;
        }

        outputWeight.setText(String.format("Converted Weight: %.4f", convertedWeight));
    }
}
