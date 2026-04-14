package de.demoshop.test;

import de.demoshop.test.entity.Desktop;
import de.demoshop.test.entity.Product;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        clickOnLoginInButton();
    }

    @Test
    public void loggedInUserAddsItemToCartTest() {
        Desktop desktop = new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
        clickOnAddToCart(desktop);
        clickOnShoppingCartLinkAtTheHeader();
        //pause(500);
        //Assert.assertTrue(isSuccessMessageShown());
        Assert.assertTrue(isItemPresentInTheCart(desktop));
    }

    @Test
    public void notLoggedInUserAddsItemToCartTest() {
        clickOnLogoutLink();

        Desktop desktop = new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
        clickOnAddToCart(desktop);
        clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(isItemPresentInTheCart(desktop));
    }

    @Test
    public void itemAddedToTheCartIsInTheCartAfterLoginTest() {
        clickOnLogoutLink();

        Desktop desktop = new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
        clickOnAddToCart(desktop);

        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        clickOnLoginInButton();

        clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(isItemPresentInTheCart(desktop));
    }

    @Test
    public void addToCartOutOfStockProductTest() {
        clickOnApparelShoesInTopMenu();
        Desktop desktop = new Desktop()
                .setDataProductid(29);
        clickOnAddToCart(desktop);

        Assert.assertTrue(isOutOfStockMessagePresent());
        Assert.assertTrue(isCartEmpty());
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
        clickOnAddToCart(desktop);
        clickOnProductAttributes(desktop);
        clickOnAddToCartButtonOnProductDetailsPage(desktop);
        clickOnShoppingCartLinkAtTheHeader();

        Assert.assertTrue(isItemPresentInTheCart(desktop));
        Assert.assertTrue(isItemDetailsPresentInTheCart(desktop));
    }

    public void clickOnAddToCartButtonOnProductDetailsPage(Product<?> product) {
        click(By.id(String.format("add-to-cart-button-%d", product.getDataProductid())));
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
        clickOnAddToCart(desktop);
        clickOnProductAttributes(desktop);
        click(By.id("add-to-cart-button-74"));
        clickOnShoppingCartLinkAtTheHeader();

        Assert.assertTrue(isItemPresentInTheCart(desktop));
        Assert.assertTrue(isItemDetailsPresentInTheCart(desktop));
    }

    public void clickOnProductAttributes(Desktop desktop) {
        desktop.getValueAttributes().forEach(a -> click(By.id(a)));
    }

    @AfterMethod(enabled = false)
    public void postconditions() {
        removeProductFromCart();
    }

}
