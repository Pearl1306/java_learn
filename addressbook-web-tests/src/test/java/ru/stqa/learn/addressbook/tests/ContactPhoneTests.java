package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
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
                    .withAddress("333 Spring St").withHomephone("111").withMobilePhone("222").withWorkphone("333")
                    .withEmail("qwerty@gmail.com"),true);
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);
        assertThat(contact.getHomephone(), equalTo(contactInfoFromEditForm.getHomephone()));
        assertThat(contact.getMobilephone(), equalTo(contactInfoFromEditForm.getMobilephone()));
        assertThat(contact.getWorkphone(), equalTo(contactInfoFromEditForm.getWorkphone()));


    }
}
