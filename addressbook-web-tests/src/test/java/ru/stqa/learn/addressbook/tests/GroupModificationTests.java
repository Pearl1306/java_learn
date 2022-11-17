package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase{
    @Test
    public void testModification(){
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAgroup()){
            app.getGroupHelper().creatGroup(new GroupData(null, null, "test2"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test2", "test3", "test1"));
        app.getGroupHelper().submitGroupmodification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size(),after.size());

    }
}
