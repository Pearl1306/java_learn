package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;
import ru.stqa.learn.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePrecondition() {
        if(app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        if(app.db().contacts().size()==0) {
            app.contact().createContact(new ContactData().withLastname("Lo").withFirstname("Sam")
                    .withAddress("123 St").withHomephone("234").withMobilePhone("333")
                    .withMobilePhone("444").withEmail("swe@"));
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("John").withLastname("Doe").withAddress("333 Spring St").withHomephone("111")
              .withMobilePhone("222").withWorkphone("305").withEmail("qwerty@gmail.com").withEmail2("1@")
              .withEmail3("doe@");
        app.contact().modify(contact);
        assertThat(before.size(),equalTo(app.contact().count()));
        Contacts after = app.db().contacts();
        assertThat(after,equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

}
