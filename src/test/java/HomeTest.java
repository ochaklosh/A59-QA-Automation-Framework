import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

public class HomeTest extends BaseTest{
    String newPlaylistName = "Sample Edited Playlist";
    String expectedMsg = "Updated playlist \"Sample Edited Playlist.\"";

    @Test
    public void hoverOverPlayButtonAndPlaySong() {
       //GIVEN
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        //WHEN
        loginPage.login();

        //Works without Javascript but locator is within the method on homePage
            homePage.hoverPlay();
        //THEN
            Assert.assertTrue(homePage.hoverPlay().isDisplayed());
                                         //////    OR
        //Works using JAvascript
             //homePage.playButtonSong();
             //Assert.assertTrue(homePage.isDisplayedPlayingSong());
    /* OLD way    /Login
        enterEmail(email);
        enterPassword(password);
        submit();
    // Verification*/
        //Assert.assertTrue(homePage.hoverPlay().isDisplayed());
    }

    @Test
    public void countSongsInPlaylist() {
        //GIVEN
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        //WHEN
        loginPage.login();
        homePage.choosePlaylistByName("TestPro Playlist2024");
        homePage.displayAllSongs();
        //THEN
        Assert.assertTrue(homePage.getPlaylistDetails().contains(String.valueOf(homePage.songsCount())));


        ///OLD WAY
    /*/Login
        enterEmail(email);
        enterPassword(password);
        submit();
    //Choose a Playlist by Name
        homePage.choosePlaylistByName("TestPro Playlist2024");
    //DisplayAllSongs
        displayAllSongs();
    //Number of songs is equal to number of songs displayed in the info section of the Playlist
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(songsCount())));*/
    }

    @Test
    public void renamePlaylist(){
    //GIVEN
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
    //WHEN
        loginPage.login();
        homePage.doubleClickPlaylist();
        homePage.enterNewName(newPlaylistName);
    //THEN
        Assert.assertEquals(homePage.getRenamePlaylistSuccessMsg(), expectedMsg);

    ///OLD WAY
     /*/ Login
        enterEmail(email);
        enterPassword(password);
        submit();
    //Double-click on Playlist name
        doubleClickPlaylist();
    //Enter the new Playlist Name
        enterNewName();
    //Verify
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), expectedMsg);  */
    }

    /*Helper Methods
     OLD WAY hoverPlay() that worked without Javascript
    public WebElement hoverPlay(){
        WebElement playBtn = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        actions.moveToElement(playBtn).perform();
        return wait.until(ExpectedConditions.visibilityOf(playBtn));
    }
    private void displayAllSongs() {
        List<WebElement> songList = driver.findElements
                (By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of Songs found: "+songsCount());
        for (WebElement e: songList){
            System.out.println(e.getText());
        }
    }
    private int songsCount() {
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();
    }
    public String getPlaylistDetails(){
        return driver.findElement
                (By.cssSelector("span.meta.text-secondary span.meta")).getText();
    }
    private void doubleClickPlaylist() {
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(
                //By.cssSelector(".playlist:nth-child(3)")));
                By.xpath("//a[contains(text(),'TestPro Playlist2024')]")));
        actions.doubleClick(playlist).perform();
    }
    private void enterNewName() {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE);
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }
    public String getRenamePlaylistSuccessMsg(){
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.success.show")));
        return notification.getText();
    }
    */

}
