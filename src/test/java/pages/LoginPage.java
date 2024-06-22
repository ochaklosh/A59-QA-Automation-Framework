package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    //CONSTRUCTOR is needed for this page to become an object
    public LoginPage(WebDriver givenDriver){
        super(givenDriver); //given driver is coming from BasePage
    }

    //LOCATORS
    private By emailField = By.cssSelector("input[type='email']");
    private By passwordField = By.cssSelector("input[type='password']");
    private By submitBtn = By.cssSelector("button[type='submit']");

    //PAGE METHODS
    public void provideEmail(String email){
        findElementVisibility(emailField).clear();
        findElementVisibility(emailField).sendKeys(email);
    }
    public void providePassword(String password){
        findElementVisibility(passwordField).clear();
        findElementVisibility(passwordField).sendKeys(password);
    }
    public void clickSubmit(){
        findElementAndClickability(submitBtn).click();
    }
    public void login(){
        provideEmail("demo@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
    }
}
