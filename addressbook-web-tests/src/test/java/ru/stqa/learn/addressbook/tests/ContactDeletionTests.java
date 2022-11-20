package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.List;


public class ContactDeletionTests extends TestBase {
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
    public void testContactDeletion() throws Exception {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size() - 1, after.size());
        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
