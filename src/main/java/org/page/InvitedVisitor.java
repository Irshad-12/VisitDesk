package org.page;

import org.example.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class InvitedVisitor extends BaseTest {
    RecurringVisitor recurringVisitor;
    NewVisitor newVisitor;
    public InvitedVisitor() {

        PageFactory.initElements(driver, this);
        recurringVisitor=new RecurringVisitor();
        newVisitor=new NewVisitor();
    }
    @FindBy(linkText = "Invited Visitor")
    public WebElement invitedVisitor;
    @FindBy(xpath = "//option[contains(@ng-repeat,'branch in branches')]")
    public WebElement branchValue;

    @FindBy(xpath = "(//select[contains(@class,'ng-invalid-required')])")
    public WebElement BranchValuesdropDwn;

    @FindBy(xpath = "(//input[contains(@ng-model,'visitorInv.inviteId')])")
    public  WebElement inviteID;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement invalidError;
    @FindBy(xpath = "//button[contains(text(),'GET DETAILS')]")
    public WebElement detailstBtn;
    @FindBy(xpath = "(//button[contains(@class,'btn btn-close')])[last()]")
    public WebElement cancelBtn;
    @FindBy(xpath = "//button[contains(@class,'btn-primary waves-effect') and contains(text(),'CHECKIN')]")
    public WebElement checkinBtn;

    public void verifyInvitedVisitor(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ViewInvites viewInvites=new ViewInvites();
        String token=viewInvites.verifyInvitesDetails();
        checkinBtn.click();
        invitedVisitor.click();
        BranchValuesdropDwn.click();
        branchValue.click();
        inviteID.sendKeys(token);
        GetDetailsButton(detailstBtn);
    }
    public void GetDetailsButton(WebElement element) {
        if (element.isDisplayed()) {
            element.click();
        }
    }

}
