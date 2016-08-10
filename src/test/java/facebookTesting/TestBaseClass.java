package facebookTesting;

import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by draicu on 8/9/2016.
 */
public class TestBaseClass {

    private static WebDriver webDriver;
    protected LogInPage logInPage;

    @BeforeClass
    public static void setUp(){
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://www.facebook.com");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Before
    public void initPageObjects(){
        logInPage = PageFactory.initElements(webDriver,LogInPage.class);
    }

    /*@AfterClass
    public static void tearDown(){
        webDriver.close();
    }*/
}
