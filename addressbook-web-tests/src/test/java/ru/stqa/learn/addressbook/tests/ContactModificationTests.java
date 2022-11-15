package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData
                ("John","Doe","333 Spring St","1112223344",
                        "qwerty@gmail.com",null),false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
