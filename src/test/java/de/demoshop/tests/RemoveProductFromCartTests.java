package de.demoshop.tests;

import de.demoshop.core.TestBase;
import de.demoshop.data.ProductData;
import de.demoshop.data.UserData;
import de.demoshop.model.Desktop;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveProductFromCartTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        if(!app.getUser().isLogoutLinkNotPresent()) app.getUser().clickOnLogoutLink();
        app.getUser().clickOnLoginLink();
        de.demoshop.model.User user = new de.demoshop.model.User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD);

        app.getUser().fillInLoginForm(user);
        app.getUser().clickOnLoginInButton();
        Desktop desktop = ProductData.computerWithoutDetails();
        app.getProduct().clickOnAddToCart(desktop);
        app.getProduct().waitBeforeSuccessMessageDisappear();
    }

    @Test
    public void removeProductFromTheCartTest() {
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
        int sizeBefore = app.getProduct().sizeOfCartList();
        app.getProduct().removeProductFromCart();
        int sizeAfter = app.getProduct().sizeOfCartList();
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

}
