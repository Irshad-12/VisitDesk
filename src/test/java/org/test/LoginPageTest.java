package org.test;

import org.example.BaseTest;
import org.page.Visitors;
import org.testng.annotations.*;

import java.io.IOException;

public class LoginPageTest extends BaseTest {

    Visitors visitors;
    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
//        beforeInitializing();
        initialization();
        visitors =new Visitors();
    }


    @Test
    public void validateLogin() throws InterruptedException {
        visitors.verifyInvestors();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
