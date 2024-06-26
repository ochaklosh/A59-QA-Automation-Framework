//THIS IS REFACTORING Homework17Tests

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageFactory.AllSongsPage;
import pageFactory.HomePage;
import pageFactory.LoginPage;

public class Homework23Tests extends BaseTest {

    @Test
    public void addSongToPlaylist(){
    //GIVEN
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongsPage = new AllSongsPage(driver);

        String expectedSongAddedMessage = "Added 1 song into \"playlist2024.\"";
        //String expectedSongAddedMessage = "Created playlist \"playlist2024.\"";
        String newPlaylistName = "playlist2024";

    //WHEN
        //login
        loginPage.login();
        //I am creating new Playlist to be used later in the testB
        homePage.createNewPlaylist(newPlaylistName);
        //wait for the success message to hide
        homePage.waitForMsgToHide();
        //search for song
        homePage.searchSong("Music");
        //click view all button
        allSongsPage.clickViewAllButton();
        //select first song from results
        allSongsPage.clickFirstSongInList();
        //click add to button
        allSongsPage.clickAddToButton();
        //choose a playlist from menu to add song
             //addToNewPlayList(newPlaylistName);
        allSongsPage.choosePlaylist();
    //THEN
        //Compare actual vs expected
        //Assert.assertEquals(homePage.getSuccessMsg(), expectedSongAddedMessage);
        Assert.assertEquals(homePage.getSuccessMsgOneSongAdded(), expectedSongAddedMessage);
    }

/*/Extra possible method
    private void addToNewPlayList(String newPlaylistName) {
        WebElement newPlaylist = driver.findElement
                (By.cssSelector("#songsWrapper section.new-playlist input[type='text']"));
        newPlaylist.sendKeys(newPlaylistName);
        WebElement submit = driver.findElement(By.cssSelector("#songsWrapper button[type='submit']"));
        submit.click();
    }*/


}

