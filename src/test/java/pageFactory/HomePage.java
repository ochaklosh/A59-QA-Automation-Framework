package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import pages.BasePage;

import java.util.List;

public class HomePage extends BasePage {

    //CONSTRUCTOR
    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    //LOCATORS
    @FindBy(css = "img.avatar")
    private  WebElement userAvatarIcon;

    @FindBy(css="i[title='Create a new playlist']")
    private WebElement addPlayLists;

    @FindBy(css = "[data-testid='playlist-context-menu-create-simple']")
    private WebElement newPlaylistChoice;

    @FindBy(css = "#playlists input[type='text']")
    private WebElement inputNewPlaylist;

    @FindBy(css = "input[type='search']")
    private WebElement searchField;

    @FindBy(css = "div.success.show")
    private WebElement notificationSuccessMsg;

    @FindBy(xpath = "//div[@class='success show' and contains(text(), 'Added 1 song into')]")
    private WebElement notificationSuccessMsgOneSongAdded;

    @FindBy(css = "div.success.hide")
    private WebElement messageHidden;

    //private By userAvatarIcon = By.cssSelector("img.avatar");
    private By allSongsList = By.cssSelector("li a.songs");
    private By playBtn = By.xpath("//footer[@id='mainFooter']//span[@class='play']//i");
    private By soundBar = By.xpath("//img[@alt='Sound bars']");
    private By playlistDetails = By.cssSelector("span.meta.text-secondary span.meta");
    private By playlistWithName = By.xpath("//a[contains(text(),'TestPro Playlist2024')]");
    private By playlistInputFld = By.cssSelector("[name='name']");
    //private By notificationMsg = By.cssSelector("div.success.show");
    private By playlistWrapper = By.cssSelector("section#playlistWrapper td.title");

    //Methods
    public WebElement getUserAvatar(){
        return waitForVisibilityPF(userAvatarIcon);
    }
    public void chooseAllSongsList(){
        waitForPresence(allSongsList).click();
    }

    public void playButtonSong(){
        mouseOverAndJavascriptExecutorClick(playBtn);
    }
    public boolean isDisplayedPlayingSong(){
        return waitForVisibility(soundBar).isDisplayed();
    }

    public WebElement hoverPlay() {
        WebElement playButton = driver.findElement(playBtn);
        actions.moveToElement(playButton).build().perform();
        return wait.until(ExpectedConditions.visibilityOf(playButton));
    }

    public void choosePlaylistByName(String playlistName) {
        waitForVisibility(playlistWithName).click();
    }

    public void displayAllSongs() {
        List<WebElement> songList = driver.findElements
                (playlistWrapper);
        System.out.println("Number of Songs found: "+songsCount());
        for (WebElement e: songList){
            System.out.println(e.getText());
        }
    }
    public int songsCount() {
        return driver.findElements(playlistWrapper).size();
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
    public String getSuccessMsg(){
        return waitForVisibilityPF(notificationSuccessMsg).getText();
    }
    public String getSuccessMsgOneSongAdded(){
        return waitForVisibilityPF(notificationSuccessMsgOneSongAdded).getText();
    }
    public void waitForMsgToHide(){
        waitForVisibilityPF(messageHidden);
    }
    public void createNewPlaylist(String newPlaylistName) {
        findElementAndClickabilityPF(addPlayLists).click();
        waitForVisibilityPF(newPlaylistChoice).click();
        waitForVisibilityPF(inputNewPlaylist).sendKeys(newPlaylistName);
        inputNewPlaylist.submit();
    }
    public void searchSong(String song) {
        waitForVisibilityPF(searchField).clear();
        searchField.sendKeys(song);
    }
}
