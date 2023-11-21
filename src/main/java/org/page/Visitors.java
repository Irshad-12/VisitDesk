package org.page;

import org.example.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Visitors extends BaseTest {
    LoginPage loginPage;

    @FindBy(xpath = "//a[@id='invite-visitors']")
    public WebElement inviteVisitorElement;

    @FindBy(xpath = "//a[contains(@id,'visitor-tab')]")
    public WebElement investorsTab;


    public Visitors() {
        PageFactory.initElements(driver, this);
    }

    public void verifyInvestors() throws InterruptedException {
        loginPage = new LoginPage();
        boolean b=loginPage.ValidateBusinessCode();
        if(b){
            System.out.println("successfull");
        }else{
            System.out.println("unsuccessfull");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(investorsTab));
        investorsTab.click();
        Thread.sleep(2000);
    }
}