---

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
BMI Calculator Android Application – Technical Report
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Project Information

Course: ICS 2300 – Mobile Applications Design and Development

Assignment: Group Assignment 1 – Android App for BMI Classification

Platform: Android

Development Environment: Android Studio


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

1. Application Overview

The BMI Calculator is an Android application designed to help users calculate their Body Mass Index (BMI) and determine their weight category based on standardized health metrics. The application provides a simple and intuitive interface where users input their weight and height, after which the calculated BMI and corresponding health classification are displayed.

1.1 Purpose

The primary purpose of this application is to enable users to:

Calculate their BMI using the standard medical formula

Understand their weight classification

Monitor basic health metrics using a mobile application


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

2. App Design and Layout

2.1 User Interface Design

The application follows Material Design principles, ensuring a clean, modern, and user-friendly experience. The interface is simple and accessible to users of all ages and technical skill levels.

2.2 Layout Structure (activity_main.xml)

The main layout consists of three major sections:

Input Section

Weight Input Field (EditText)

ID: etWeight

Input Type: Decimal number

Hint: Enter weight in kg

Purpose: Captures the user’s weight in kilograms

Validation: Required, must be a positive number


Height Input Field (EditText)

ID: etHeight

Input Type: Decimal number

Hint: Enter height in meters

Purpose: Captures the user’s height in meters

Validation: Required, must be a positive number


Action Section

Calculate Button (Button)

ID: btnCalculate

Text: Calculate BMI

Purpose: Triggers validation and BMI calculation


Output Section

BMI Result Display (TextView)

ID: tvBMIResult

Purpose: Displays the calculated BMI (2 decimal places) and category

Visibility: Hidden initially, visible after successful calculation


2.3 Design Principles Applied

Simplicity: Minimal interface with essential components only

Clarity: Clear hints, labels, and error messages

Responsiveness: Instant feedback via Toast messages

Accessibility: Readable text and large touch targets

User Feedback: Inline errors and success notifications


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

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

Explanation:

UI components are declared as class-level variables

findViewById() links XML elements to Java objects

Initialization occurs in the onCreate() lifecycle method


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

Prevents calculation with missing inputs

Displays inline error messages

Automatically focuses on the invalid field


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

Ensures inputs are valid numbers

Prevents application crashes

Displays user-friendly error feedback


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

Rejects negative or zero values

Ensures logical and mathematical correctness


3.2.3 BMI Calculation

double bmi = weight / (height * height);

Uses double precision for accuracy

Applies the standard BMI formula


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

Classification Table

Category	BMI Range	Health Implication

Underweight	BMI < 18.5	Below healthy weight
Normal weight	18.5 ≤ BMI < 25	Healthy weight range
Overweight	25 ≤ BMI < 30	Above healthy weight
Obese	BMI ≥ 30	Significantly above healthy weight


3.2.5 Result Display

String result = String.format(
        "Your BMI: %.2f\nCategory: %s", bmi, category);

tvBMIResult.setText(result);
tvBMIResult.setVisibility(View.VISIBLE);

Toast.makeText(MainActivity.this,
        "BMI Calculated Successfully",
        Toast.LENGTH_SHORT).show();

BMI displayed to 2 decimal places

Result shown only after successful calculation

Confirmation provided via Toast message


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

4. Technical Implementation

4.1 MainActivity.java Structure

Class Variables

EditText etWeight, etHeight;
Button btnCalculate;
TextView tvBMIResult;

Accessible throughout the activity

Represent key UI elements


Event Handling

btnCalculate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // Validation and BMI calculation logic
    }
});

BMI calculation is user-triggered

All logic executed only on button click


4.2 Key Features

Inline validation using setError()

Automatic focus control with requestFocus()

Toast notifications for feedback

Dynamic visibility control of results

Exception handling with try-catch

Input sanitization using trim()


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

5. Test Case

Test Case	Weight (kg)	Height (m)	Expected BMI	Expected Category	Expected Behavior	Status

TC-01	70	1.75	22.86	Normal weight	Correct result displayed, success Toast shown	✓ Pass


Test Result Analysis

BMI calculated accurately (22.86)

Category correctly identified as Normal weight

Result TextView displayed successfully

Toast message confirms successful calculation

All validation checks executed correctly


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
