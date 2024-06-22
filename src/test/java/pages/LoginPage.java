package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    //CONSTRUCTOR is needed for this page to become an object
    public LoginPage(WebDriver givenDriver){
        super(givenDriver); //given driver is coming from BasePage
    }

    //LOCATORS
    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitBtn = By.cssSelector("button[type='submit']");

    //PAGE METHODS
    public void provideEmail(String email){
        findElement(emailField).clear();
        findElement(emailField).sendKeys(email);
    }
    public void providePassword(String password){
        findElement(passwordField).clear();
        findElement(passwordField).sendKeys(password);
    }
    public void clickSubmit(){
        findElement(submitBtn).click();
    }
    public void login(){
        provideEmail("demo@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
    }
}
