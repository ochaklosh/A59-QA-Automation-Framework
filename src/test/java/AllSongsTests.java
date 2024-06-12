import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllSongsTests extends BaseTest{
    @Test
    public void playSongWithRightClick(){
        //Login
        enterEmail(email);
        enterPassword(password);
        submit();
        //Navigate to AllSongs Page
        navigateToChooseAllSongs();
        //Right click / Context Click on the first song
        contextClickFirstSong();
        //Choose Play from context menu
        choosePlayOption();
        //Verify if Song is being played.
        Assert.assertTrue(isSongPlaying());

    }

    private void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.playback"))).click();
    }

    private void contextClickFirstSong() {
        WebElement firstSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        actions.contextClick(firstSongElement).perform();
    }

    private void navigateToChooseAllSongs() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li a.songs"))).click();
    }
public boolean isSongPlaying(){
        WebElement soundBarVisualizer = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[data-testid = 'sound-bar-play']")));
        return soundBarVisualizer.isDisplayed();
    }



}
