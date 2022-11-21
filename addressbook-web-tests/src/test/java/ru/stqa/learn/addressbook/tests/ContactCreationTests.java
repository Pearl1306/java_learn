package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;
import ru.stqa.learn.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
           public void ensurePrecondition(){
        app.goTo().groupPage();
        if (app.group().all().size() ==0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactCreation() throws Exception {

        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("John").withLastname("Doe")
                .withAddress("333 Spring St").withHomephone("1112223344").withEmail("qwerty@gmail.com");
        app.contact().initContactCreation();
        app.contact().createContact(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(),equalTo( before.size() + 1));

        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

    }


}
