package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Set;

public class ContactModificationTests extends TestBase{
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
                            .withAddress("333 Spring St").withHomephone("1112223344")
                            .withEmail("qwerty@gmail.com"),true);
        }
    }

    @Test
    public void testContactModification(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("John").withLastname("Doe")
                .withAddress("333 Spring St").withHomephone("1112223344").withEmail("qwerty@gmail.com");
        app.contact().modifyContact(modifiedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before.size(),after.size());
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);


    }

}
