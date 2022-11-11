package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() throws Exception {

        app.selectContact();
        app.deleteContact();
        app.acceptDeletionContact();

    }


}
