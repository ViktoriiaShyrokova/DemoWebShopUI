package de.demoshop.tests;

import de.demoshop.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if(app.getUser().isLogoutLinkPresent()) app.getUser().clickOnLogoutLink();
    }

    @Test//(enabled = false)
    public void newUserRegisterPositiveTest() {
        app.getUser().clickOnRegisterLink();
        String email = app.getUser().fillRegisterFormWithGeneratedEmail(new de.demoshop.model.User()
                .setName("Jack")
                .setLastName("Sparrow")
                .setPassword("Password1!"));
        app.getUser().clickOnRegisterButton();
        Assert.assertTrue(app.getUser().isLogoutLinkPresent());
        Assert.assertTrue(app.getUser().verifyUserEmailInHeader(email));

    }

    @Test
    public void userCanClickOnRegisterButtonOnTheSignInPage() {

        app.getUser().openRegisterPageFromSignInPage();

        String email = app.getUser().fillRegisterFormWithGeneratedEmail(new de.demoshop.model.User()
                .setName("Jack")
                .setLastName("Sparrow")
                .setPassword("Password1!"));
        app.getUser().clickOnRegisterButton();
        Assert.assertTrue(app.getUser().isLogoutLinkPresent());
        Assert.assertTrue(app.getUser().verifyUserEmailInHeader(email));
    }


    @Test
    public void existedUserRegisterNegativeTest() {
        app.getUser().clickOnRegisterLink();
        app.getUser().fillRegisterForm(new de.demoshop.model.User()
                .setName("Jack")
                .setLastName("Sparrow")
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        app.getUser().clickOnRegisterButton();
        Assert.assertTrue(app.getUser().isValidationPresent());
        Assert.assertTrue(app.getUser().isEmailAlreadyExists());
        Assert.assertTrue(app.getUser().isLogoutLinkNotPresent());
    }


}
