package de.demoshop.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @Test//(enabled = false)
    public void newUserRegisterPositiveTest() {

        clickOnRegisterLink();
        String email = fillRegisterFormWithGeneratedEmail(new User()
                .setName("Jack")
                .setLastName("Sparrow")
                .setPassword("Password1!"));
        clickOnRegisterButton();
        Assert.assertTrue(isLogoutLinkPresent());
        Assert.assertTrue(verifyUserEmailInHeader(email));

    }

    @Test
    public void userCanClickOnRegisterButtonOnTheSignInPage() {

        openRegisterPageFromSignInPage();

        String email = fillRegisterFormWithGeneratedEmail(new User()
                .setName("Jack")
                .setLastName("Sparrow")
                .setPassword("Password1!"));
        clickOnRegisterButton();
        Assert.assertTrue(isLogoutLinkPresent());
        Assert.assertTrue(verifyUserEmailInHeader(email));
    }


    @Test
    public void existedUserRegisterNegativeTest() {
        clickOnRegisterLink();
        fillRegisterForm(new User()
                .setName("Jack")
                .setLastName("Sparrow")
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        clickOnRegisterButton();
        Assert.assertTrue(isValidationPresent());
        Assert.assertTrue(isEmailAlreadyExists());
        Assert.assertFalse(isLogoutLinkPresent());
    }


}
