BMI Calculator Android Application - Technical Report

Project Information
- Course: ICS 2300 Mobile Applications Design and Development
- Assignment: Group Assignment 1 - Android App for BMI Classification
- Platform: Android
- Development Environment: Android Studio

---

**1. Application Overview**

The BMI Calculator is an Android application designed to help users calculate their Body Mass Index (BMI) and determine their weight category based on standardized health metrics. The application provides a simple, intuitive interface for users to input their weight and height, then displays their calculated BMI along with the corresponding health classification.

**1.1 Purpose**

The primary purpose of this application is to provide users with a quick and accurate way to:
- Calculate their BMI using the standard formula
- Understand their weight category (Underweight, Normal, Overweight, or Obese)
- Monitor their health metrics in a user-friendly mobile interface

---

**2. App Design and Layout**

**2.1 User Interface Design**

The application follows Material Design principles to ensure a clean, modern, and intuitive user experience. The layout is designed to be simple yet functional, making it accessible for users of all ages and technical abilities.

**2.2 Layout Structure (activity_main.xml)**

The main layout consists of the following components:

**Input Section**
- **Weight Input Field (EditText)**: 
  - ID: `etWeight`
  - Input Type: Decimal number
  - Hint: "Enter weight in kg"
  - Purpose: Captures the user's weight in kilograms

- **Height Input Field (EditText)**: 
  - ID: `etHeight`
  - Input Type: Decimal number
  - Hint: "Enter height in meters"
  - Purpose: Captures the user's height in meters

**Action Section**
- **Calculate Button (Button)**:
  - ID: `btnCalculate`
  - Text: "Calculate BMI"
  - Purpose: Triggers the BMI calculation when clicked

**Output Section**
- **BMI Result Display (TextView)**:
  - ID: `tvResult`
  - Purpose: Displays the calculated BMI value (formatted to 2 decimal places)

- **Category Display (TextView)**:
  - ID: `tvCategory`
  - Purpose: Displays the BMI classification category

**2.3 Design Principles Applied**
- **Simplicity**: Minimalist design with only essential elements
- **Clarity**: Clear labels and hints for each input field
- **Responsiveness**: Immediate feedback upon button press
- **Accessibility**: Large touch targets and readable text sizes

---

**3. Logic for BMI Calculation**

**3.1 BMI Formula**

The application uses the standard BMI calculation formula:
BMI = weight (kg) / height² (m²)
**3.2 Implementation Logic (MainActivity.java)**

**3.2.1 Input Capture**
```java
String weightStr = etWeight.getText().toString().trim();
String heightStr = etHeight.getText().toString().trim();
Retrieves user input from EditText fields
Trims whitespace to prevent formatting errors
3.2.2 BMI Calculation
double weight = Double.parseDouble(weightStr);
double height = Double.parseDouble(heightStr);
double bmi = weight / (height * height);
Converts string inputs to double values
Performs the mathematical calculation using double precision for accuracy
3.2.3 Category Classification
The application classifies BMI values according to WHO standards:
String category;
if (bmi < 18.5) {
    category = "Underweight";
} else if (bmi < 25) {
    category = "Normal";
} else if (bmi < 30) {
    category = "Overweight";
} else {
    category = "Obese";
}
Classification Ranges:
Category
BMI Range
Underweight
BMI < 18.5
Normal
18.5 ≤ BMI < 25
Overweight
25 ≤ BMI < 30
Obese
BMI ≥ 30
3.2.4 Result Display
tvResult.setText(String.format("BMI: %.2f", bmi));
tvCategory.setText("Category: " + category);
Displays BMI rounded to 2 decimal places
Shows the corresponding health category
4. Technical Implementation
4.1 MainActivity.java Structure
Class Variables
private EditText etWeight, etHeight;
private Button btnCalculate;
private TextView tvResult, tvCategory;
onCreate Method
Initializes UI components using findViewById()
Sets up click listener for the calculate button
calculateBMI Method
Core method containing all calculation logic
Handles input processing, computation, and output display
