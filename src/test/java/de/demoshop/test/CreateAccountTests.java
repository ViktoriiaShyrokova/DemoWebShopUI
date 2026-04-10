package de.demoshop.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @Test//(enabled = false)
    public void newUserRegisterPositiveTest() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        click(By.cssSelector("[href='/register']"));

        type(By.id("FirstName"), "Jack");
        type(By.id("LastName"), "Sparrow");
        type(By.id("Email"), "jack" + i + "@sparrow.com");
        type(By.id("Password"), "Password1!");
        type(By.id("ConfirmPassword"), "Password1!");
        click(By.id("register-button"));
        Assert.assertTrue(isElementPresent(By.cssSelector("[href='/logout']")));
        assertEqualsTextShown(By.cssSelector(".header-links .account"), "jack" + i + "@sparrow.com");
    }

    @Test
    public void userCanClickOnRegisterButtonOnTheSignInPage() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        click(By.cssSelector("[href='/login']"));
        click(By.className("register-button"));

        type(By.id("FirstName"), "Jack");
        type(By.id("LastName"), "Sparrow");
        type(By.id("Email"), "jack" + i + "@sparrow.com");
        type(By.id("Password"), "Password1!");
        type(By.id("ConfirmPassword"), "Password1!");
        click(By.id("register-button"));
        Assert.assertTrue(isElementPresent(By.cssSelector("[href='/logout']")));
        assertEqualsTextShown(By.cssSelector(".header-links .account"), "jack" + i + "@sparrow.com");
    }


    @Test
    public void existedUserRegisterNegativeTest() {
        click(By.cssSelector("[href='/register']"));
        type(By.id("FirstName"), "Jack");
        type(By.id("LastName"), "Sparrow");
        type(By.id("Email"), "jack@sparrow.com");
        type(By.id("Password"), "Password1!");
        type(By.id("ConfirmPassword"), "Password1!");
        click(By.id("register-button"));
        Assert.assertTrue(isElementPresent(By.className("validation-summary-errors")));
        assertEqualsTextShown(By.className("validation-summary-errors"), "The specified email already exists");
        Assert.assertFalse(isElementPresent(By.cssSelector("[href='/logout']")));
    }


}
