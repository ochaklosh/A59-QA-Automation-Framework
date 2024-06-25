package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import pages.BasePage;
import pageFactory.BasePage;
public class LoginPage extends BasePage {

    //CONSTRUCTOR is needed for this page to become an object
    public LoginPage(WebDriver givenDriver){
        super(givenDriver); //given driver is coming from BasePage
    }

    //LOCATORS
    //private By emailField = By.cssSelector("input[type='email']");
    //private By passwordField = By.cssSelector("input[type='password']");
    //private By submitBtn = By.cssSelector("button[type='submit']");

    @FindBy(css = "input[type='email']")
    private WebElement emailField;

    @FindBy(css = "input[type='password']")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitBtn;

    //PAGE METHODS
    public void provideEmail(String email){
        emailField.clear();
        waitForVisibilityPF(emailField).sendKeys(email);
    }
    public void providePassword(String password){
        waitForVisibilityPF(passwordField).clear();
        waitForVisibilityPF(passwordField).sendKeys(password);
    }
    public void clickSubmit(){
        findElementAndClickabilityPF(submitBtn).click();
    }

    public void login(){
        provideEmail("demo@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
    }
}
