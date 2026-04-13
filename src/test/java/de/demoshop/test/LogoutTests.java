package de.demoshop.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        clickOnLoginInButton();
    }

    @Test
    public void loggedInUserCanLogoutTest() {
        clickOnLogoutLink();
        Assert.assertFalse(isLogoutLinkPresent());
        Assert.assertTrue(isLoginLinkPresent());
        Assert.assertTrue(isRegisterLinkPresent());
        Assert.assertTrue(isCartEmpty());
        Assert.assertTrue(isWishListEmpty());
    }

}
