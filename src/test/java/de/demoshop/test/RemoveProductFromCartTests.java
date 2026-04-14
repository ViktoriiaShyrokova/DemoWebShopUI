package de.demoshop.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveProductFromCartTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        clickOnLoginLink();
        User user = new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!");

        fillInLoginForm(user);
        clickOnLoginInButton();

        //clickOnAddToCartComputerWithoutDetailsIsInStock();
    }

    @Test
    public void removeProductFromTheCartTest() {
        clickOnShoppingCartLinkAtTheHeader();
        int sizeBefore = sizeOfCartList();
        removeProductFromCart();
        pause(1000);
        int sizeAfter = sizeOfCartList();
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

}
