package ru.stqa.learn.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;
import ru.stqa.learn.addressbook.model.GroupData;
import ru.stqa.learn.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader
          (new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      xstream.allowTypes(new Class[]{ContactData.class});
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader
          (new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());//==List<ContactData>.class
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.db().groups().size()==0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().initContactCreation();
    app.contact().createContact(contact);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(app.contact().count()));

    assertThat(after, equalTo(before
          .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }
  @Test
  public void testContactCreationWithPhoto() throws Exception {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/dog.png");
    ContactData newContact = new ContactData().withFirstname("John").withLastname("Doe")
          .withAddress("333 Spring St").withHomephone("111").withMobilePhone("222")
          .withWorkphone("333").withEmail("qwerty@gmail.com").withPhoto(photo).inGroup(groups.iterator().next());
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().initContactCreation();
    app.contact().createContact(newContact);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(app.contact().count()));

    assertThat(after, equalTo(before
          .withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();

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
