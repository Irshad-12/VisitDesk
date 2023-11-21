package org.test;

import org.example.BaseTest;
import org.page.Visitors;
import org.page.RecurringVisitor;
import org.testng.annotations.Test;


public class RecurringPageTest extends BaseTest {
    RecurringVisitor recurringVisitor;
    Visitors inviteVisitor;

    @Test
    public void validateRecurringVisitor() throws InterruptedException {
        inviteVisitor=new Visitors();
        recurringVisitor=new RecurringVisitor();
        inviteVisitor.verifyInvestors();
        recurringVisitor.verifyRecurringVisitor();
    }
}
