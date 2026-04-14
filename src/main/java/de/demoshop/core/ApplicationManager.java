package de.demoshop.core;

import de.demoshop.fw.HomePageHelper;
import de.demoshop.fw.ProductHelper;
import de.demoshop.fw.UserHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager{
    WebDriver driver;
    ProductHelper product;
    UserHelper user;
    HomePageHelper homePage;

    public void init() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        product = new ProductHelper(driver);
        user = new UserHelper(driver);
        homePage = new HomePageHelper(driver);
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
