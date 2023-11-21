package org.test;

import org.example.BaseTest;
import org.page.InvitedVisitor;
import org.page.ViewInvites;
import org.page.Visitors;
import org.page.NewVisitor;
import org.testng.annotations.Test;

import java.text.ParseException;

public class NewVisitorPageTest extends BaseTest {
    NewVisitor newVisitor;
    Visitors inviteVisitor;
    ViewInvites viewInvites;

    @Test
    public void validateNewVisitor() throws InterruptedException, ParseException {
        newVisitor=new NewVisitor();
        inviteVisitor=new Visitors();
        viewInvites=new ViewInvites();
        inviteVisitor.verifyInvestors();
        newVisitor.checkinBtn.click();
        Thread.sleep(3000);
        String ActFN=newVisitor.verifyNewVisitor();
        System.out.println(ActFN);
        String ExpFN= viewInvites.token.getText();
        System.out.println(ExpFN);
        if(ActFN.equalsIgnoreCase(ExpFN)){
            System.out.println("Successfully Validated");
        }else{
            System.out.println("Not Successfully Validated");
        }
        Thread.sleep(3000);
    }

}
