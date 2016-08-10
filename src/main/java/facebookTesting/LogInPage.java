package facebookTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by draicu on 8/9/2016.
 */
public class LogInPage {

    private WebDriver webDriver;
    public LogInPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='pass']")
    private WebElement passwdField;

    @FindBy(xpath = "//label[@id='loginbutton']/input")
    private WebElement logInButton;

    public void completeEmailField(){
        emailField.sendKeys("rdragos05@gmail.com");
    }

    public void  completePasswdField(){
        passwdField.sendKeys("73s71234");
    }

    public HomePage openHomePage(){
        logInButton.click();

        HomePage homePage = PageFactory.initElements(webDriver, HomePage.class);
        homePage.waitForPage();
        return homePage;
    }

}
