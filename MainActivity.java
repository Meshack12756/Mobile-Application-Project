package com.example.bmiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    Button btnCalculate, btnClear;
    TextView tvBMIResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        tvBMIResult = findViewById(R.id.tvBMIResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etWeight.getText().toString().trim().isEmpty()) {
                    etWeight.setError("Please enter weight in kg");
                    etWeight.requestFocus();
                    return;
                }

                if (etHeight.getText().toString().trim().isEmpty()) {
                    etHeight.setError("Please enter height in meters");
                    etHeight.requestFocus();
                    return;
                }

                double weight, height;

                try {
                    weight = Double.parseDouble(etWeight.getText().toString().trim());
                    height = Double.parseDouble(etHeight.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Please enter valid numeric values",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (weight <= 0) {
                    etWeight.setError("Weight must be greater than 0");
                    etWeight.requestFocus();
                    return;
                }

                if (height <= 0) {
                    etHeight.setError("Height must be greater than 0");
                    etHeight.requestFocus();
                    return;
                }

                double bmi = weight / (height * height);

                String category;
                if (bmi < 18.5) {
                    category = "Underweight";
                } else if (bmi < 25) {
                    category = "Normal weight";
                } else if (bmi < 30) {
                    category = "Overweight";
                } else {
                    category = "Obese";
                }

                String result = String.format("Your BMI: %.2f\nCategory: %s", bmi, category);
                tvBMIResult.setText(result);
                tvBMIResult.setVisibility(View.VISIBLE);

                Toast.makeText(MainActivity.this,
                        "BMI Calculated Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etWeight.setText("");
                etHeight.setText("");
                tvBMIResult.setText("");
                tvBMIResult.setVisibility(View.GONE);
                etWeight.requestFocus();

                Toast.makeText(MainActivity.this,
                        "Cleared. Enter new values.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
