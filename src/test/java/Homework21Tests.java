import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class Homework21Tests extends BaseTest {

    @Test
    public void improvedRenamePlaylist() {

        String expectedSongDeletedMessage = "Updated playlist \"homework21.\"";
        String newPlaylistName = "homework21";

        //Login
        enterEmail(email);
        enterPassword(password);
        submit();
        //Create New Playlist
        createNewPlaylist(newPlaylistName);
        //Add songs to the Playlist
        addSongToPlaylist();
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

    public void addSongToPlaylist(){
        //search for song
        searchSong("Music");
        //click view all button
        clickViewAllButton();
        System.out.println("The size is   " + wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("section#playlistWrapper td.title"))).getSize());
        //
        //select first song from results
        //clickFirstSongInList();
        //click add to button
        //clickAddToButton();
        //choose a playlist from menu to add song
        //choosePlaylist();

    }



    private void choosePlaylist() {
        //WebElement listItem = driver.findElement
        // (By.cssSelector("#songsWrapper .existing-playlists ul :nth-child(5)"));
        WebElement listItem = driver.findElement
                (By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'playlist2024')]"));
        listItem.click();
    }

    private void clickAddToButton() {
        WebElement addToListButton = driver.findElement(By.cssSelector("#songResultsWrapper button[data-test='add-to-btn']"));
        addToListButton.click();
    }

    private void clickFirstSongInList() {
        WebElement firstSong = driver.findElement(By.cssSelector("#songResultsWrapper tr[class='song-item']:nth-child(1)"));
        firstSong.click();
    }

    private void clickViewAllButton() {
       wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("[data-test='view-all-songs-btn']"))).click();
    }


    public void searchSong(String song) {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("input[type='search']")));
        searchField.clear();
        searchField.sendKeys(song);
    }

}