```markdown
# BMI Calculator Android Application - Technical Report

## Project Information
- **Course:** ICS 2300 Mobile Applications Design and Development
- **Assignment:** Group Assignment 1 - Android App for BMI Classification
- **Platform:** Android
- **Development Environment:** Android Studio

---

## 1. Application Overview

The BMI Calculator is an Android application designed to help users calculate their Body Mass Index (BMI) and determine their weight category based on standardized health metrics. The application provides a simple, intuitive interface for users to input their weight and height, then displays their calculated BMI along with the corresponding health classification.

### 1.1 Purpose
The primary purpose of this application is to provide users with a quick and accurate way to:
- Calculate their BMI using the standard formula
- Understand their weight category (Underweight, Normal, Overweight, or Obese)
- Monitor their health metrics in a user-friendly mobile interface

---

## 2. App Design and Layout

### 2.1 User Interface Design
The application follows Material Design principles to ensure a clean, modern, and intuitive user experience. The layout is designed to be simple yet functional, making it accessible for users of all ages and technical abilities.

### 2.2 Layout Structure (`activity_main.xml`)

The main layout consists of the following components:

#### Input Section
- **Weight Input Field (`EditText`)**: 
  - ID: `etWeight`
  - Input Type: Decimal number
  - Hint: "Enter weight in kg"
  - Purpose: Captures the user's weight in kilograms

- **Height Input Field (`EditText`)**: 
  - ID: `etHeight`
  - Input Type: Decimal number
  - Hint: "Enter height in meters"
  - Purpose: Captures the user's height in meters

#### Action Section
- **Calculate Button (`Button`)**:
  - ID: `btnCalculate`
  - Text: "Calculate BMI"
  - Purpose: Triggers the BMI calculation when clicked

#### Output Section
- **BMI Result Display (`TextView`)**:
  - ID: `tvResult`
  - Purpose: Displays the calculated BMI value (formatted to 2 decimal places)

- **Category Display (`TextView`)**:
  - ID: `tvCategory`
  - Purpose: Displays the BMI classification category

### 2.3 Design Principles Applied
- **Simplicity**: Minimalist design with only essential elements
- **Clarity**: Clear labels and hints for each input field
- **Responsiveness**: Immediate feedback upon button press
- **Accessibility**: Large touch targets and readable text sizes

---

## 3. Logic for BMI Calculation

### 3.1 BMI Formula
The application uses the standard BMI calculation formula:

```
BMI = weight (kg) / height² (m²)
```

### 3.2 Implementation Logic (`MainActivity.java`)

#### 3.2.1 Input Capture
```java
String weightStr = etWeight.getText().toString().trim();
String heightStr = etHeight.getText().toString().trim();
```
- Retrieves user input from EditText fields
- Trims whitespace to prevent formatting errors

#### 3.2.2 Input Validation
The application implements multiple validation checks:

1. **Empty Field Check**:
   ```java
   if (weightStr.isEmpty() || heightStr.isEmpty()) {
       Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
       return;
   }
   ```
   - Ensures both fields contain data before calculation

2. **Positive Value Check**:
   ```java
   if (weight <= 0 || height <= 0) {
       Toast.makeText(this, "Please enter valid positive values", Toast.LENGTH_SHORT).show();
       return;
   }
   ```
   - Validates that weight and height are positive numbers

3. **Number Format Validation**:
   ```java
   try {
       double weight = Double.parseDouble(weightStr);
       double height = Double.parseDouble(heightStr);
       // ... calculation
   } catch (NumberFormatException e) {
       Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
   }
   ```
   - Catches invalid number formats and displays error message

#### 3.2.3 BMI Calculation
```java
double bmi = weight / (height * height);
```
- Performs the mathematical calculation using double precision for accuracy

#### 3.2.4 Category Classification
The application classifies BMI values according to WHO standards:

```java
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
```

**Classification Ranges:**
| Category | BMI Range |
|----------|-----------|
| Underweight | BMI < 18.5 |
| Normal | 18.5 ≤ BMI < 25 |
| Overweight | 25 ≤ BMI < 30 |
| Obese | BMI ≥ 30 |

#### 3.2.5 Result Display
```java
tvResult.setText(String.format("BMI: %.2f", bmi));
tvCategory.setText("Category: " + category);
```
- Displays BMI rounded to 2 decimal places
- Shows the corresponding health category

---

## 4. Technical Implementation

### 4.1 MainActivity.java Structure

#### Class Variables
```java
private EditText etWeight, etHeight;
private Button btnCalculate;
private TextView tvResult, tvCategory;
```

#### onCreate Method
- Initializes UI components using `findViewById()`
- Sets up click listener for the calculate button

#### calculateBMI Method
- Core method containing all calculation logic
- Handles input validation, computation, and output display

### 4.2 Error Handling Strategy
The application implements comprehensive error handling:

1. **User Input Errors**: Toast messages for empty or invalid inputs
2. **Number Format Errors**: Try-catch block for parsing exceptions
3. **Logical Errors**: Validation for negative or zero values

---

## 5. Screenshots

### 5.1 Initial Screen
[Insert screenshot of the empty form]
- Shows the clean interface with input fields and calculate button

### 5.2 Normal BMI Result
[Insert screenshot showing a normal BMI calculation]
- Example: Weight: 70kg, Height: 1.75m, BMI: 22.86 (Normal)

### 5.3 Underweight Result
[Insert screenshot showing an underweight classification]
- Example: Weight: 50kg, Height: 1.75m, BMI: 16.33 (Underweight)

### 5.4 Overweight Result
[Insert screenshot showing an overweight classification]
- Example: Weight: 85kg, Height: 1.75m, BMI: 27.76 (Overweight)

### 5.5 Obese Result
[Insert screenshot showing an obese classification]
- Example: Weight: 100kg, Height: 1.75m, BMI: 32.65 (Obese)

### 5.6 Error Handling
[Insert screenshot showing error message]
- Example: Empty field validation or invalid input message

---

## 6. Testing

### 6.1 Test Cases

| Test Case | Input (Weight/Height) | Expected BMI | Expected Category | Result |
|-----------|----------------------|--------------|-------------------|--------|
| TC-01 | 70kg / 1.75m | 22.86 | Normal | Pass |
| TC-02 | 50kg / 1.75m | 16.33 | Underweight | Pass |
| TC-03 | 85kg / 1.75m | 27.76 | Overweight | Pass |
| TC-04 | 100kg / 1.75m | 32.65 | Obese | Pass |
| TC-05 | Empty / 1.75m | N/A | Error Message | Pass |
| TC-06 | 70kg / Empty | N/A | Error Message | Pass |
| TC-07 | -70kg / 1.75m | N/A | Error Message | Pass |
| TC-08 | abc / 1.75m | N/A | Error Message | Pass |

### 6.2 Device Testing
- **Emulator**: Pixel 5 API 33 (Android 13)
- **Physical Device**: [Add your test device if applicable]

---

## 7. Future Enhancements

Potential improvements for future versions:

1. **Unit Selection**: Toggle between metric (kg/m) and imperial (lbs/ft) units
2. **History Tracking**: Save and display BMI calculation history
3. **Visual Indicators**: Color-coded results or progress bars
4. **Health Tips**: Display personalized health recommendations based on BMI category
5. **Age/Gender Consideration**: More detailed analysis incorporating demographic factors
6. **Charts**: Visual representation of BMI trends over time
7. **Goal Setting**: Allow users to set and track weight goals

---

## 8. Conclusion

The BMI Calculator application successfully fulfills all the assignment requirements by providing:
- A simple and intuitive user interface
- Accurate BMI calculation using the standard formula
- Proper classification into health categories
- Robust error handling and input validation

The application demonstrates fundamental Android development concepts including UI design, event handling, data validation, and user feedback mechanisms.

---

## 9. References

1. World Health Organization (WHO) - BMI Classification Standards
2. Android Developer Documentation - https://developer.android.com
3. Material Design Guidelines - https://material.io/design

---

## 10. Group Members

| Name | Student ID | Role |
|------|-----------|------|
| [Member 1] | [ID] | [Role] |
| [Member 2] | [ID] | [Role] |
| [Member 3] | [ID] | [Role] |

---

## Appendix: Complete Source Code

### MainActivity.java
```java
[Insert complete MainActivity.java code here]
```

### activity_main.xml
```xml
[Insert complete activity_main.xml code here]
```

### AndroidManifest.xml
```xml
[Insert relevant portions of AndroidManifest.xml]
```

---

**Date of Submission:** [Insert Date]

**Academic Year:** [Insert Academic Year]
```

This markdown file provides a comprehensive, professional report that you can edit and customize with your specific details, screenshots, and code. Simply add your screenshots, group member information, and the complete source code in the appendix section.
