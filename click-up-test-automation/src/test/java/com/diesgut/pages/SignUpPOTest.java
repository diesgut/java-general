package com.diesgut.pages;

import com.diesgut.BaseTestReport;
import com.diesgut.config.GlobalConstants;
import com.diesgut.config.WebDriverConfig;
import com.diesgut.domain.UserRegister;
import com.diesgut.utils.MessagesUtils;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.stream.IntStream;

@Slf4j
public class SignUpPOTest extends BaseTestReport {

    private SignUpPO signUpPO;

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true, enabled = true)
    public void beforeTest(@Optional(GlobalConstants.BROWSER_CHROME) String browser) {
        WebDriverConfig.getInstance().setDriver(browser);
        signUpPO = new SignUpPO(WebDriverConfig.getInstance().getWebDriver());
    }

    @AfterTest(alwaysRun = true, enabled = true)
    protected void testTeardown() throws Exception {
        // signUpPO.closeDriver();
    }

/*
    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true, enabled = true)
    protected void testSetup(@Optional(GlobalConstants.BROWSER_CHROME) String browser) throws Exception {
        signUpPO.visit();
    }
*/

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, enabled = true)
    protected void beforeMethod(Method method, Object[] testData, @Optional(GlobalConstants.BROWSER_CHROME) String browser) throws Exception {
        signUpPO.visit();

        Test testAnnotation = method.getAnnotation(Test.class);
        String testDescription = "The method is " + method.getName();

        if (testData.length > 0 && testData[0] instanceof UserRegister userRegister) {
            test = extent.createTest(
                    userRegister.getTestId() + " - " + userRegister.getTestCaseDescription(),
                    testDescription
            );
        } else {
            test = extent.createTest(testAnnotation.description(), testDescription);
        }
        test.assignCategory("SignUp Tests");
    }
  /*
        @AfterMethod(alwaysRun = true, enabled = true)
        protected void afterMethod() throws Exception {
          / WebDriverConfig.getInstance().getWebDriver().manage().deleteAllCookies();
            WebDriverConfig.getInstance().getWebDriver().navigate().refresh();
            signUpPO.isReady();
            signUpPO.closeDriver();
        }
    */

    @Test(priority = 1, dataProvider = "userRegisterInvalidData", dataProviderClass = UserRegisterCasesDP.class)
    public void testUserRegistration(UserRegister userRegister) {
        test.assignCategory("SignUp Tests");

        Reporter.log("Running test case: " + userRegister.getTestId() + " - " + userRegister.getTestCaseDescription());
        test.info("Running test case: " + userRegister.getTestId() + " - " + userRegister.getTestCaseDescription());

        test.info("Filling registration form with test data");
        signUpPO.fillInfo(userRegister);

        test.info("Clicking register button");
        signUpPO.clickRegisterButton();

        test.info("Validating error messages");
        String actualFullNameError = signUpPO.getFullNameInputErrorMessage();
        String expectedFullNameError = MessagesUtils.getMessage(userRegister.getExpectedFullNameError());
        Assert.assertEquals(actualFullNameError, expectedFullNameError);
        test.info("Full Name Error Message: " + actualFullNameError);

        String actualEmailError = signUpPO.getEmailInputErrorMessage();
        String expectedEmailError = MessagesUtils.getMessage(userRegister.getExpectedEmailError());
        Assert.assertEquals(actualEmailError, expectedEmailError);
        test.info("Email Error Message: " + actualEmailError);

        String actualPasswordError = signUpPO.getPasswordInputErrorMessage();
        String expectedPasswordError = MessagesUtils.getMessage(userRegister.getExpectedPasswordError());
        Assert.assertEquals(actualPasswordError, expectedPasswordError);
        test.info("Password Error Message: " + actualPasswordError);

        if (signUpPO.isEmailConfirmationPresent()) {
            String actualEmailConfirmError = signUpPO.getEmailConfirmationInputErrorMessage();
            String expectedEmailConfirmError = MessagesUtils.getMessage(userRegister.getExpectedEmailConfirmError());
            Assert.assertEquals(actualEmailConfirmError, expectedEmailConfirmError);
            test.info("Email Confirmation Error Message: " + actualEmailConfirmError);
        }

        if (signUpPO.isPasswordConfirmationPresent()) {
            String actualPasswordConfirmError = signUpPO.getPasswordConfirmationInputErrorMessage();
            String expectedPasswordConfirmError = MessagesUtils.getMessage(userRegister.getExpectedPasswordConfirmError());
            Assert.assertEquals(actualPasswordConfirmError, expectedPasswordConfirmError);
            test.info("Password Confirmation Error Message: " + actualPasswordConfirmError);
        }
    }

    @Test(priority = 2, description = "TC04 - Registration successful when the email and password confirmation fields are not required")
    public void testSuccessfulRegistration_NoConfirmationFields() throws InterruptedException {
        Faker faker = new Faker();
        UserRegister userRegister = UserRegister.builder()
                .fullName("Diego E Gutierrez")
                .professionalEmail("indigo_ct2@hotmail.com") // to change for faker
                .password("M1Passw0rd123")
                .build();

        test.info("Attempting registration with potentially used email");

        boolean registrationSuccessful = false;
        for (int index = 1; index <= 3; index++) {
            test.info("Registration attempt #" + index);
            String sourceUrl = signUpPO.getCurrentUrl();
            test.info("Current URL: " + sourceUrl);

            test.info("Filling form with data: " + userRegister.getProfessionalEmail());
            signUpPO.fillInfo(userRegister);
            Thread.sleep(500);

            test.info("Clicking register button");
            signUpPO.clickRegisterButton();

            String emailErrorMessage = signUpPO.getEmailInputErrorMessage();
            if (!emailErrorMessage.equals(MessagesUtils.getMessage("signup.email.in.use"))) {
                test.info("Email is not in use, waiting for redirect");
                signUpPO.waitForNotURL(sourceUrl);
                String targetUrl = signUpPO.getCurrentUrl();
                test.info("Redirected to URL: " + targetUrl);

                boolean success = targetUrl.endsWith(SignUpPO.SUCCESSFUL_PAGE);
                Assert.assertTrue(success, "The registration end URL is not as expected: " + targetUrl);
                test.info("Registration successful!");
                registrationSuccessful = true;
                break;
            } else {
                test.warning("Email already in use: " + userRegister.getProfessionalEmail());
                String newEmail = faker.internet().emailAddress();
                test.info("Trying with new email: " + newEmail);
                userRegister.setProfessionalEmail(newEmail);
            }
        }

        if (!registrationSuccessful) {
            test.warning("TC05 - Failed to register after 3 attempts with different emails");
        }
    }

    @Test(priority = 3, description = "TC05 - Register user with email in use")
    public void testEmailInUse() throws InterruptedException {
        UserRegister userRegister = UserRegister.builder()
                .fullName("Diego E Gutierrez")
                .professionalEmail("indigo_ct2@hotmail.com") // Known to be in use
                .password("M1Passw0rd123")
                .build();

        test.info("Testing registration with known used email: " + userRegister.getProfessionalEmail());
        test.info("Filling registration form");
        signUpPO.fillInfo(userRegister);

        test.info("Clicking register button");

        Thread.sleep(500);
        signUpPO.clickRegisterButton();

        String actualEmailError = signUpPO.getEmailInputErrorMessage();
        String expectedEmailError = MessagesUtils.getMessage("signup.email.in.use");
        test.info("Validating 'Email in use' error message");
        Assert.assertEquals(actualEmailError, expectedEmailError);
        test.info("Received expected error: " + actualEmailError);
    }
}
