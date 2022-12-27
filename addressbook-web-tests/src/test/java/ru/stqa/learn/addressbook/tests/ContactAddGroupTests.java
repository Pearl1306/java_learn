package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;
import ru.stqa.learn.addressbook.model.GroupData;
import ru.stqa.learn.addressbook.model.Groups;

import java.util.Set;

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
        Contacts contacts = app.db().contacts();
        if(app.contact().all().size()==0 || contactNotInAllGroup(contacts)==null){
            app.contact().createContact(new ContactData().withLastname("Lo").withFirstname("Sam")
                    .withAddress("123 St").withHomephone("234").withMobilePhone("333")
                    .withMobilePhone("444").withEmail("swe@"));
        }
    }
    @Test
    public void testContactAddGroup(){
        Contacts contacts = app.db().contacts();
        ContactData selectedContact = contactNotInAllGroup(contacts);
        ContactData before = selectedContact;
        app.contact().selectContactById(selectedContact.getId());
        app.contact().addContactToGroup(groupNotInContact().getName());
        GroupData group = groupNotInContact();
        ContactData after = selectedContact.inGroup(groupNotInContact());
        assertThat(after, equalTo(before));

    }
    public ContactData contactNotInAllGroup(Contacts contacts){
        for(ContactData contact : contacts){
            Set<GroupData> contactInGroup = contact.getGroups();
           if(contactInGroup.size() < app.group().all().size() ){
                return contact;
            }
        }
        return null;
    }
    public GroupData groupNotInContact(){
        Contacts contacts = app.db().contacts();
        Groups groupsInContact  = contactNotInAllGroup(contacts).getGroups();
        Groups groups = app.db().groups();
        groups.removeAll(groupsInContact);
        GroupData group = groups.iterator().next();
        return group;
    }

}
