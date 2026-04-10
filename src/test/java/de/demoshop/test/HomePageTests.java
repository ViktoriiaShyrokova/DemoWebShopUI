package de.demoshop.test;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void isHomePageShownTest(){
        Assert.assertTrue(isElementPresent(By.className("topic-html-content-header")));
    }

}
