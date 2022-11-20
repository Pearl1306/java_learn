package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
           public void ensurePrecondition(){
        app.goTo().groupPage();
        if (app.group().list().size() ==0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactCreation() throws Exception {

        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData
                ("John", "Doe", "333 Spring St", "1112223344",
                        "qwerty@gmail.com");
        app.contact().initContactCreation();
        app.contact().createContact(contact);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size(), after.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1,g2)-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);

    }


}
