package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() throws Exception {

        app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().fillContactForm(new ContactData("John", "Doe",
                    "3 Duncroft ,Silver Spring MD", "2223334556",
                    "qwerty1@gmail.com","test1"),true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptDeletionContact();

    }


}
