package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;
import ru.stqa.learn.addressbook.model.GroupData;
import ru.stqa.learn.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddGroupTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() ==0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        Contacts contacts = app.contact().all();
        if(app.contact().all().size()==0 || contactNotInAllGroup()==null){
            app.contact().createContact(new ContactData().withLastname("Lo").withFirstname("Sam")
                    .withAddress("123 St").withHomephone("234").withMobilePhone("333")
                    .withMobilePhone("444").withEmail("swe@"));
        }
    }
    @Test
    public void testContactAddGroup(){
        Contacts before = app.db().contacts();
        ContactData selectedContact = contactNotInAllGroup();
        app.contact().selectContactById(contactNotInAllGroup().getId());
        app.contact().addContactToGroup(groupNotInContact().iterator().next().getName());
        ContactData contactWithAddedGroup = selectedContact.inGroup(groupNotInContact().iterator().next());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(selectedContact).withAdded(contactWithAddedGroup )));

    }
    public ContactData contactNotInAllGroup(){
        Contacts contacts = app.contact().all();
        for(ContactData contact : contacts){
            if(contact.getGroups().size()==0 || contact.getGroups().size() < app.group().all().size() ){
                return contact;
            }
        }
        return null;
    }
    public Groups groupNotInContact(){
        Groups groupsInContact  = contactNotInAllGroup().getGroups();
        Groups groups = app.group().all();
        groups.removeAll(groupsInContact);
        return groups;
    }

}
