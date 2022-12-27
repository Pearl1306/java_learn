package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;
import ru.stqa.learn.addressbook.model.GroupData;
import ru.stqa.learn.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveGroupTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() ==0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        Contacts contacts = app.contact().all();
        if(app.contact().all().size()==0 ){
            app.contact().createContact(new ContactData().withLastname("Lo").withFirstname("Sam")
                    .withAddress("123 St").withHomephone("234").withMobilePhone("333")
                    .withMobilePhone("444").withEmail("swe@"));
        }
        for(ContactData contact : contacts){
            if(contact.getGroups().size() == 0){
                break;
            }
            app.contact().createContact(new ContactData().withLastname("Lo").withFirstname("Sam")
                    .withAddress("123 St").withHomephone("234").withMobilePhone("333")
                    .withMobilePhone("444").withEmail("swe@").inGroup(app.group().all().iterator().next()));
        }
    }
    @Test
    public void testContactRemoveGroup(){
        Contacts contacts = app.db().contacts();
        ContactData selectedContact = app.contact().contactInGroup(contacts);
        ContactData before = selectedContact;
        app.contact().selectContactById(app.contact().contactInGroup(contacts).getId());
        app.contact().selectGroup(selectedGroup().getName());
        app.contact().removeContactFromGroup();
        ContactData after = selectedContact.inGroup(selectedGroup());
        assertThat(after, equalTo(before));
    }

    public GroupData selectedGroup(){
        Contacts contacts = app.db().contacts();
        return app.contact().contactInGroup(contacts).getGroups().iterator().next();
    }


}
