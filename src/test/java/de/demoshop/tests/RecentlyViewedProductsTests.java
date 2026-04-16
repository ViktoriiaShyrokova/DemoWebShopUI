package de.demoshop.tests;


import de.demoshop.core.TestBase;
import de.demoshop.data.ProductData;
import de.demoshop.data.UserData;
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
        Desktop desktop = ProductData.computerWithoutDetails();
        app.getProduct().clickOnProduct(desktop);
        app.getHomePage().clickOnLogo();
        Assert.assertTrue(app.getProduct().isRecentlyViewedBlockPresent());
        Assert.assertTrue(app.getProduct().isProductShownInRecentlyViewedBlock(desktop));
    }

    @Test
    public void previouslyViewedProductIsShownInTheListTestForLoggedInUser(){
        app.getUser().clickOnLoginLink();
        de.demoshop.model.User user = new de.demoshop.model.User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD);
        app.getUser().fillInLoginForm(user);
        app.getUser().clickOnLoginInButton();

        Desktop desktop = ProductData.computerWithoutDetails();
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
