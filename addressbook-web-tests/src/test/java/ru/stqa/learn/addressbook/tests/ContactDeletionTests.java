package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() throws Exception {

        app.getNavigationHelper().goToHomePage();
        int before =app.getContactHelper().getContactCount();

        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().fillContactForm(new ContactData("John", "Doe",
                    "3 Duncroft ,Silver Spring MD", "2223334556",
                    "qwerty1@gmail.com"),true);
        }
        app.getContactHelper().selectContact(before-1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptDeletionContact();
        int after =app.getContactHelper().getContactCount();
        Assert.assertEquals(before,after+1);


    }


}
