package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    //Locators
    private By soundBarVisualizer = By.cssSelector("[data-testid = 'sound-bar-play']");

    public BasePage(WebDriver givenDriver){
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    public WebElement findElementVisibility(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement findElementAndClickability(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void click(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    public void doubleClick(By locator){
        actions.doubleClick(findElementVisibility(locator)).perform();
    }
    /*
    public boolean isPlaying(){
        findElement(soundBarVisualizer);
        return soundBarVisualizer.isDisplayed;
    }  */
}
