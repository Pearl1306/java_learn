package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        int before =app.getContactHelper().getContactCount();

        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().fillContactForm(new ContactData("John", "Doe",
                    "3 Duncroft ,Silver Spring MD", "2223334556",
                    "qwerty1@gmail.com","test1"),true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData
                ("John","Doe","333 Spring St","1112223344",
                        "qwerty@gmail.com",null),false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        int after =app.getContactHelper().getContactCount();
        Assert.assertEquals(before,after);


    }
}
