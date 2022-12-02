package ru.stqa.learn.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.learn.addressbook.appmanager.ApplicationManager;
import ru.stqa.learn.addressbook.model.ContactData;
import ru.stqa.learn.addressbook.model.Contacts;
import ru.stqa.learn.addressbook.model.GroupData;
import ru.stqa.learn.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();

    }
    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("start test " + m.getName() + " with parametrs " + Arrays.asList(p));

    }
    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info("stop test " + m.getName());

    }
    public void verifyGroupListInUI() {
        if(Boolean.getBoolean("verifyUI")){
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream().map((g)-> new GroupData().withId(g.getId())
                  .withName(g.getName())).collect(Collectors.toSet())));
        }
    }
    public void verifyContactListInUI() {
        if(Boolean.getBoolean("verifyUI")){
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream().map((g)-> new ContactData().withId(g.getId())
                  .withFirstname(g.getFirstname()).withLastname(g.getLastname()).withAddress(g.getAddress())
                  .withHomephone(g.getHomephone()).withMobilePhone(g.getMobilephone())
                  .withWorkphone(g.getWorkphone()).withEmail(g.getEmail())
                  .withEmail2(g.getEmail2()).withEmail3(g.getEmail3())).collect(Collectors.toSet())));
        }
    }
}
