package de.demoshop.fw;

import de.demoshop.core.BaseHelper;
import de.demoshop.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public boolean verifyUserEmailInHeader(String email) {
        return verifyByText(By.cssSelector(".header-links .account"), email);
    }

    public boolean isLogoutLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/logout']"));
    }

    public void clickOnRegisterButton() {
        click(By.id("register-button"));
    }

    public String fillRegisterFormWithGeneratedEmail(User user) {
        user.setEmail(generateEmail(user.getName(), user.getLastName()));
        fillRegisterForm(user);
        return user.getEmail();
    }

    public void fillRegisterForm(User user) {
        type(By.id("FirstName"), user.getName());
        type(By.id("LastName"), user.getLastName());
        type(By.id("Email"), user.getEmail());
        type(By.id("Password"), user.getPassword());
        type(By.id("ConfirmPassword"), user.getPassword());
    }

    private String generateEmail(String name, String lastName) {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        return name + i + "@" + lastName + ".com";
    }

    public void clickOnRegisterLink() {
        click(By.cssSelector("[href='/register']"));
    }

    public void openRegisterPageFromSignInPage() {
        click(By.cssSelector("[href='/login']"));
        click(By.className("register-button"));
    }

    public boolean isEmailAlreadyExists() {
        return verifyByText(By.className("validation-summary-errors"), "The specified email already exists");
    }

    public void clickOnLoginInButton() {
        click(By.cssSelector("[value='Log in']"));
    }

    public void fillInLoginForm(User user) {
        type(By.id("Email"), user.getEmail());
        type(By.id("Password"), user.getPassword());
    }

    public void clickOnLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    public boolean isRegisterLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/register']"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/login']"));
    }

    public void clickOnLogoutLink() {
        click(By.cssSelector("[href='/logout']"));
    }

    public boolean isErrorNoCustomerFoundPresent() {
        return verifyByText(By.className("validation-summary-errors"), "Login was unsuccessful. Please correct the errors and try again." +
                "\nNo customer account found");
    }

    public boolean isErrorCredentialsAreIncorrectPresent() {
        return verifyByText(By.className("validation-summary-errors"), "Login was unsuccessful. Please correct the errors and try again." +
                "\nThe credentials provided are incorrect");
    }
}
