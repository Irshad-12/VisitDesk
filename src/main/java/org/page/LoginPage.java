package org.page;
//
import org.example.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoginPage extends BaseTest {

    @FindBy(xpath = "//h4[text()='Enter your business code']")
    public WebElement businessText;

    @FindBy(xpath= "//div[contains(@ng-if,'firstScreen')]")
    public WebElement businessCodeFrame;
    @FindBy(xpath= "//h4[contains(@class,'text')]/parent::div//following::div/div//div[@class='form-group']/input")
    public WebElement businessCode;

    @FindBy(xpath = "//button[contains(@class,'m-0 btn btn-primary btn-block')]")
    public WebElement businessContinue;

    @FindBy(xpath = "//input[@ng-model='user.username']")
    public WebElement userName;

    @FindBy(xpath = "//input[@ng-model='user.password']")
    public WebElement userPassword;

    @FindBy(xpath="//span[text()='LOGIN']")
    public WebElement loginBtn;

    @FindBy(xpath = "//h4[text()='Welcome to irshad123']")
    public WebElement welcomeText;

    @FindBy(xpath = "//li[contains(@class,'login menu')]")
    public WebElement login;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean ValidateBusinessCode() throws InterruptedException
    {

//        login.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(businessText));
        String ele = businessText.getText();
        System.out.println(ele);
        if (ele.equalsIgnoreCase("ENTER YOUR BUSINESS CODE")) {
            String element="irshad123";
            for(char c:element.toCharArray()){
                businessCode.sendKeys(String.valueOf(c));
            }
            Actions actions=new Actions(driver);
            actions.moveToElement(businessContinue).doubleClick().perform();
            String s=driver.getCurrentUrl();
            System.out.println(s);
            String s1=validateCredentials();
            System.out.println("s1 = " + s1);
            return true;
        }
        return false;
    }
    public String validateCredentials(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Welcome to irshad123']")));
        String welcome=welcomeText.getText();
        System.out.println(welcome);
        if(welcome.equalsIgnoreCase("Welcome to irshad123")) {
            WebDriverWait username = new WebDriverWait(driver,Duration.ofSeconds(10));
            username.until(ExpectedConditions.visibilityOf(userName));
            userName.sendKeys("irshad123");
            userPassword.sendKeys("1245irS#");
            loginBtn.click();
            return welcome;
        }
        return "no data";
    }
}
