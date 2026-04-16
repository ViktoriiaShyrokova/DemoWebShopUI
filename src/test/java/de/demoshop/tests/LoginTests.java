package de.demoshop.tests;

import de.demoshop.core.TestBase;
import de.demoshop.data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if(!app.getUser().isLogoutLinkNotPresent()) app.getUser().clickOnLogoutLink();
    }

    @Test
    public void existedUserLoginPositiveTest(){
        app.getUser().clickOnLoginLink();
        de.demoshop.model.User user = new de.demoshop.model.User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD);
        app.getUser().fillInLoginForm(user);
        app.getUser().clickOnLoginInButton();

        Assert.assertTrue(app.getUser().isLogoutLinkPresent());
        Assert.assertTrue(app.getUser().verifyUserEmailInHeader(user.getEmail()));
    }


    @Test
    public void userLoginWithNonExistedInDatabaseEmailNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(UserData.userWithNotExistedInDbEmail());
        app.getUser().clickOnLoginInButton();
        Assert.assertTrue(app.getUser().isLogoutLinkNotPresent());
        Assert.assertTrue(app.getUser().isErrorNoCustomerFoundPresent());
    }

    @Test
    public void userLoginWithWrongPasswordNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillInLoginForm(UserData.userWithWrongPassword());
        app.getUser().clickOnLoginInButton();
        Assert.assertTrue(app.getUser().isLogoutLinkNotPresent());
        Assert.assertTrue(app.getUser().isErrorCredentialsAreIncorrectPresent());
    }

}
