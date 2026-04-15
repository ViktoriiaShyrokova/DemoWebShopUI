package de.demoshop.tests;


import de.demoshop.core.TestBase;
import de.demoshop.model.Desktop;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RecentlyViewedProductsTests extends TestBase {
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

}
