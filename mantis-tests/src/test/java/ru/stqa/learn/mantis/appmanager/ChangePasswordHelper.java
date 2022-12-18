package ru.stqa.learn.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase{
  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }
  public void loginAsAdmin() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"),"administrator");
    type(By.name("password"), "root");
    click(By.xpath("//input[@value='Login']"));
  }
  public void goToUserPage(){
    click(By.xpath("//a[contains(text(),'Manage')]"));
  }
  public void goToMenageUserPage(){
    click(By.xpath("//a[contains(text(),'Manage Users')]"));
  }
  public void selectUser(){
   // user = wd.findElement(By.xpath("//tr[2]/td/")).getText();
    //System.out.println(wd.findElement(By.xpath("//tr[2]/td/")).getText());
   // click(By.xpath("//tr[2]/td/a"));
    click(By.xpath("//a[contains(text(),'user1671339351530')]"));
  }
  public void resetPassword(){
    click(By.cssSelector("input[value='Reset Password']"));
  }
}
