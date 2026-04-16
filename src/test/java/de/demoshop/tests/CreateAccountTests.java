package de.demoshop.tests;

import de.demoshop.core.TestBase;
import de.demoshop.data.UserData;
import de.demoshop.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CreateAccountTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLogoutLinkNotPresent()) app.getUser().clickOnLogoutLink();
    }

    @Test//(enabled = false)
    public void newUserRegisterPositiveTest() {
        app.getUser().clickOnRegisterLink();
        String email = app.getUser().fillRegisterFormWithGeneratedEmail(new de.demoshop.model.User()
                .setName(UserData.NAME)
                .setLastName(UserData.LASTNAME)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnRegisterButton();
        Assert.assertTrue(app.getUser().isLogoutLinkPresent());
        Assert.assertTrue(app.getUser().verifyUserEmailInHeader(email));

    }

    @Test
    public void userCanClickOnRegisterButtonOnTheSignInPage() {

        app.getUser().openRegisterPageFromSignInPage();

        String email = app.getUser().fillRegisterFormWithGeneratedEmail(new de.demoshop.model.User()
                .setName(UserData.NAME)
                .setLastName(UserData.LASTNAME)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnRegisterButton();
        Assert.assertTrue(app.getUser().isLogoutLinkPresent());
        Assert.assertTrue(app.getUser().verifyUserEmailInHeader(email));
    }


    @Test
    public void existedUserRegisterNegativeTest() {
        app.getUser().clickOnRegisterLink();
        app.getUser().fillRegisterForm(new de.demoshop.model.User()
                .setName(UserData.NAME)
                .setLastName(UserData.LASTNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnRegisterButton();
        Assert.assertTrue(app.getUser().isValidationPresent());
        Assert.assertTrue(app.getUser().isEmailAlreadyExists());
        Assert.assertTrue(app.getUser().isLogoutLinkNotPresent());
    }

    @Test(dataProvider = "fillRegisterFormWithInvalidDataFromCsv")
    public void registerWithInvalidDataNegativeTest(User user) {
        app.getUser().clickOnRegisterLink();
        app.getUser().fillRegisterForm(user);
        app.getUser().clickOnRegisterButton();
        Assert.assertTrue(app.getUser().isRegistrationValidationPresent());
    }


    @DataProvider
    public Iterator<Object[]> fillRegisterFormWithInvalidDataFromCsv() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/RegistrationInvalidData.csv"), StandardCharsets.UTF_8)) {

            return lines
                    .filter(l -> !l.isBlank())
                    .skip(1)
                    .map(l -> l.split(",",-1))
                    .map(split -> new Object[]{new User()
                            .setName(split[0])
                            .setLastName(split[1])
                            .setEmail(split[2])
                            .setPassword(split[3])})
                    .toList()
                    .iterator();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
