package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AllSongsPage extends BasePage{

    //CONSTRUCTOR
    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //LOCATORS
    private By firstSong = By.cssSelector(".all-songs tr.song-item:nth-child(1)");
    private By playOption = By.cssSelector("li.playback");

    //METHODS
    public void contextClickFirstSong(){
        actions.contextClick(findElementAndClickability(firstSong)).perform();
    }
    public void choosePlayOption(){
        findElementAndClickability(playOption).click();
    }
}
