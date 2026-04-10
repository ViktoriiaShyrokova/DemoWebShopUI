package de.demoshop.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void existedUserLoginPositiveTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.id("Email"), "jack@sparrow.com");
        type(By.id("Password"), "Password1!");
        click(By.cssSelector("[value='Log in']"));
        Assert.assertTrue(isElementPresent(By.cssSelector("[href='/logout']")));
        assertEqualsTextShown(By.cssSelector(".header-links .account"),"jack@sparrow.com");
    }


    @Test
    public void userLoginWithNonExistedInDatabaseEmailNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.id("Email"), "jack@sparroww.com");
        type(By.id("Password"), "Password1!");
        click(By.cssSelector("[value='Log in']"));
        Assert.assertFalse(isElementPresent(By.cssSelector("[href='/logout']")));
        assertEqualsTextShown(By.className("validation-summary-errors"),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void userLoginWithWrongPasswordNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.id("Email"), "jack@sparrow.com");
        type(By.id("Password"), "Password1!!");
        click(By.cssSelector("[value='Log in']"));
        Assert.assertFalse(isElementPresent(By.cssSelector("[href='/logout']")));
        assertEqualsTextShown(By.className("validation-summary-errors"),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }
}
