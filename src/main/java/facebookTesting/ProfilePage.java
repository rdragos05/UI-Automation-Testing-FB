package facebookTesting;

import org.junit.experimental.theories.DataPoint;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by draicu on 8/9/2016.
 */
public class ProfilePage {

    private WebDriver webDriver;
    public ProfilePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "(//div[contains(@class,'userContentWrapper ')]/div/div/div/a[@role='button'])[position()=1]")
    private WebElement storyOptionsDropDown;

    @FindBy(xpath = "//ul[@role='menu']/li[@data-feed-option-name='FeedEditOption']//span/span")
    private WebElement editPostOption;

    @FindBy(xpath = "//span[@data-text]/../../../../..")
    private WebElement editPostTextField;

    @FindBy(xpath = "//div[@role='dialog']//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "(//a[@title='Send this to friends or post it on your timeline.'])[1]")
    private WebElement shareButton;

    @FindBy(xpath = "(//div[contains(@id,'tl_unit')])[position()=1]//img[contains(@src,'.jpg') and @height]")
    private WebElement lastPicturePostArea;

    @FindBy(xpath = "//li[contains(@class,'MenuItem')]//span[text()='Share Now (Friends)']")
    private WebElement shareNowButton;

    @FindBy(xpath = "//a[contains(@class,'fbReactComposerAttachmentSelector_MEDIA')]/span/span[1]")
    private WebElement postPhotoVideoTab;

    @FindBy(xpath = "//input[@name='composer_photo']")
    private WebElement uploadPhotoVideoButton;

    @FindBy(xpath = "//button[text()='Post']")
    private WebElement postButton;

    @FindBy(xpath = "//div[@class='dialog_body']")
    private WebElement shareConfirmationMessage;

    @FindBy(xpath = "//div[@id='fbTimelineHeadline']//a[@data-tab-key][2]")
    private WebElement aboutTab;

    public void openStoryOptionsDropDown(){
        storyOptionsDropDown.click();
    }

    public void clickEditPostOption(){
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(ExpectedConditions.visibilityOf(editPostOption));
        editPostOption.click();
    }

    public void editPostField(){
        editPostTextField.sendKeys("asd");
    }

    public void saveEditedPost(){
        saveButton.click();
    }

    public void sharePost(){
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(ExpectedConditions.visibilityOf(shareButton));

        Actions mouseOverShareSection = new Actions(webDriver);
        mouseOverShareSection.moveToElement(shareButton).build().perform();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        shareButton.click();

        wait.until(ExpectedConditions.visibilityOf(shareNowButton));

        shareNowButton.click();
    }

    public boolean getShareConfirmation(){
        if (shareConfirmationMessage.isDisplayed())
            return true;
        else
            return false;

    }

    String fileToBeUploadedLocation = "C:\\Users\\draicu\\Desktop\\city.PNG";

    public void addPhotoVideo(){


        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(ExpectedConditions.visibilityOf(postPhotoVideoTab));
        /*wait.until(ExpectedConditions.visibilityOf(postPhotoVideoTab));

        Actions mouseOverShareSection = new Actions(webDriver);
        mouseOverShareSection.moveToElement(postPhotoVideoTab).build().perform();
*/
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        postPhotoVideoTab.click();

        uploadPhotoVideoButton.click();
        uploadPhotoVideoButton.sendKeys(fileToBeUploadedLocation);

        wait.until(ExpectedConditions.elementToBeClickable(postButton));

        postButton.click();
    }

    public String getLastPostedImageSrc(){
        return lastPicturePostArea.getAttribute("src");
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        //wait.until(ExpectedConditions.visibilityOfAllElements(productList));
       // wait.until(ExpectedConditions.elementToBeClickable(shareButton));
    }

    public boolean isAboutTabShown(){ //this means that the profile page is opened
        if (aboutTab.isDisplayed())
            return true;
        else
            return false;
    }
}

