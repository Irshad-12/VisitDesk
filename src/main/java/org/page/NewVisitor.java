package org.page;

import com.google.j2objc.annotations.Weak;
import org.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Factory;

import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class NewVisitor extends BaseTest {
    public NewVisitor() {


        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//span//div[contains(@ng-bind-html,'host.firstName')]")
    public List<WebElement> hostValues;
    @FindBy(xpath = "//div[@role='option']")
    public List<WebElement> purposeValues;

    @FindBy(xpath = "//option[contains(@ng-repeat,'branch in branches')]")
    public WebElement branchValue;

    @FindBy(xpath = "(//select[contains(@class,'ng-valid-parse')])[last()-2]")
    public WebElement BranchValuesdropDwn;

    @FindBy(xpath = "(//select[contains(@class,'ng-valid-parse')])[last()-1]")
    public WebElement DeskValuesdropDwn;

    @FindBy(xpath = "//input[contains(@name,'mobile')]")
    public WebElement mobileNumber;

    @FindBy(xpath = "//input[@name='fullName']")
    public WebElement fullName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//div[contains(@ng-model,'visitor.hostId')]//span[contains(@class, 'select-toggle')]")
    public WebElement host;

    @FindBy(xpath = "(//div[@class='ui-select-match ng-scope'])[last()]")
    public WebElement purpose;

    @FindBy(xpath = "//input[@ng-model='visitor.organization']")
    public WebElement organisation;
    @FindBy(xpath = "//input[@ng-model='visitor.designation']")
    public WebElement designation;

    @FindBy(xpath = "//input[contains(@type,'radio')]")
    public List<WebElement> vehicle;

    @FindBy(xpath = "//input[contains(@ng-model,'visitor.vehicle') and @type='text']")
    public List<WebElement> vehicleText;

    @FindBy(xpath = "//input[@ng-model='visitor.externalId']")
    public WebElement externalId;

    @FindBy(xpath = "//textarea[@ng-model='visitor.remarks']")
    public WebElement privateNotes;

    @FindBy(xpath = "//button[contains(@ng-click,'checkin.openCalendar')]")
    public WebElement checkDate;

    @FindBy(xpath = "//i[contains(@class,'chevron-left')]")
    public WebElement dateLeftMoveBtn;

    @FindBy(xpath = "//td[contains(@id,'datepicker')]")
    public List<WebElement> date;

    @FindBy(xpath = "//input[contains(@ng-model,'hours')]")
    public WebElement hourInDate;

    @FindBy(xpath = "//span[contains(@class,'chevron-down')]")
    public WebElement hourChevronDown;

    @FindBy(xpath = "//button[contains(@ng-class,'getClass')]")
    public List<WebElement> hourFields;

    @FindBy(xpath = "//label[@class='switch']")
    public WebElement toggleBtn;

    @FindBy(xpath = "//div[contains(@id,'fieldsToggle')]")
    public WebElement hideToggle;

    @FindBy(xpath = "(//button[contains(text(),'CREATE')] )[last()]")
    public WebElement createBtn;
    @FindBy(xpath = "//button[contains(@class,'btn-primary waves-effect') and contains(text(),'CHECKIN')]")
    public WebElement checkinBtn;

    @FindBy(xpath = "//a[@id='visitors']")
    public WebElement visitorTab;

    public void verifyNewVisitorsDetails() throws ParseException {
        ViewInvites viewInvites=new ViewInvites();
        List<String> tokenDetailsList=viewInvites.visitorDetails();
        String Name=fullName.getText();
        for(int i=0;i<tokenDetailsList.size();i++){
            if(tokenDetailsList.get(i).equalsIgnoreCase(Name)){
                System.out.println("valid visitor name");
            }
        }
        hideToggle.click();
        String originalDateStr = "17-11-2023";
        SimpleDateFormat originalDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date originalDate = originalDateFormat.parse(originalDateStr);
        SimpleDateFormat newDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String newDateStr = newDateFormat.format(originalDate);
        System.out.println("Original Date: " + originalDateStr);
        System.out.println("Formatted Date: " + newDateStr);
        for(int i=0;i<tokenDetailsList.size();i++){
            if(tokenDetailsList.get(i).contains(newDateStr)){
                System.out.println("valid visitor date");
            }
        }

    }
    public void fillOutForm(WebElement element, String value) {
        element.sendKeys(value);
        String s=element.getAttribute("value");
        if (!(s.isEmpty())) {
            System.out.println("Entered text: " + s);
        }else{
            System.out.println("Not entered text");
        }
    }

    public void calendar(WebElement checkDate,WebElement lftBtn,List<WebElement> date,WebElement hourChevronDown,List<WebElement> hourField) throws InterruptedException {
        checkDate.click();
        lftBtn.click();
        int minDay = 1; // Minimum day
        int maxDay = 31; // Maximum day
        int randomDay = minDay + new Random().nextInt(maxDay - minDay + 1);
        date.get(randomDay - 1).click();
        Thread.sleep(2000);
        hourChevronDown.click();
        List<String> hourFieldValues= Arrays.asList("Now","Clear","Date","Close");
        for(int i=0;i<hourField.size();i++){
              if(hourField.get(i).getAttribute("ng-class").equalsIgnoreCase(hourFieldValues.get(i))){
                  System.out.println(hourFieldValues.get(i)+" Value is there");
              }
        }
        for (WebElement element : hourField) {
            if (element.getAttribute("ng-class").equalsIgnoreCase("Close")) {
                element.click();
                System.out.println("Check date verified successfully");
            }
        }
    }

    public void toggle(WebElement idVerification) {
        idVerification.click();
        boolean otp=driver.findElement(By.xpath("//label//strong[contains(text(),'Verify via OTP (Email/SMS)')]")).isDisplayed();
        if(otp){
            System.out.println("Toggle enabled and OTP column displayed succesfully");
        }else{
            System.out.println("Toggle not enabled and OTP column not displayed succesfully");
        }
    }

       public void privateNotes(WebElement element,String text){
           char[] characters=text.toCharArray();
           if(characters.length>200) {
               String textToFill = new String(characters);
               fillOutForm(element, textToFill);
           }else{
               System.out.println("Enter 200 characters");
           }
       }
       public void branchAndDesk(WebElement element,String value){
           Select select=new Select(element);
           select.selectByVisibleText(value);
       }

        public String verifyNewVisitor() throws InterruptedException {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            dropDown(purpose,purposeValues);
            branchAndDesk(BranchValuesdropDwn,"Branch A");
            branchAndDesk(DeskValuesdropDwn,"Reception");
            fillOutForm(mobileNumber,"9884744069");
            fillOutForm(fullName,"Sameera Fairoze");
            fillOutForm(email,"irshadahamed124@gmail.com");
            hideToggle.click();
            fillOutForm(organisation,"ifelse");
            fillOutForm(designation,"EEE");
            fillOutForm(externalId,"1234");
            privateNotes(privateNotes,"Selenium is a powerful automation tool for web testing. It enables the control of browsers, automating repetitive tasks, and validating web applications. With cross-browser compatibility, Selenium simplifies testing, ensuring robust and efficient web development processes.");
            verifyVehicle(vehicle,vehicleText,"bike");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(toggleBtn));
            toggle(clickableElement);
//            calendar(checkDate,dateLeftMoveBtn,date,hourChevronDown,hourFields);
            createBtn.click();
            String FN=returnFullName();
            Thread.sleep(2000);
            visitorTab.click();
            return FN;
        }
        public String returnFullName(){
            String s=fullName.getAttribute("value");
            System.out.println("fn"+s);
            return s;
        }
        public void dropDown(WebElement element1,List<WebElement> element2){
             element1.click();
             element2.get(2).click();
        }

    public void dropAndClick(WebElement element1){
        if(element1.isDisplayed()){
            element1.sendKeys("irshad");
        }else{
            System.out.println("empty");
        }
    }
    public void verifyVehicle(List<WebElement> vehicle,List<WebElement> vehicleText,String value){
        vehicle.get(2).click();
        vehicleText.get(2).sendKeys(value);
    }
}
