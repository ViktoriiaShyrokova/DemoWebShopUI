package de.demoshop.test;

import de.demoshop.test.entity.Desktop;
import de.demoshop.test.entity.Product;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

//clickOnAddToCartComputerWithoutDetailsIsInStock();
        clickOnAddToCart(new Desktop()
                .setDataProductid(31));
        clickOnShoppingCartLinkAtTheHeader();
        //pause(500);
        //Assert.assertTrue(isSuccessMessageShown());
        Assert.assertTrue(isItemWithoutDetailsPresentInTheCart());
    }

    @Test
    public void notLoggedInUserAddsItemToCartTest() {
        clickOnLogoutLink();

        clickOnAddToCart(new Desktop()
                .setDataProductid(31));
        clickOnShoppingCartLinkAtTheHeader();
        //Assert.assertTrue(isSuccessMessageShown());
        Assert.assertTrue(isItemWithoutDetailsPresentInTheCart());
    }

    @Test
    public void itemAddedToTheCartIsInTheCartAfterLoginTest() {
        clickOnLogoutLink();
        clickOnAddToCart(new Desktop()
                .setDataProductid(31));

        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        clickOnLoginInButton();

        clickOnShoppingCartLinkAtTheHeader();
        Assert.assertTrue(isItemWithoutDetailsPresentInTheCart());
    }

    @Test
    public void addToCartOutOfStockProductTest() {
        clickOnApparelShoesInTopMenu();
        clickOnAddToCartBagOutOfStock();
        Assert.assertTrue(isOutOfStockMessagePresent());
        Assert.assertTrue(isCartEmpty());
    }

    @Test
    public void addToCartProductWithRequiredDetailsTest() {
        clickOnAddToCartComputerWithRequiredDetailsIsInStock();
        clickOnProcessorRequiredField();
        clickOnAddToCartComputerWithRequiredFieldButton();
        clickOnShoppingCartLinkAtTheHeader();

        Assert.assertTrue(isRequiredItemDetailsPresentInTheCart());
        //Assert.assertTrue();
    }

    public void clickOnAddToCartComputerWithRequiredFieldButton() {
        click(By.id("add-to-cart-button-75"));
    }

    public void clickOnProcessorRequiredField() {
        click(By.id("product_attribute_75_5_31_96"));
    }

    @Test
    public void addToCartProductWithOptionalDetailsTest() {
        clickOnAddToCartComputerWithOptionalDetailsIsInStock();
        click(By.id("product_attribute_74_5_26_82"));
        click(By.id("product_attribute_74_6_27_85"));
        click(By.id("product_attribute_74_3_28_87"));
        click(By.id("product_attribute_74_8_29_89"));
        click(By.id("add-to-cart-button-74"));
        clickOnShoppingCartLinkAtTheHeader();

        Assert.assertTrue(isRequiredItemDetailsPresentInTheCart());
        Assert.assertTrue(isElementPresent(By.xpath("//tr[.//div[contains(@class,'attributes') and contains(., 'Processor: Fast [+100.00]') and contains(., 'RAM: 8GB [+60.00]') and contains(., 'HDD: 400 GB [+100.00]') and contains(., 'Software: Office Suite [+100.00]')]]")));
    }

    @AfterMethod(enabled = false)
    public void postconditions() {
        removeProductFromCart();
    }

}
