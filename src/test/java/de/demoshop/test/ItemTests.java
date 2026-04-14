package de.demoshop.test;

import de.demoshop.core.TestBase;
import de.demoshop.model.Desktop;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ItemTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(new de.demoshop.model.User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        app.getUser().clickOnLoginInButton();
    }

    @Test
    public void loggedInUserAddsItemToCartTest() {
        Desktop desktop = new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
        app.getProduct().clickOnAddToCart(desktop);
        Assert.assertTrue(app.getProduct().isProductAddedToTheCartMessagePresent());
        app.getProduct().waitBeforeSuccessMessageDisappear();
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(app.getProduct().isItemPresentInTheCart(desktop));
    }

    @Test
    public void notLoggedInUserAddsItemToCartTest() {
        app.getUser().clickOnLogoutLink();

        Desktop desktop = new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
        app.getProduct().clickOnAddToCart(desktop);
        Assert.assertTrue(app.getProduct().isProductAddedToTheCartMessagePresent());
        app.getProduct().waitBeforeSuccessMessageDisappear();
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(app.getProduct().isItemPresentInTheCart(desktop));
    }

    @Test
    public void itemAddedToTheCartIsInTheCartAfterLoginTest() {
        app.getUser().clickOnLogoutLink();

        Desktop desktop = new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
        app.getProduct().clickOnAddToCart(desktop);

        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(new de.demoshop.model.User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        app.getUser().clickOnLoginInButton();

        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(app.getProduct().isItemPresentInTheCart(desktop));
    }

    @Test
    public void addToCartOutOfStockProductTest() {
        app.getProduct().clickOnApparelShoesInTopMenu();
        Desktop desktop = new Desktop()
                .setDataProductid(29);
        app.getProduct().clickOnAddToCart(desktop);

        Assert.assertTrue(app.getProduct().isOutOfStockMessagePresent());
        Assert.assertTrue(app.getHomePage().isCartEmpty());
    }

    @Test
    public void addToCartProductWithRequiredDetailsTest() {
        Desktop desktop = new Desktop()
                .setDataProductid(75)
                .setTitle("Simple Computer")
                .setProcessor("Processor: Slow")
                .setRam("RAM: 2 GB")
                .setHdd("HDD: 320 GB")
                .setValueAttributes(List.of("product_attribute_75_5_31_96"));
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
        Desktop desktop = new Desktop()
                .setDataProductid(74)
                .setTitle("Build your own expensive computer")
                .setProcessor("Processor: Fast [+100.00]")
                .setRam("RAM: 8GB [+60.00]")
                .setHdd("HDD: 400 GB [+100.00]")
                .setSoftware("Software: Office Suite [+100.00]")
                .setValueAttributes(List.of("product_attribute_74_5_26_82", "product_attribute_74_6_27_85", "product_attribute_74_3_28_87", "product_attribute_74_8_29_89"));
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
