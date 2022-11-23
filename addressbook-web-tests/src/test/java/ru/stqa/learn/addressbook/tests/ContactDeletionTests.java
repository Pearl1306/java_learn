package ru.stqa.learn.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() ==0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        if(app.contact().all().size()==0){
            app.contact().createContact(new ContactData().withLastname("Lo").withFirstname("Sam")
                        .withAddress("123 St").withHomephone("234").withMobilePhone("333")
                        .withMobilePhone("444").withEmail("swe@"));
        }
    }
    
    @Test
    public void testContactDeletion() throws Exception {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(before.size() - 1,equalTo(app.contact().count()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
