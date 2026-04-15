package de.demoshop.tests;

import de.demoshop.core.TestBase;
import de.demoshop.model.Desktop;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ChangeProductQtyInTheCartTest extends TestBase {
    @BeforeMethod
    public void preconditions() {
        if(!app.getUser().isLogoutLinkNotPresent()) app.getUser().clickOnLogoutLink();
        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(new de.demoshop.model.User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        app.getUser().clickOnLoginInButton();
        Desktop desktop = new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
        app.getProduct().clickOnAddToCart(desktop);
        app.getProduct().waitBeforeSuccessMessageDisappear();
        app.getHomePage().clickOnShoppingCartLinkAtTheHeader();
    }

    @Test
    public void userCanChangeProductQtyInTheCartTest() {
        app.getProduct().changeQtyInTheCart(5);
        Assert.assertTrue(app.getProduct().isSubTotalCalculatedCorrect());
    }

}
