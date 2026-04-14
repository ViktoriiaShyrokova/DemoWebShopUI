package de.demoshop.test;

import de.demoshop.core.TestBase;
import de.demoshop.model.Desktop;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveProductFromCartTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        app.getUser().clickOnLoginLink();
        de.demoshop.model.User user = new de.demoshop.model.User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!");

        app.getUser().fillInLoginForm(user);
        app.getUser().clickOnLoginInButton();
        Desktop desktop = new Desktop()
                .setDataProductid(31);
        app.getProduct().clickOnAddToCart(desktop);
        app.getProduct().waitBeforeSuccessMessageDisappear();
    }

    @Test
    public void removeProductFromTheCartTest() {
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        int sizeBefore = app.getProduct().sizeOfCartList();
        app.getProduct().removeProductFromCart();
        app.getProduct().pause(1000);
        int sizeAfter = app.getProduct().sizeOfCartList();
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

}
