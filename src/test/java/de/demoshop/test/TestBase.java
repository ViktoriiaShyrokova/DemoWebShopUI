package de.demoshop.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase extends ApplicationManager {

    @BeforeMethod
    public void setup() {
        init();
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        stop();
    }


}
//jack@sparrow.com
//Password1!