

```markdown
# BMI Calculator Android Application – Technical Report

***

## Project Information

| Item                     | Description                                           |
|--------------------------|-------------------------------------------------------|
| Course                   | ICS 2300 – Mobile Applications Design and Development |
| Assignment               | Group Assignment 1 – Android App for BMI Classification |
| Platform                 | Android                                               |
| Development Environment  | Android Studio                                        |

***

## 1. Application Overview

The **BMI Calculator** is an Android application designed to help users calculate their Body Mass Index (BMI) and determine their weight category based on standard health guidelines.

The application provides a simple and intuitive interface where users enter their weight and height, after which the calculated BMI and corresponding health classification are displayed.

### 1.1 Purpose

The application enables users to:

- Calculate BMI using the standard medical formula
- Identify their weight category
- Perform a quick health assessment using a mobile device

***

## 2. App Design and Layout

### 2.1 User Interface Design

The application follows **Material Design** principles, ensuring a clean, modern, and intuitive user experience.  
The interface is simple and accessible to users of all ages and technical skill levels.

### 2.2 Layout Structure (`activity_main.xml`)

#### Input Section

| Component       | ID          | Description                                      |
|-----------------|-------------|--------------------------------------------------|
| Weight Input    | `etWeight`  | Accepts user weight in kilograms                 |
| Height Input    | `etHeight`  | Accepts user height in meters                    |

- Input type: Decimal number
- Validation: Required and must be positive

#### Action Section

| Component        | ID             | Description                     |
|------------------|----------------|---------------------------------|
| Calculate Button | `btnCalculate` | Triggers BMI calculation        |

#### Output Section

| Component       | ID            | Description                                    |
|-----------------|---------------|------------------------------------------------|
| Result TextView | `tvBMIResult` | Displays BMI value and category                |

- Visibility: Hidden initially, shown after calculation

### 2.3 Design Principles Applied

- Minimal and simple interface
- Clear labels and hints
- Immediate feedback via Toast messages
- Inline validation for user guidance
- Readable text and accessible layout

***

## 3. Logic for BMI Calculation

### 3.1 BMI Formula

```
BMI = weight (kg) / height² (m²)
```

### 3.2 Implementation Logic (`MainActivity.java`)

#### 3.2.1 Component Initialization

```java
EditText etWeight, etHeight;
Button btnCalculate;
TextView tvBMIResult;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etWeight     = findViewById(R.id.etWeight);
    etHeight     = findViewById(R.id.etHeight);
    btnCalculate = findViewById(R.id.btnCalculate);
    tvBMIResult  = findViewById(R.id.tvBMIResult);
}
```

#### 3.2.2 Input Validation

**Empty Field Check**

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

**Number Format & Positive Value Validation**

```java
double weight, height;

try {
    weight = Double.parseDouble(etWeight.getText().toString().trim());
    height = Double.parseDouble(etHeight.getText().toString().trim());
} catch (NumberFormatException e) {
    Toast.makeText(MainActivity.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
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
```

#### 3.2.3 BMI Calculation

```java
double bmi = weight / (height * height);
```

#### 3.2.4 BMI Category Classification

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

#### BMI Classification Table

| Category       | BMI Range       | Health Description              |
|----------------|-----------------|---------------------------------|
| Underweight    | < 18.5          | Below healthy weight            |
| Normal weight  | 18.5 – 24.9     | Healthy weight                  |
| Overweight     | 25.0 – 29.9     | Above healthy weight            |
| Obese          | ≥ 30            | Significantly above healthy weight |

#### 3.2.5 Result Display

```java
String result = String.format("Your BMI: %.2f\nCategory: %s", bmi, category);

tvBMIResult.setText(result);
tvBMIResult.setVisibility(View.VISIBLE);

Toast.makeText(MainActivity.this, "BMI Calculated Successfully", Toast.LENGTH_SHORT).show();
```

***

## 4. Technical Implementation

### 4.1 MainActivity Structure

| Element            | Description                                      |
|--------------------|--------------------------------------------------|
| Class Variables    | Hold references to UI components                 |
| `onCreate()`       | Initializes UI and event listeners               |
| `OnClickListener`  | Handles BMI calculation logic                    |

### 4.2 Key Features

- Inline input validation
- Automatic cursor focus on invalid fields
- Toast messages for feedback
- Dynamic visibility of results
- Exception handling to prevent crashes
- Input sanitization using `trim()`

***

## 5. Test Case

| Test Case | Weight (kg) | Height (m) | Expected BMI | Expected Category | Status |
|-----------|-------------|------------|--------------|-------------------|--------|
| TC-01     | 70          | 1.75       | 22.86        | Normal weight     | Pass   |

**Test Result Analysis**

- BMI calculated accurately to two decimal places
- Category correctly classified
- Result displayed only after calculation
- Success Toast displayed
- All validations passed

***

**End of Document**
```

