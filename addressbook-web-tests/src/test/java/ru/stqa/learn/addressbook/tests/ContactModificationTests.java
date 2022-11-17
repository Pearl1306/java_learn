package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.List;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();

        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().fillContactForm(new ContactData("John", "Doe",
                    "3 Duncroft ,Silver Spring MD", "2223334556",
                    "qwerty1@gmail.com"),true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData
                ("John","Doe","333 Spring St","1112223344",
                        "qwerty@gmail.com"),false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(),after.size());


    }
}
