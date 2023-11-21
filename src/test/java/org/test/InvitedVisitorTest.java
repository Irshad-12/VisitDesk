package org.test;

import org.example.BaseTest;
import org.page.NewVisitor;
import org.page.ViewInvites;
import org.page.Visitors;
import org.page.InvitedVisitor;
import org.testng.annotations.Test;

import java.text.ParseException;

public class InvitedVisitorTest extends BaseTest {


    NewVisitor newVisitor;
    Visitors inviteVisitor;
    ViewInvites viewInvites;
    InvitedVisitor invitedVisitor;

    @Test
    public void validateInvitedVisitor() throws InterruptedException, ParseException {
        inviteVisitor=new Visitors();
        newVisitor=new NewVisitor();
        viewInvites=new ViewInvites();
        invitedVisitor=new InvitedVisitor();
        inviteVisitor.verifyInvestors();
        viewInvites.verifyInvitesDetails();
        invitedVisitor.verifyInvitedVisitor();
        newVisitor.verifyNewVisitorsDetails();
        Thread.sleep(3000);
//        invitedVisitor=new InvitedVisitor();;
//        inviteVisitor=new Visitors();;
//        inviteVisitor.verifyInvestors();
//        invitedVisitor.verifyInvitedVisitor();
    }


}
