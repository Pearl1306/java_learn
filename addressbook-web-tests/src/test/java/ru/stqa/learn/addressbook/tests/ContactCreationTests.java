package ru.stqa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;
import ru.stqa.learn.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader
          (new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1])
            .withAddress(split[2]).withHomephone(split[3]).withMobilePhone(split[4]).withWorkphone(split[5])
            .withEmail(split[6]).withEmail2(split[7]).withEmail3(split[8])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {

    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/dog.png");
       /*ContactData contact = new ContactData().withFirstname("John").withLastname("Doe")
                .withAddress("333 Spring St").withHomephone("111").withMobilePhone("222")
                .withWorkphone("333").withEmail("qwerty@gmail.com");//.withPhoto(photo);*/
    app.contact().initContactCreation();
    app.contact().createContact(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(app.contact().count()));

    assertThat(after, equalTo(before
          .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/dog.png");

    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }


}
