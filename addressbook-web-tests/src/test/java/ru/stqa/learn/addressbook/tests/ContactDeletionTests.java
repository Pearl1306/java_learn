package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() throws Exception {

        app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().fillContactForm(new ContactData("John", "Doe",
                    "3 Duncroft ,Silver Spring MD", "2223334556",
                    "qwerty1@gmail.com"),true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptDeletionContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(),after.size()+1);


    }


}
