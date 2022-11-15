package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.learn.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("John", "Doe",
                "3 Duncroft ,Silver Spring MD", "2223334556",
                "qwerty1@gmail.com","test1"),true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        app.logout();
    }


}
