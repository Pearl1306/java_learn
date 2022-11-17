package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().creatGroup(new GroupData("test2", null, null));
        List<GroupData> after = app.getGroupHelper().getGroupList();

        app.logout();
        Assert.assertEquals(after.size(),before.size() + 1);


    }


}
