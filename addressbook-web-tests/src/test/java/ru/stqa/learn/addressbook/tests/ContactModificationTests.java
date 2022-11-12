package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
