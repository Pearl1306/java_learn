package ru.stqa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.learn.addressbook.tests.TestBase;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))){
            return;
        }else{
            click(By.linkText("groups"));
        }


    }

    public void goToHomePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        }else{
            click(By.linkText("home"));

        }
    }
}
