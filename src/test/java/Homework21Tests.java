import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21Tests extends BaseTest {
    String editedPlaylistName = "homework21";
    String expectedPlaylistUpdatedMessage = "Updated playlist \"homework21.\"";
    String newPlaylistName = "Playlist21";

    @Test
    public void improvedRenamePlaylist() {

    //Login
        enterEmail(email);
        enterPassword(password);
        submit();
    //Create New Playlist
        createNewPlaylist(newPlaylistName);
    //Wait for successful message to hide
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.hide")));
    //Add songs to the Playlist
        addSongToPlaylist();
    //Update Playlist Name
        changePlaylistName();
     //Wait for successful message to hide
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.hide")));
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), expectedPlaylistUpdatedMessage);
    }


    private void createNewPlaylist(String newPlaylistName) {
        wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("i[title='Create a new playlist']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[data-testid='playlist-context-menu-create-simple']"))).click();
        WebElement inputNewPlaylist = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("#playlists input[type='text']")));
        inputNewPlaylist.sendKeys(newPlaylistName);
        inputNewPlaylist.submit();
    }
///////  Add Song To Play list methods
    public void addSongToPlaylist(){
        //search for song
        searchSong("Music");
        //click view all button
        clickViewAllButton();
        //for (int i=0; i<3; i++ ) {
            clickFirstSongInList();
            clickAddToButton();
            choosePlaylistToAddTo();
       // }
    }

    public void searchSong(String song) {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("input[type='search']")));
        searchField.clear();
        searchField.sendKeys(song);
    }

    private void clickViewAllButton() {
        wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("[data-test='view-all-songs-btn']"))).click();
    }

    private void clickFirstSongInList() {
        WebElement firstSong = driver.findElement(By.cssSelector("#songResultsWrapper tr[class='song-item']:nth-child(1)"));
        firstSong.click();
    }

    private void clickAddToButton() {
        WebElement addToListButton = driver.findElement(By.cssSelector("#songResultsWrapper button[data-test='add-to-btn']"));
        addToListButton.click();
    }

    private void choosePlaylistToAddTo() {
        wait.until(ExpectedConditions.elementToBeClickable
           (By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'Playlist21')]"))).click();
    }

/////////  Change the Name of the Play List

    private void changePlaylistName() {
    choosePlaylist();
    doubleClickPlaylist();
    enterNewName();
    }

    private void choosePlaylist() {
        wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(text(),'Playlist21')]"))).click();
    }

    private void doubleClickPlaylist() {
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Playlist21')]")));
        actions.doubleClick(playlist).perform();
    }

    private void enterNewName() {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE);
        playlistInputField.sendKeys(editedPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public String getRenamePlaylistSuccessMsg(){
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.success.show")));
        return notification.getText();
    }

}