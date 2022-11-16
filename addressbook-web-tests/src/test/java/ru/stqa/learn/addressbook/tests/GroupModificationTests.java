package ru.stqa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.learn.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{
    @Test
    public void testModification(){
        app.getNavigationHelper().goToGroupPage();
        int before =app.getGroupHelper().getGroupCount();
        if(!app.getGroupHelper().isThereAgroup()){
            app.getGroupHelper().creatGroup(new GroupData("test2", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test2", "test3", "test1"));
        app.getGroupHelper().submitGroupmodification();
        app.getGroupHelper().returnToGroupPage();
        int after =app.getGroupHelper().getGroupCount();
        Assert.assertEquals(before,after);

    }
}
