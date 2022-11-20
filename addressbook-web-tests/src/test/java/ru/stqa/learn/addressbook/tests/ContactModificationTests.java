package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() ==0) {
            app.group().create(new GroupData(null, null, "test1"));
        }
        app.goTo().homePage();
        if(app.contact().list().size()==0){
            app.contact().initContactCreation();
            app.contact().fillContactForm(new ContactData("John", "Doe",
                    "3 Duncroft ,Silver Spring MD", "2223334556",
                    "qwerty1@gmail.com"),true);
        }
    }

    @Test
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData
                (before.get(before.size()-1).getId(),"John","Doe","333 Spring St","1112223344",
                        "qwerty@gmail.com");
        app.contact().modifyContact(before, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size(),after.size());
        before.remove(before.size()-1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1,g2)->Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);


    }

}
