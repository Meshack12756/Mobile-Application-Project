```markdown
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
- Understand their weight category (Underweight, Normal weight, Overweight, or Obese)
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
  - Validation: Required field with positive number validation

- **Height Input Field (EditText)**: 
  - ID: `etHeight`
  - Input Type: Decimal number
  - Hint: "Enter height in meters"
  - Purpose: Captures the user's height in meters
  - Validation: Required field with positive number validation

**Action Section**
- **Calculate Button (Button)**:
  - ID: `btnCalculate`
  - Text: "Calculate BMI"
  - Purpose: Triggers the BMI calculation and validation when clicked

**Output Section**
- **BMI Result Display (TextView)**:
  - ID: `tvBMIResult`
  - Purpose: Displays the calculated BMI value (formatted to 2 decimal places) and category
  - Visibility: Initially hidden, becomes visible after successful calculation

**2.3 Design Principles Applied**
- **Simplicity**: Minimalist design with only essential elements
- **Clarity**: Clear labels, hints, and error messages for each input field
- **Responsiveness**: Immediate feedback through Toast notifications and error messages
- **Accessibility**: Large touch targets and readable text sizes
- **User Feedback**: Visual cues through error highlighting and success messages

---

**3. Logic for BMI Calculation**

**3.1 BMI Formula**

The application uses the standard BMI calculation formula:

```
BMI = weight (kg) / height² (m²)
```

**3.2 Implementation Logic (MainActivity.java)**

**3.2.1 Component Initialization**

```java
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
```
- Declares UI components as class-level variables
- Initializes all components using `findViewById()` in the `onCreate` method
- Links XML layout elements to Java objects

**3.2.2 Input Validation**

The application implements comprehensive multi-level validation:

**Empty Field Validation:**
```java
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
```
- Checks if input fields are empty before processing
- Displays inline error messages using `setError()`
- Moves cursor focus to the problematic field using `requestFocus()`
- Prevents further execution with `return` statement

**Number Format Validation:**
```java
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
```
- Attempts to parse string inputs to double values
- Catches `NumberFormatException` for invalid numeric formats
- Displays Toast message for user-friendly error notification

**Positive Value Validation:**
```java
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
```
- Ensures weight and height are positive numbers
- Prevents mathematically invalid or illogical inputs
- Provides specific error messages for each field

**3.2.3 BMI Calculation**

```java
double bmi = weight / (height * height);
```
- Performs the mathematical calculation using double precision
- Implements the standard BMI formula: BMI = weight / height²
- Uses parentheses to ensure correct order of operations

**3.2.4 Category Classification**

The application classifies BMI values according to WHO standards:

```java
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
```

**Classification Ranges:**

| Category | BMI Range | Health Implication |
|----------|-----------|-------------------|
| Underweight | BMI < 18.5 | Below healthy weight |
| Normal weight | 18.5 ≤ BMI < 25 | Healthy weight range |
| Overweight | 25 ≤ BMI < 30 | Above healthy weight |
| Obese | BMI ≥ 30 | Significantly above healthy weight |

**3.2.5 Result Display**

```java
String result = String.format("Your BMI: %.2f\nCategory: %s", bmi, category);
tvBMIResult.setText(result);
tvBMIResult.setVisibility(View.VISIBLE);

Toast.makeText(MainActivity.this, 
        "BMI Calculated Successfully", 
        Toast.LENGTH_SHORT).show();
```
- Formats the BMI value to 2 decimal places using `String.format()`
- Combines BMI value and category in a multi-line string using `\n`
- Makes the result TextView visible using `setVisibility(View.VISIBLE)`
- Displays a success Toast notification to confirm calculation completion

---

**4. Technical Implementation**

**4.1 MainActivity.java Structure**

**Class Variables**
```java
EditText etWeight, etHeight;
Button btnCalculate;
TextView tvBMIResult;
```
- Declared at class level for accessibility throughout the activity
- Represents the main UI components of the application

**onCreate Method**
- Entry point of the activity lifecycle
- Initializes UI components using `findViewById()`
- Sets up click listener for the calculate button using anonymous inner class
- Contains all the calculation and validation logic within the button's `onClick` method

**Event Handling**
```java
btnCalculate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // Validation and calculation logic
    }
});
```
- Uses `OnClickListener` interface to handle button clicks
- Implements all BMI calculation logic within the click event
- Ensures calculation only occurs when user explicitly requests it

**4.2 Key Features**

- **Inline Error Display**: Uses `setError()` method to show validation errors directly on input fields
- **Focus Management**: Automatically moves cursor to problematic fields using `requestFocus()`
- **Toast Notifications**: Provides non-intrusive feedback for both errors and success
- **Dynamic Visibility**: Result TextView is only shown after successful calculation
- **Exception Handling**: Try-catch block prevents app crashes from invalid inputs
- **Input Sanitization**: `trim()` method removes leading/trailing whitespace

---

**5. Test Case**

The application has been tested with the following scenario to ensure accuracy and functionality:

| Test Case | Weight (kg) | Height (m) | Expected BMI | Expected Category | Expected Behavior | Status |
|-----------|-------------|------------|--------------|-------------------|-------------------|---------|
| TC-01 | 70 | 1.75 | 22.86 | Normal weight | Display "Your BMI: 22.86" and "Category: Normal weight", show success Toast | ✓ Pass |

**Test Result Analysis:**
- BMI calculation is accurate to 2 decimal places (22.86)
- Category correctly classified as "Normal weight" (BMI within 18.5-25 range)
- Result TextView becomes visible after calculation
- Success Toast message displayed: "BMI Calculated Successfully"
- All validation checks passed before calculation execution
```
