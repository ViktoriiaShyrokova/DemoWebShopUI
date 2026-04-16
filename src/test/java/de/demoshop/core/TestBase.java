package de.demoshop.core;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));
    public static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setup() {
        app.init();
    }

    @AfterSuite(enabled = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void startTest(Method method){
        logger.info("Start test_______{}", method.getName());
    }

    @AfterMethod
    public void stopTest(ITestResult result){
        if(result.isSuccess()) logger.info("PASSED");
        else logger.error("FAILED, Screenshot -> {}", app.getUser().takeScreenshot());
        logger.info("=".repeat(50));
    }

}
//jack@sparrow.com
//Password1!