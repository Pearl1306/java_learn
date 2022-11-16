package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAgroup()){
            app.getGroupHelper().creatGroup(new GroupData(null, null, "test1"));
        }
        app.getNavigationHelper().goToHomePage();
        int before =app.getContactHelper().getContactCount();


        app.getContactHelper().initContactCreation();
        app.getContactHelper().createContact(new ContactData
                ("John","Doe","333 Spring St","1112223344",
                        "qwerty@gmail.com"));

        int after =app.getContactHelper().getContactCount();
        Assert.assertEquals(before,after-1);

        app.logout();
    }


}
