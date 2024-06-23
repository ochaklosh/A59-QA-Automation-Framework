package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    //Locators


    public BasePage(WebDriver givenDriver){
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }
    //METHODS

    //Method to wait for an element to be visible on the page
    public WebElement waitForVisibility(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    //Method to wait for all elements on the page to be visible
    public List<WebElement> waitUntilAllElementsLocatedAreVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    //Method to wait for an element to become invisible
    public boolean waitUntilRequiredElementIsInvisible (By locator){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    //Method to wait until all required elements are invisible
    public boolean waitUntilRequiredElementsAreInvisible(List<WebElement> elements){
        return wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }
    //Method to wait until  element to be clickable
    public WebElement findElementAndClickability(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    //Method to click an element after waiting for it to be visible
    public void tryClick(By locator){
        waitForVisibility(locator).click();
    }

    public void doubleClick(By locator){
        actions.doubleClick(waitForVisibility(locator)).perform();
    }

    //Method to click an element without any wait
    public void tryClickNoWait(By locator){
        driver.findElement(locator).click();
    }
    //Method to wait for an element to be present on the page
    public WebElement waitForPresence(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    //Method to wait for all elements to be present on the page
    public List<WebElement> waitForPresentsOfAllElements(By locator){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    //Method to move the mouse over an element and click and hold it
    public void mouseOverAndClickAndHold(By locator){
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).clickAndHold().perform();
    }
    //Method to move the mouse over an element and click it
    public void mouseOverAndJavascriptExecutorClick(By locator){
        try{
            WebElement element = waitForVisibility(locator);
            actions.moveToElement(element).perform();  //move mouse over element
            actions.click(element).build().perform(); //perform a click using Actions class
        }  catch(TimeoutException e){
            System.out.println("Standard click failed, trying JavaScript click");
            //If element is not clickable, resort to Javascript
            WebElement element = driver.findElement(locator);
            //Javascript to trigger mouseover event
            String mouseOverScript =
             "var event = new MouseEvent('mouseover', { 'view': window, 'bubbles': true, 'cancelable': true}); arguments[0].dispatchEvent(event);";
            ((JavascriptExecutor) driver).executeScript(mouseOverScript,element);
            //Javascript to trigger click event
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }

    }

}
