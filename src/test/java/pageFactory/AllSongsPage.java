package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageFactory.BasePage;

public class AllSongsPage extends BasePage {

    //CONSTRUCTOR
    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //LOCATORS
    @FindBy(css = "[data-test='view-all-songs-btn']")
    private WebElement viewAllButton;

    @FindBy(css = "#songResultsWrapper tr[class='song-item']:nth-child(1)")
    private WebElement firstSong;

    @FindBy(css = "#songResultsWrapper button[data-test='add-to-btn']")
    private WebElement addToListButton;

    @FindBy(xpath = "//section[@id='songResultsWrapper']//li[contains(text(),'playlist2024')]")
    private WebElement playlistItem;

    //private By firstSong = By.cssSelector(".all-songs tr.song-item:nth-child(1)");
    private By playOption = By.cssSelector("li.playback");

    //METHODS
    public void contextClickFirstSong(){
        actions.contextClick(waitForVisibilityPF(firstSong)).perform();
    }
    public void choosePlayOption(){
        findElementAndClickability(playOption).click();
    }
    //Method to click View All button after when the search results are displayed
    public void clickViewAllButton() {
        findElementAndClickabilityPF(viewAllButton).click();
    }
    public void clickFirstSongInList() {
        findElementAndClickabilityPF(firstSong).click();
    }
    public void clickAddToButton() {
        findElementAndClickabilityPF(addToListButton).click();
    }
    public void choosePlaylist() {
        findElementAndClickabilityPF(playlistItem).click();
    }
}

