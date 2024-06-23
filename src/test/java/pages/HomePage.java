package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage{

    //CONSTRUCTOR
    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    //LOCATORS
    private By userAvatarIcon = By.cssSelector("img.avatar");
    private By allSongsList = By.cssSelector("li a.song");
    private By playBtn = By.xpath("//footer[@id='mainFooter']//span[@class='play']//i");
    private By soundBar = By.xpath("//img[@alt='Sound bars']");
    private By playlistDetails = By.cssSelector("span.meta.text-secondary span.meta");
    private By playlistWithName = By.xpath("//a[contains(text(),'TestPro Playlist2024')]");
    private By playlistInputFld = By.cssSelector("[name='name']");
    private By notificationMsg = By.cssSelector("div.success.show");
    //Methods
    public WebElement getUserAvatar(){
        return waitForVisibility(userAvatarIcon);
    }
    public void chooseAllSongsList(){
        tryClick(allSongsList);
    }

    public void playButtonSong(){
        mouseOverAndJavascriptExecutorClick(playBtn);
    }
    public boolean isDisplayedPlayingSong(){
        return waitForVisibility(soundBar).isDisplayed();
    }

    public WebElement hoverPlay() {
        WebElement playButton = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        actions.moveToElement(playButton).build().perform();
        return wait.until(ExpectedConditions.visibilityOf(playButton));
    }

    public void choosePlaylistByName(String playlistName) {
        waitForVisibility(playlistWithName).click();
    }

    public void displayAllSongs() {
        List<WebElement> songList = driver.findElements
                (By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of Songs found: "+songsCount());
        for (WebElement e: songList){
            System.out.println(e.getText());
        }
    }
    public int songsCount() {
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();
    }
    public String getPlaylistDetails(){
        return waitForVisibility(playlistDetails).getText();
    }
    public void doubleClickPlaylist() {
        WebElement playlist = waitForVisibility(playlistWithName);
        actions.doubleClick(playlist).perform();
    }
    public void enterNewName(String newPlaylistName) {
        WebElement playlistInputField = waitForVisibility(playlistInputFld);
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE);
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }
    public String getRenamePlaylistSuccessMsg(){
        WebElement notification = waitForVisibility(notificationMsg);
        return notification.getText();
    }
}
