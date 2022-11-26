package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFeatureTests extends TestBase {
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
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }
    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomephone(),contact.getMobilephone(),contact.getWorkphone())
                .stream().filter((s)->!s.equals("")).map(ContactFeatureTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
    public static String cleaned(String phone){
        return phone.replaceAll("//s","")
                .replaceAll("[-()]","");
    }

    @Test
    public void testcontactEmails(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }
    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s)->!s.equals("")).collect(Collectors.joining("\n"));
    }
    @Test
    public void testContactAddress(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

    }
}
