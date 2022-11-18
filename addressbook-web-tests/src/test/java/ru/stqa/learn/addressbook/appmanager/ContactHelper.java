package ru.stqa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("email"), contactData.getEmail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }


    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void acceptDeletionContact() {
        alertAccept();
    }


    public void deleteContact() {

        click(By.xpath("//input[@value='Delete']"));
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void selectContact(int index) {
        wd.findElements(By.xpath("//td/input")).get(index).click();

    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));

    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//td/input"));

    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToHomePage();
    }

    public int getContactCount() {
        return wd.findElements(By.xpath("//td/input")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String last = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allPhone = cells.get(5).getText();
            String allEmail = cells.get(4).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, name, last, address, allPhone, allEmail);
            contacts.add(contact);
        }
        return contacts;

    }
}
