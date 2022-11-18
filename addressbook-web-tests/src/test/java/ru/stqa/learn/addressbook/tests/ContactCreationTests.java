package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAgroup()) {
            app.getGroupHelper().creatGroup(new GroupData(null, null, "test1"));
        }
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData
                ("John", "Doe", "333 Spring St", "1112223344",
                        "qwerty@gmail.com");
        app.getContactHelper().initContactCreation();
        app.getContactHelper().createContact(contact);

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size() - 1);
        app.logout();
        contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) ->
                Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }


}
