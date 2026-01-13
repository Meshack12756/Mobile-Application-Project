


---

BMI Calculator Android Application – Technical Report


---

Project Information

Course: ICS 2300 – Mobile Applications Design and Development

Assignment: Group Assignment 1 – Android App for BMI Classification

Platform: Android

Development Environment: Android Studio



---

1. Application Overview

The BMI Calculator is an Android application designed to help users calculate their Body Mass Index (BMI) and determine their weight category based on standard health guidelines. The application provides a simple and intuitive interface where users enter their weight and height, after which the calculated BMI and corresponding health classification are displayed.

1.1 Purpose

The purpose of the application is to:

Calculate BMI using the standard medical formula

Display the user’s weight category

Provide a quick and user-friendly health assessment tool



---

2. App Design and Layout

2.1 User Interface Design

The application follows Material Design principles, ensuring a clean, modern, and intuitive user experience. The layout is simple and accessible to users of all ages and technical backgrounds.

2.2 Layout Structure (activity_main.xml)

The user interface is divided into three main sections:

Input Section

Weight Input Field (EditText)

ID: etWeight

Input Type: Decimal number

Hint: Enter weight in kg

Purpose: Captures the user’s weight in kilograms

Validation: Required, positive numeric value


Height Input Field (EditText)

ID: etHeight

Input Type: Decimal number

Hint: Enter height in meters

Purpose: Captures the user’s height in meters

Validation: Required, positive numeric value


Action Section

Calculate Button (Button)

ID: btnCalculate

Text: Calculate BMI

Purpose: Triggers input validation and BMI calculation


Output Section

BMI Result Display (TextView)

ID: tvBMIResult

Purpose: Displays the calculated BMI and category

Visibility: Hidden initially and shown after successful calculation


2.3 Design Principles Applied

Simplicity through minimal UI components

Clear labels, hints, and error messages

Responsive feedback using Toast notifications

Accessible layout with readable text sizes

User feedback through inline validation errors



---

3. Logic for BMI Calculation

3.1 BMI Formula

BMI = weight (kg) / height² (m²)

3.2 Implementation Logic (MainActivity.java)

3.2.1 Component Initialization

EditText etWeight, etHeight;
Button btnCalculate;
TextView tvBMIResult;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etWeight = findViewById(R.id.etWeight);
    etHeight = findViewById(R.id.etHeight);
    btnCalculate = findViewById(R.id.btnCalculate);
    tvBMIResult = findViewById(R.id.tvBMIResult);
}

3.2.2 Input Validation

Empty Field Validation

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

Number Format Validation

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

Positive Value Validation

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

3.2.3 BMI Calculation

double bmi = weight / (height * height);

3.2.4 BMI Category Classification

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

BMI Classification Table

Category	BMI Range	Description

Underweight	BMI < 18.5	Below healthy weight
Normal weight	18.5 – 24.9	Healthy weight range
Overweight	25.0 – 29.9	Above healthy weight
Obese	BMI ≥ 30	Significantly above normal


3.2.5 Result Display

String result = String.format(
        "Your BMI: %.2f\nCategory: %s", bmi, category);

tvBMIResult.setText(result);
tvBMIResult.setVisibility(View.VISIBLE);

Toast.makeText(MainActivity.this,
        "BMI Calculated Successfully",
        Toast.LENGTH_SHORT).show();


---

4. Technical Implementation

4.1 MainActivity Structure

Class Variables

EditText etWeight, etHeight;
Button btnCalculate;
TextView tvBMIResult;

Event Handling

btnCalculate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // Validation and BMI calculation logic
    }
});

4.2 Key Features

Inline validation using setError()

Focus control using requestFocus()

Toast notifications for user feedback

Dynamic visibility of result display

Exception handling to prevent crashes

Input sanitization using trim()



---

5. Test Case

Test Case	Weight (kg)	Height (m)	Expected BMI	Expected Category	Result

TC-01	70	1.75	22.86	Normal weight	Pass


Test Result Analysis

BMI calculated correctly to two decimal places

Category correctly identified as Normal weight

Result displayed after calculation

Success message shown via Toast

All validation checks passed



---

