package de.demoshop.core;

import de.demoshop.fw.HomePageHelper;
import de.demoshop.fw.ProductHelper;
import de.demoshop.fw.UserHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager{
    WebDriver driver;
    WebDriverWait wait;
    ProductHelper product;
    UserHelper user;
    HomePageHelper homePage;

    public void init() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();

        product = new ProductHelper(driver,wait);
        user = new UserHelper(driver,wait);
        homePage = new HomePageHelper(driver,wait);
    }

    public ProductHelper getProduct() {
        return product;
    }

    public UserHelper getUser() {
        return user;
    }

    public HomePageHelper getHomePage() {
        return homePage;
    }

    public void stop() {
        if(driver != null) driver.quit();
    }

}
