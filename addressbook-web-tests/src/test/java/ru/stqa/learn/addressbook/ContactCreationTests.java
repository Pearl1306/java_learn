package ru.stqa.learn.addressbook;

import org.testng.annotations.*;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {

        initContactCreation();
        fillContactForm(new ContactData("John", "Doe", "3 Duncroft ,Silver Spring MD", "2223334556", "qwerty1@gmail.com"));
        submitContactCreation();
        returnToHomePage();
        logout();
    }


}
