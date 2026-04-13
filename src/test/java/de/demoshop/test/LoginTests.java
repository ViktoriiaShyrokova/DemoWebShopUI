package de.demoshop.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void existedUserLoginPositiveTest(){
        clickOnLoginLink();
        User user = new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!");

        fillInLoginForm(user);
        clickOnLoginInButton();

        Assert.assertTrue(isLogoutLinkPresent());
        Assert.assertTrue(verifyUserEmailInHeader(user.getEmail()));
    }


    @Test
    public void userLoginWithNonExistedInDatabaseEmailNegativeTest(){
        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        clickOnLoginInButton();
        Assert.assertFalse(isLogoutLinkPresent());
        Assert.assertTrue(isErrorNoCustomerFoundPresent());
    }

    @Test
    public void userLoginWithWrongPasswordNegativeTest(){
        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!"));
        clickOnLoginInButton();
        Assert.assertFalse(isLogoutLinkPresent());
        Assert.assertTrue(isErrorCredentialsAreIncorectPresent());
    }

}
