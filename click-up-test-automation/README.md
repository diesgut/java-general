# Selenium Automation Project

This project is an automated testing framework built with **Java** and **Selenium WebDriver**, with **ExtentReports** for detailed test reporting.
The design patterns used is Page Object Model (POM), which helps to organize test code by separating test logic from UI interactions.
Each relevant page of the application has a corresponding class that contains locators and actions.

## Requirements

- Java 21 or higher
- Maven
- Google Chrome browser
- Compatible version of ChromeDriver (placed in your system PATH)

## How to Run the Tests

1. Clone the repository:
   ```bash
   git clone https://github.com/diesgut/ClickUpTestAutomation.git
   cd your-project
   ```

2. After the tests are executed, the ExtentReports HTML report will be generated in the following location:
   ```
   /test-output/extent-report.html
   ```

## Test Scenarios Covered

1. **TC01 - All fields are empty**
    - Attempts to submit the form with required fields empty.
    - Validation messages are asserted.

2. **TC02 - Invalid Email Format**
    - Enters an incorrectly formatted email (e.g., "user@domain") and verifies error handling.
    - Validation messages are asserted.

3. **TC03 - Invalid password length**
    - Enter a password that does not meet the minimum character limit.
    - Validation messages are asserted.

4.  **TC04 - Registration successful when the email and password confirmation fields are not required**
    - Enters valid information and asserts successful login and redirection.

5. **TC05 - Register user with email in use**
    - Enter an email already in use.
    - Validation messages are asserted.

## Reporting

The project uses **ExtentReports** for generating visually rich HTML reports. After running the tests, you can open the report at:

```
/test-output/extent-report.html
```

## Notes

- Make sure your ChromeDriver version matches your Chrome browser version.
- You can customize test data and configuration in the `resources` folder if applicable.

## Project Structure

```
ClickUpTestAutomation
├── .idea
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.diesgut
│   │   │       ├── config
│   │   │       │   ├── GlobalConstants.java
│   │   │       │   └── WebDriverConfig.java
│   │   │       ├── domain
│   │   │       │   └── UserRegister.java
│   │   │       ├── pages
│   │   │       │   ├── ClickUpPO.java
│   │   │       │   └── SignUpPO.java
│   │   │       └── utils
│   │   │           ├── BrowserUtils.java
│   │   │           ├── JavaScriptUtils.java
│   │   │           └── PropertiesUtils.java
│   │   └── resources
│   │       └── selenium.properties
│   └── test
│       ├── java
│       │   └── com.diesgut
│       │       ├── pages
│       │       │   ├── SignUpPOTest.java
│       │       │   └── UserRegisterCasesDP.java
│       │       └── utils
│       │           ├── MessagesUtils.java
│       │           └── BaseTestReport.java
│       └── resources
│           ├── messages_en.properties
│           ├── messages_es.properties
│           └── user_register_invalid_cases.json
├── test-output
│   └── extent-report.html
├── .gitignore
├── pom.xml
└── README.md
```