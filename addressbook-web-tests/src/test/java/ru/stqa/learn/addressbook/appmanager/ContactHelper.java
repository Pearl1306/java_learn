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

    public void fillContactForm(ContactData contactData,boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("email"), contactData.getEmail());
        if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);

        }else{
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
        fillContactForm(contact,true);
        submitContactCreation();
        returnToHomePage();
    }

    public int getContactCount() {
        return wd.findElements(By.xpath("//td/input")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//td/input"));
        for(WebElement element : elements){
            String name = element.getText();
            ContactData contact = new ContactData("Doe",null,null,null,null);
            contacts.add(contact);
        }
        return contacts;

    }
}
