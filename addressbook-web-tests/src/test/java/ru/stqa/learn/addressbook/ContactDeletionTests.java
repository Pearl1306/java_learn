package ru.stqa.learn.addressbook;

import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() throws Exception {

        selectContact();
        deleteContact();
        acceptDelitionContact();

    }


}
