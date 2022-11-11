package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.learn.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {

        app.initContactCreation();
        app.fillContactForm(new ContactData("John", "Doe", "3 Duncroft ,Silver Spring MD", "2223334556", "qwerty1@gmail.com"));
        app.submitContactCreation();
        app.returnToHomePage();
        app.logout();
    }


}
