package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.learn.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        int before =app.getGroupHelper().getGroupCount();
        app.getGroupHelper().creatGroup(new GroupData("test2", null, null));
        int after =app.getGroupHelper().getGroupCount();
        app.logout();
        Assert.assertEquals(after,before + 1);


    }


}
