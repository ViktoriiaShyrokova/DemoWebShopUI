package de.demoshop.tests;

import de.demoshop.core.TestBase;
import de.demoshop.data.ProductData;
import de.demoshop.data.UserData;
import de.demoshop.model.Apparel;
import de.demoshop.model.Desktop;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ItemTests extends TestBase {

    @BeforeMethod
    public void preconditions() {
        if(!app.getUser().isLogoutLinkNotPresent()) app.getUser().clickOnLogoutLink();
        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(new de.demoshop.model.User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginInButton();
    }

    @Test
    public void loggedInUserAddsItemToCartTest() {
        Desktop desktop = ProductData.computerWithoutDetails();
        app.getProduct().clickOnAddToCart(desktop);
        Assert.assertTrue(app.getProduct().isProductAddedToTheCartMessagePresent());
        app.getProduct().waitBeforeSuccessMessageDisappear();
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(app.getProduct().isItemPresentInTheCart(desktop));
    }

    @Test
    public void loggedInUserAddsTwoEqualItemsToCartTest() {
        Desktop desktop = ProductData.simpleComputer();
        app.getProduct().clickOnAddToCart(desktop);
        app.getProduct().clickOnProductAttributes(desktop);
        app.getProduct().fillQty(desktop,2);
        app.getProduct().clickOnAddToCartButtonOnProductDetailsPage(desktop);

        Assert.assertTrue(app.getProduct().isProductAddedToTheCartMessagePresent());
        app.getProduct().waitBeforeSuccessMessageDisappear();
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(app.getProduct().isItemPresentInTheCart(desktop));
        Assert.assertTrue(app.getProduct().isQtyPresentInTheCart(2));
    }

    @Test
    public void notLoggedInUserAddsItemToCartTest() {
        app.getUser().clickOnLogoutLink();

        Desktop desktop = ProductData.computerWithoutDetails();
        app.getProduct().clickOnAddToCart(desktop);
        Assert.assertTrue(app.getProduct().isProductAddedToTheCartMessagePresent());
        app.getProduct().waitBeforeSuccessMessageDisappear();
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(app.getProduct().isItemPresentInTheCart(desktop));
    }

    @Test
    public void itemAddedToTheCartIsInTheCartAfterLoginTest() {
        app.getUser().clickOnLogoutLink();

        Desktop desktop = ProductData.computerWithoutDetails();
        app.getProduct().clickOnAddToCart(desktop);

        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(new de.demoshop.model.User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginInButton();

        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(app.getProduct().isItemPresentInTheCart(desktop));
    }

    @Test
    public void addToCartOutOfStockProductTest() {
        app.getProduct().clickOnApparelShoesInTopMenu();
        Apparel apparel = ProductData.OutOfStockBag();
        app.getProduct().clickOnAddToCart(apparel);

        Assert.assertTrue(app.getProduct().isOutOfStockMessagePresent());
        Assert.assertTrue(app.getHomePage().isCartEmpty());
    }

    @Test
    public void addToCartProductWithRequiredDetailsTest() {
        Desktop desktop = ProductData.simpleComputer();
        app.getProduct().clickOnAddToCart(desktop);
        app.getProduct().clickOnProductAttributes(desktop);
        app.getProduct().clickOnAddToCartButtonOnProductDetailsPage(desktop);

        Assert.assertTrue(app.getProduct().isProductAddedToTheCartMessagePresent());
        app.getProduct().waitBeforeSuccessMessageDisappear();
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(app.getProduct().isItemPresentInTheCart(desktop));
        Assert.assertTrue(app.getProduct().isItemDetailsPresentInTheCart(desktop));
    }


    @Test
    public void addToCartProductWithOptionalDetailsTest() {
        Desktop desktop = ProductData.expensiveComputer();
        app.getProduct().clickOnAddToCart(desktop);
        app.getProduct().clickOnProductAttributes(desktop);
        app.getProduct().clickOnAddToCartButtonOnProductDetailsPage(desktop);
        Assert.assertTrue(app.getProduct().isProductAddedToTheCartMessagePresent());
        app.getProduct().waitBeforeSuccessMessageDisappear();
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(app.getProduct().isItemPresentInTheCart(desktop));
        Assert.assertTrue(app.getProduct().isItemDetailsPresentInTheCart(desktop));
    }



    @AfterMethod(enabled = true)
    public void postconditions() {
        if(!app.getHomePage().isCartEmpty()) {
            app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
            app.getProduct().removeProductFromCart();
        }
    }

}
