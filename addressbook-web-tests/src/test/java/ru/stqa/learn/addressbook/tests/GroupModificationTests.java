package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.group().all().size() ==0){
            app.group().create(new GroupData().withName("test1"));
        }
    }
    @Test
    public void testModification(){
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test2").withHeader("test3").withFooter("test1");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(before.size(),after.size());

        before.remove(modifiedGroup);
        before.add(group);
        //Comparator<? super GroupData> byId = (g1,g2)-> Integer.compare(g1.getId(),g2.getId());
        Assert.assertEquals(before,after);
        //Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after)); --converting to sets and comparing

    }


}
