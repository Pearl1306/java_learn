package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if(app.group().list().size() ==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(before.size()-1,after.size());

    before.remove(deletedGroup);
    Assert.assertEquals(before,after);
  }

}
