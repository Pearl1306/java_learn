package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test2", null, "test3");
        app.getGroupHelper().creatGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();

        app.logout();
        Assert.assertEquals(after.size(),before.size() + 1);

        group.setId(after.stream().max((Comparator<GroupData>) (o1, o2) ->
                Integer.compare(o1.getId(),o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));



    }


}
