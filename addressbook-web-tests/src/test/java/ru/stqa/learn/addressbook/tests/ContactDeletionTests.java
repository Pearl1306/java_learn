package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Set;


public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() ==0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        if(app.contact().all().size()==0){
            app.contact().initContactCreation();
            app.contact().fillContactForm(new ContactData().withFirstname("John").withLastname("Doe")
                    .withAddress("333 Spring St").withHomephone("1112223344").withEmail("qwerty@gmail.com")
                   ,true);
        }
    }


    @Test
    public void testContactDeletion() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before.size() - 1, after.size());
        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

}
