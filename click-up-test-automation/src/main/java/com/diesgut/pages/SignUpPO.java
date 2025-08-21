package com.diesgut.pages;

import com.diesgut.domain.UserRegister;
import com.diesgut.utils.BrowserUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPO extends ClickUpPO{
    public final static String PAGE ="signup";
    public final static String SUCCESSFUL_PAGE ="signup/validate-email";

    private final By signUpForm = By.id("signup-form");

    private By fullNameInput = By.id("signup-username-input");
    private By emailInput = By.id("signup-email");
    private By passwordInput = By.id("signup-password-input");
    private By registerButton = By.xpath("//button[@type='submit' and @data-test='signup-form__submit-button']");

    private By fullNameInputErrorMessage = By.cssSelector("#cu-signup-form__error_username .cu-form__error-text");
    private By emailInputErrorMessage = By.cssSelector("#cu-signup-form__error_email .cu-form__error-text");
    private By passwordInputErrorMessage = By.cssSelector("#cu-signup-form__error_password .cu-form__error-text");

    //Future use
    private By emailConfirmationInput = By.id("signup-confirm-email");
    private By passwordConfirmationInput = By.id("signup-confirm-password-input");

    private By emailConfirmationInputErrorMessage = By.cssSelector("#cu-signup-form__error_confirm_email .cu-form__error-text");
    private By passwordConfirmationInputErrorMessage = By.cssSelector("#cu-signup-form__error_confirm_password .cu-form__error-text");


    @Getter
    private boolean isEmailConfirmationPresent;
    @Getter
    private boolean isPasswordConfirmationPresent;

    public SignUpPO(WebDriver driver) {
        super(driver);
    }

    public void fillInfo(UserRegister userRegister) {
        enterFullName(userRegister.getFullName());
        enterEmailInput(userRegister.getProfessionalEmail());
        enterPasswordInput(userRegister.getPassword());
      /*  enterEmailConfirmationInput(userRegister.getProfessionalEmailToConfirm());
        enterPasswordConfirmationInput(userRegister.getPasswordToConfirm());*/
    }

    public void enterFullName(String fullName) {
        BrowserUtils.waitForSendKeys(fullNameInput, fullName);
    }

    public void enterEmailInput(String email) {
        BrowserUtils.waitForSendKeys(emailInput, email);
    }

    public void enterPasswordInput(String password) {
        BrowserUtils.waitForSendKeys(passwordInput, password);
    }

    public void enterEmailConfirmationInput(String email) {
        if(BrowserUtils.waitUntilVisible(emailConfirmationInput).isPresent()){
            this.isEmailConfirmationPresent = true;
            BrowserUtils.waitForSendKeys(emailInput, email);
        }
    }

    public void enterPasswordConfirmationInput(String password) {
        if(BrowserUtils.waitUntilVisible(passwordConfirmationInput).isPresent()){
            this.isPasswordConfirmationPresent = true;
            BrowserUtils.waitForSendKeys(passwordConfirmationInput, password);
        }
    }

    public void clickRegisterButton() {
        BrowserUtils.waitForClickable(registerButton);
    }

    public String getFullNameInputErrorMessage() {
        return BrowserUtils.waitForGetText(fullNameInputErrorMessage);
    }

    public String getEmailInputErrorMessage() {
        return BrowserUtils.waitForGetText(emailInputErrorMessage);
    }

    public String getPasswordInputErrorMessage() {
        return BrowserUtils.waitForGetText(passwordInputErrorMessage);
    }

    public String getEmailConfirmationInputErrorMessage() {
        return BrowserUtils.waitForGetText(emailConfirmationInputErrorMessage);
    }

    public String getPasswordConfirmationInputErrorMessage() {
        return BrowserUtils.waitForGetText(passwordConfirmationInputErrorMessage);
    }

    public void visit() {
        super.visit(PAGE);
    }

    public  void waitForNotURL(String url) {
        BrowserUtils.waitForNotURL(url);
    }


    @Override
    public void isReady() {
        BrowserUtils.waitFor(signUpForm);
    }
}
