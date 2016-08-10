package facebookTesting;

import org.apache.xpath.operations.Bool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by draicu on 8/9/2016.
 */
public class facebookPageTest extends TestBaseClass {

    @Test
    public void facebookPageTesting(){
        logInPage.completeEmailField();
        logInPage.completePasswdField();

        HomePage homePage = logInPage.openHomePage();
        boolean isLogoutMenuPresent = homePage.isLogoutMenuDropDownPresent();
        assertTrue(isLogoutMenuPresent); //verification that the user is logged in

        homePage.completeNewPostTextArea(); //writing the new post
        homePage.clickPostButton(); //posting the message

        String latestMessagePosted = homePage.getLatestPostText();
        String actualMessagePosted = homePage.textToBePosted.toString();
        assertEquals(actualMessagePosted,latestMessagePosted); //verifying the last message posted

        ProfilePage profilePage = homePage.openProfilePage();

        boolean isProfilePageShown = profilePage.isAboutTabShown();

        assertTrue(isProfilePageShown); //verifying the Profile Page is opened

        profilePage.openStoryOptionsDropDown();
        profilePage.clickEditPostOption();
        profilePage.editPostField();
        profilePage.saveEditedPost();
        profilePage.sharePost();

        boolean isShareConfirmationMessageDisplayed = profilePage.getShareConfirmation();
        assertTrue(isShareConfirmationMessageDisplayed); //verify that the confirmation message was displayed

        profilePage.addPhotoVideo();
        String lastImageSrc = profilePage.getLastPostedImageSrc();
        System.out.println("Source of last image: " + lastImageSrc);

        assertTrue(lastImageSrc.contains(".jpg")); //verify the uploaded file is a picture (contains ".jpg" extension)
    }
}
