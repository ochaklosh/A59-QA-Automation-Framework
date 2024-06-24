package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

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
    WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;

    //PAGE METHODS
    public LoginPage provideEmail(String email){
        emailField.clear();
        emailField.sendKeys(email);
        //waitForVisibility(emailField).clear();
        //waitForVisibility(emailField).sendKeys(email);
        return this;
    }
    public LoginPage providePassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
        //waitForVisibility(passwordField).clear();
        //waitForVisibility(passwordField).sendKeys(password);
        return this;
    }
    public LoginPage clickSubmit(){
        submitBtn.click();
        //findElementAndClickability(submitBtn).click();
        return this;
    }

    public void login(){
        provideEmail("demo@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
    }
}
