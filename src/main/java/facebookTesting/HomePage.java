package facebookTesting;

import org.apache.xpath.operations.Bool;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

/**
 * Created by draicu on 8/9/2016.
 */
public class HomePage {

    private WebDriver webDriver;
    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//textarea[@name='xhpc_message']")
    private WebElement newPostTextArea;

    @FindBy(xpath = "//div[@id='logoutMenu']")
    private  WebElement logoutMenuDropDown;

    @FindBy(xpath = "//button[text()='Post']")
    private WebElement postButton;

    @FindBy(xpath = "(//div[contains(@class,'userContent')]/p)[position()=1]") //latest post
    private WebElement latestPostText;

    @FindBy(xpath = "//div[@data-click='profile_icon']")
    private WebElement profileButton;

    public Long timestamp = new Date().getTime(); //unique input due to error message when trying to repost the same text
    public Long textToBePosted = timestamp; //saving the timestamp to another value (otherwise it will change)

    public void completeNewPostTextArea(){
        newPostTextArea.sendKeys(textToBePosted.toString());
    }

    public Boolean isLogoutMenuDropDownPresent(){
        if (logoutMenuDropDown.isDisplayed())
            return true;
        else
            return false;
    }
    public void clickPostButton(){
        postButton.click();
    }

    public String getLatestPostText(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } //used Thread.sleep() function due to the fact that the timestamp changes between requests, and the assert fails
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(ExpectedConditions.visibilityOf(latestPostText));
        String latestMessage = latestPostText.getText();
        return latestMessage;
    }


    public ProfilePage openProfilePage(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           e.printStackTrace();
       }

        profileButton.click();


        ProfilePage profilePage = PageFactory.initElements(webDriver, ProfilePage.class);
        profilePage.waitForPage();
        return profilePage;
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        //wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        wait.until(ExpectedConditions.textToBePresentInElement(logoutMenuDropDown,""));
    }
}
