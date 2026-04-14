package de.demoshop.core;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setup() {
        app.init();
    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        app.stop();
    }


}
//jack@sparrow.com
//Password1!