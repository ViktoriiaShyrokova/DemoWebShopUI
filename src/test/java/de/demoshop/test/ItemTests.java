package de.demoshop.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemTests extends TestBase {


    @Test
    public void loggedInUserAddsItemToCartTest() {
        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        clickOnLoginInButton();

        clickOnAddToCart();
        clickOnShoppingCartLinkAtTheHeader();
        //pause(500);
        //Assert.assertTrue(isSuccessMessageShown());
        Assert.assertTrue(isItemPresentInTheCart());
    }
    @Test
    public void notLoggedInUserAddsItemToCartTest() {

        clickOnAddToCart();
        clickOnShoppingCartLinkAtTheHeader();
        //Assert.assertTrue(isSuccessMessageShown());
        Assert.assertTrue(isItemPresentInTheCart());
    }

    @Test
    public void itemAddedToTheCartIsInTheCartAfterLoginTest() {
        clickOnAddToCart();

        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        clickOnLoginInButton();

        clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(isItemPresentInTheCart());
    }

    @AfterMethod(enabled = false)
    public void postconditions() {
        removeProductFromCart();
    }

}
