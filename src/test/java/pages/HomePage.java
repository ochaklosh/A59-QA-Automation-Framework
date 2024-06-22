package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    //CONSTRUCTOR
    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    //LOCATORS
    By userAvatarIcon = By.cssSelector("img.avatar");
    By allSongsList = By.cssSelector("li a.song");
    
    //METHODS
    public WebElement getUserAvatar(){
        return findElement(userAvatarIcon);
    }
    public void chooseAllSongsList(){
        findElement(allSongsList).click();
    }

}
