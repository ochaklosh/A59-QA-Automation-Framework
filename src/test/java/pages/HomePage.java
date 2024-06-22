package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    //CONSTRUCTOR
    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    //LOCATORS
    private By userAvatarIcon = By.cssSelector("img.avatar");
    private By allSongsList = By.cssSelector("li a.song");
    private By playBtn = By.cssSelector("[data-testid='play-btn']");

    //METHODS
    public WebElement getUserAvatar(){
        return findElementVisibility(userAvatarIcon);
    }
    public void chooseAllSongsList(){
        findElementAndClickability(allSongsList).click();
    }

    public WebElement hoverPlay(){
        WebElement playButton = findElementVisibility(playBtn);
        actions.moveToElement(playButton).perform();
        return playButton;
    }
}
