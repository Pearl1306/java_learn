package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAgroup()){
            app.getGroupHelper().creatGroup(new GroupData("test2", null, null));
        }

        app.getContactHelper().initContactCreation();
        app.getContactHelper().createContact(new ContactData
                ("John","Doe","333 Spring St","1112223344",
                        "qwerty@gmail.com","test2"));

        app.logout();
    }


}
