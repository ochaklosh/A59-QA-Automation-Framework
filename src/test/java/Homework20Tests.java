import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20Tests extends BaseTest{
    @Test
    public void deletePlaylist() throws InterruptedException {
        String expectedSongDeletedMessage = "Deleted playlist \"homework20.\"";
        String newPlaylistName = "homework20";

    //Login
        enterEmail(email);
        enterPassword(password);
        submit();
    //Create Playlist homework19
        createNewPlaylist(newPlaylistName);
    //Select Playlist
        selectPlaylist();
    //Delete Playlist
        deleteSelectedList();
    //Verify actual message with expected
        Assert.assertEquals(getDeletePlaylistMessage(), expectedSongDeletedMessage);
    }

    private String getDeletePlaylistMessage() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.show")));
        //WebElement message = driver.findElement(By.cssSelector("div.success.show"));
        return message.getText();
    }

    private void createNewPlaylist(String newPlaylistName) {
        WebElement addPlayLists = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("i[title='Create a new playlist']")));
        //WebElement addPlayLists = driver.findElement(By.cssSelector("i[title='Create a new playlist']"));
        addPlayLists.click();
        WebElement newPlaylistChoice = driver.findElement
                (By.cssSelector("[data-testid='playlist-context-menu-create-simple']"));
        newPlaylistChoice.click();
        WebElement inputNewPlaylist = driver.findElement(By.cssSelector("#playlists input[type='text']"));
        inputNewPlaylist.sendKeys(newPlaylistName);
        inputNewPlaylist.submit();
    }

    private void deleteSelectedList() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.hide")));
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("button[title='Delete this playlist']")));
        //WebElement deleteBtn = driver.findElement(By.cssSelector("button[title='Delete this playlist']"));
        deleteBtn.click();
    }

    private void selectPlaylist() {
        WebElement playList = driver.findElement
                (By.xpath("//section[@id='playlists']//li[contains(normalize-space(), 'homework20')]"));
        playList.click();
    }
}
