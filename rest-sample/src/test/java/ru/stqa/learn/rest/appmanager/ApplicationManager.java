package ru.stqa.learn.rest.appmanager;

import org.openqa.selenium.WebDriver;

import java.util.Properties;


public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private RestHelper restHelper;


    public ApplicationManager(String browser){
        this.browser = browser;
        String target = System.getProperty("target", "local");
        properties = new Properties();
    }

   /* public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }
    public void stop() {
        if(wd!=null){
            wd.quit();
        }
    }*/

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    public RestHelper rest(){
        if(restHelper==null){
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }

}
