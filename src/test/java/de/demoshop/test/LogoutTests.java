package de.demoshop.test;

import de.demoshop.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(new de.demoshop.model.User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        app.getUser().clickOnLoginInButton();
    }

    @Test
    public void loggedInUserCanLogoutTest() {
        app.getUser().clickOnLogoutLink();
        Assert.assertFalse(app.getUser().isLogoutLinkPresent());
        Assert.assertTrue(app.getUser().isLoginLinkPresent());
        Assert.assertTrue(app.getUser().isRegisterLinkPresent());
        Assert.assertTrue(app.getHomePage().isCartEmpty());
        Assert.assertTrue(app.getHomePage().isWishListEmpty());
    }

}
