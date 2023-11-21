package org.page;

import org.example.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ViewInvites extends BaseTest {
    public ViewInvites() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "view-invites")
    public WebElement viewInvitesPage;

    @FindBy(xpath ="//a[@class='ng-binding']")
    public WebElement token;

    @FindBy(xpath = "//button[text()='CHECKIN']")
    public WebElement checkinBtn;

    @FindBy(xpath = "//td[@class='ng-binding']")
    public List<WebElement> viewInvitesDetails;


    public String verifyInvitesDetails(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        viewInvitesPage.click();
        String Token=token.getText();
        System.out.println("Token = " + Token);
        return Token;
    }
    public List<String> visitorDetails() {
        List<String> tokenDetailsList = new ArrayList<>();
        for (int i = 0; i < viewInvitesDetails.size(); i++) {
            tokenDetailsList.add(viewInvitesDetails.get(i).getText());
        }
        return tokenDetailsList;
    }
}
