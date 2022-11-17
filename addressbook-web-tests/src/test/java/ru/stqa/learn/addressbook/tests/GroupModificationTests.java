package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{
    @Test
    public void testModification(){
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAgroup()){
            app.getGroupHelper().creatGroup(new GroupData(null, null, "test1"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(before.get(before.size()-1).getId(),"test2", "test3", "test1");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupmodification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size(),after.size());
        before.remove(before.size()-1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

    }
}
