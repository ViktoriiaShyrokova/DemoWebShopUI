package de.demoshop.tests;

import de.demoshop.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        if(!app.getUser().isLogoutLinkNotPresent()) app.getUser().clickOnLogoutLink();
        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(new de.demoshop.model.User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        app.getUser().clickOnLoginInButton();
    }

    @Test
    public void loggedInUserCanLogoutTest() {
        app.getUser().clickOnLogoutLink();
        Assert.assertTrue(app.getUser().isLoginLinkPresent());
        Assert.assertTrue(app.getUser().isRegisterLinkPresent());
        Assert.assertTrue(app.getHomePage().isCartEmpty());
        Assert.assertTrue(app.getHomePage().isWishListEmpty());
    }

}
