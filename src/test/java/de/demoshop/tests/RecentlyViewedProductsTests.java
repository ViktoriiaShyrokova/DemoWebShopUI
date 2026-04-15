package de.demoshop.tests;


import de.demoshop.core.TestBase;
import de.demoshop.model.Desktop;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecentlyViewedProductsTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if(!app.getUser().isLogoutLinkNotPresent()) app.getUser().clickOnLogoutLink();
    }

    @Test
    public void recentlyViewedProductIsShownInTheListTest(){
        Desktop desktop = new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
        app.getProduct().clickOnProduct(desktop);
        app.getHomePage().clickOnLogo();
        Assert.assertTrue(app.getProduct().isRecentlyViewedBlockPresent());
        Assert.assertTrue(app.getProduct().isProductShownInRecentlyViewedBlock(desktop));
    }

    @Test
    public void previouslyViewedProductisShownInTheListTestForLoggedInUser(){
        app.getUser().clickOnLoginLink();
        de.demoshop.model.User user = new de.demoshop.model.User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!");
        app.getUser().fillInLoginForm(user);
        app.getUser().clickOnLoginInButton();

        Desktop desktop = new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
        app.getProduct().clickOnProduct(desktop);
        app.getHomePage().clickOnLogo();

        app.getUser().clickOnLogoutLink();
        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(user);
        app.getUser().clickOnLoginInButton();

        Assert.assertTrue(app.getProduct().isRecentlyViewedBlockPresent());
        Assert.assertTrue(app.getProduct().isProductShownInRecentlyViewedBlock(desktop));
    }

}
