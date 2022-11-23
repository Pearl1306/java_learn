package ru.stqa.learn.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.GroupData;
import ru.stqa.learn.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test0").withHeader("test3").withFooter("test1");
        app.group().modify(group);
        assertThat(before.size(),equalTo(app.group().count()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withoutAdded(modifiedGroup).withAdded(group)));//Ctrl+space 2 times for calling static methods

    }


}
