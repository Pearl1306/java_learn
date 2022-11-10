package ru.stqa.learn.addressbook;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() {

    goToGroupPage();
    selectGroup();
    deleteSelectedGroup();
    returnToGroupPage();
  }


}
