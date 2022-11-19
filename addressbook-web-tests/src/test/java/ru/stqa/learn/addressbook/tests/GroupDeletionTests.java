package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.learn.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().goToGroupPage();
    if(!app.getGroupHelper().isThereAgroup()){
      app.getGroupHelper().creatGroup(new GroupData(null, null, "test1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size()-1;
    app.getGroupHelper().deleteGroup(index);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size()-1,after.size());

    before.remove(index);
    Assert.assertEquals(before,after);
  }

}
