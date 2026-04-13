package de.demoshop.test;


import org.testng.Assert;
import org.testng.annotations.Test;

public class RecentlyViewedProductsTests extends TestBase {
    @Test
    public void recentlyViewedProductIsShownInTheListTest(){
        clickOnProduct();
        clickOnLogo();
        Assert.assertTrue(isRecentlyViewedBlockPresent());
        Assert.assertTrue(isProductShownInRecentlyViewedBlock());
    }

}
