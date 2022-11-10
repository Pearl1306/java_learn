package ru.stqa.learn.addressbook;

import org.testng.annotations.*;


public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test2", "test3", "test1"));
        submitGroupCreation();
        returnToGroupPage();
        logout();

    }


}
