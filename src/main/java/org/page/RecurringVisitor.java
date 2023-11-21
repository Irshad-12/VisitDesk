package org.page;

import dev.failsafe.internal.util.Assert;
import org.example.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecurringVisitor extends BaseTest {
    NewVisitor newVisitor;
    public RecurringVisitor() {
        PageFactory.initElements(driver, this);
        newVisitor=new NewVisitor();
    }

    @FindBy(linkText = "Recurring Visitor")
    public WebElement recurringPage;

    @FindBy(xpath = "(//div[contains(@class,'toast toast-error')])")
    public WebElement toastError;

    @FindBy(xpath = "(//button[contains(@ng-disabled,'visitorRecForm')])")
    public WebElement requestBtn;
    @FindBy(xpath = "(//button[contains(@class,'btn btn-close')])[last()]")
    public WebElement cancelBtn;

    @FindBy(xpath = "//option[contains(@ng-repeat,'branch in branches')]")
    public WebElement branchValue;


    @FindBy(xpath = "(//select[contains(@class,'ng-invalid-required')])")
    public WebElement BranchValuesdropDwn;
    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;
    @FindBy(xpath = "//input[contains(@name,'mobile')]")
    public WebElement mobileNumber;

    @FindBy(xpath = "//h4[text()='Recurring Visitor verification']")
    public WebElement verifyOTP;

    public void verifyRecurringVisitor() throws InterruptedException {
        newVisitor.checkinBtn.click();
        recurringPage.click();
        BranchValuesdropDwn.click();
        branchValue.click();
        newVisitor.fillOutForm(email,"irshadahamed124@gmail.com");
        newVisitor.fillOutForm(mobileNumber,"9884744069") ;
        requestButton(requestBtn);
        String s=verifyOTP.getText();
        if(s.equalsIgnoreCase("Recurring Visitor verification")){
            Assert.isTrue(true,"verified OTP validation");
        }
        Thread.sleep(3000);

    }
    public void requestButton(WebElement element){
        if(element.isDisplayed()) {
            element.click();
        }
    }

    public boolean cancelBtn(WebElement element){
        if(element.isDisplayed()){
            element.click();
            WebElement fullName = newVisitor.fullName;
            fullName.isDisplayed();
            return true;
        }
        return false;
    }

}
