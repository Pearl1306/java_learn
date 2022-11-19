package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();

        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().initContactCreation();
            app.getContactHelper().fillContactForm(new ContactData("John", "Doe",
                    "3 Duncroft ,Silver Spring MD", "2223334556",
                    "qwerty1@gmail.com"),true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size()-1);
        ContactData contact = new ContactData
                (before.get(before.size()-1).getId(),"John","Doe","333 Spring St","1112223344",
                        "qwerty@gmail.com");
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(),after.size());
        before.remove(before.size()-1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1,g2)->Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);


    }
}
