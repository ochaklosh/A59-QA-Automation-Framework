package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AllSongsPage extends BasePage{

    //CONSTRUCTOR
    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //LOCATORS
    By firstSong = By.cssSelector(".all-songs tr.song-item:nth-child(1)");
    By playOption = By.cssSelector("li.playback");

    //METHODS
    public void contextClickFirstSong(){
        actions.contextClick(findElement(firstSong)).perform();
    }
    public void choosePlayOption(){
        findElement(playOption).click();
    }
}
