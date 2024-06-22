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
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.login();
        Assert.assertTrue(homePage.hoverPlay().isDisplayed());
    /* OLD way    /Login
        enterEmail(email);
        enterPassword(password);
        submit();
    // Verification*/
       // Assert.assertTrue(hoverPlay().isDisplayed());
    }

    //@Test
    public void countSongsInPlaylist() {
    //Login
        enterEmail(email);
        enterPassword(password);
        submit();
    //Choose a Playlist by Name
        choosePlaylistByName("TestPro Playlist");
    //DisplayAllSongs
        displayAllSongs();
    //Number of songs is equal to number of songs displayed in the info section of the Playlist
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(songsCount())));
    }

    //@Test
    public void renamePlaylist(){
    // Login
        enterEmail(email);
        enterPassword(password);
        submit();
    //Double-click on Playlist name
        doubleClickPlaylist();
    //Enter the new Playlist Name
        enterNewName();
    //Verify
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), expectedMsg);
    }

    public String getRenamePlaylistSuccessMsg(){
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.success.show")));
        return notification.getText();
    }

    private void enterNewName() {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE);
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    private void doubleClickPlaylist() {
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(
                //By.cssSelector(".playlist:nth-child(3)")));
                By.xpath("//a[contains(text(),'TestPro Playlist')]")));
        actions.doubleClick(playlist).perform();
    }

    //Helper Methods


    // Helper methods
    public String getPlaylistDetails(){
        return driver.findElement
                (By.cssSelector("span.meta.text-secondary span.meta")).getText();
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

    private void choosePlaylistByName(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(text(),'"+playlistName+"')]"))).click();
    }

    /*Helper Methods
    public WebElement hoverPlay(){
        WebElement playBtn = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        actions.moveToElement(playBtn).perform();
        return wait.until(ExpectedConditions.visibilityOf(playBtn));
    }*/

}
