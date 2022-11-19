package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAgroup()){
            app.getGroupHelper().creatGroup(new GroupData(null, null, "test1"));
        }
    }
    @Test
    public void testModification(){
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size()-1;
        GroupData group = new GroupData(before.get(index).getId(),"test2", "test3", "test1");
        app.getGroupHelper().modifyGroup(index, group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size(),after.size());
        before.remove(index);
        before.add(group);

        Comparator<? super GroupData> byId = (g1,g2)-> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
        //Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after)); --converting to sets and comparing

    }


}
