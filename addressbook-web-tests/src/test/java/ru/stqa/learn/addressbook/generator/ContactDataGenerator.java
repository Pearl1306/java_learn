package ru.stqa.learn.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.learn.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);

    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContact(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname()
          , contact.getAddress(), contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone()
          , contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
    }
    writer.close();
  }

  private List<ContactData> generateContact(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("Sui %s", i)).withLastname(String.format("Doe %s", i))
          .withAddress(String.format("road %s", i)).withHomephone(String.format("123 %s", i))
          .withMobilePhone(String.format("345 %s", i)).withWorkphone(String.format("678 %s", i))
          .withEmail(String.format("1@ %s", i)).withEmail2(String.format("2@ %s", i)).withEmail3(String.format("3@ %s", i)));
    }
    return contacts;
  }
}
