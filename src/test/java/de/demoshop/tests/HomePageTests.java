package de.demoshop.tests;


import de.demoshop.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void isHomePageShownTest(){
        Assert.assertTrue(app.getHomePage().isHomePageMainBlockPresent());
    }



}
