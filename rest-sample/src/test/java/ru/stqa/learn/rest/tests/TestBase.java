package ru.stqa.learn.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.learn.rest.appmanager.ApplicationManager;
import ru.stqa.learn.rest.model.Issue;

import java.io.IOException;
import java.util.Set;


public class TestBase {

   protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();

    }
   public boolean isIssueOpen(int issueId) throws IOException {
       Set<Issue> issues = app.rest().getIssues();
       Issue issue = issues.iterator().next();
       String issueStatus = issue.getStatus();
       if(issueStatus.equals("resolved")) {
           return false;
       } else {
           return true;
       }

   }
    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
