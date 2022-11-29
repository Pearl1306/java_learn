package ru.stqa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("email"), contactData.getEmail());
        if(contactData.getPhoto() != null){
            attach(By.name("photo"), contactData.getPhoto());
        }

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
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    public void deleteContact() {

        click(By.xpath("//input[@value='Delete']"));
        //wd.findElement(By.cssSelector("div.msgbox"));
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id+ "']")).click();
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
        /*wd.findElement(By.cssSelector("input[value='"+ id+"']"))
                .findElement(By.xpath("../..//img[@alt='Edit']")).click();*/
        //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id)));
      //  wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
      //  wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/../../td[8]/a",id))).click();

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
        contactCache=null;
        returnToHomePage();
    }
    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact,false);
        submitContactModification();
        contactCache=null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        acceptDeletionContact();
        contactCache=null;

    }

    public int count() {
        return wd.findElements(By.xpath("//td/input")).size();
    }
    private Contacts contactCache = null;

    public Contacts all() {
        if(contactCache!=null){
        return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String last = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            contactCache.add(new ContactData().withId(id).withFirstname(name).withLastname(last)
                    .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);

    }

    public ContactData contactInfoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email =wd.findElement(By.name("email")).getAttribute("value");
        String email2 =wd.findElement(By.name("email2")).getAttribute("value");
        String email3 =wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withAddress(address).withLastname(lastname)
                .withHomephone(home).withMobilePhone(mobile).withWorkphone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3);

    }
}
