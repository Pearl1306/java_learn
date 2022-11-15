package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.learn.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {

        app.getContactHelper().initContactCreation();
        app.getContactHelper().createContact(new ContactData
                ("John","Doe","333 Spring St","1112223344",
                        "qwerty@gmail.com","test1"),true);

        app.logout();
    }


}
