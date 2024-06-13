package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    //CONSTRUCTOR
    public LoginPage(WebDriver givenDriver){
        super(givenDriver);
    }

    //LOCATORS
    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitBtn = By.cssSelector("button[type='submit']");

    //PAGE METHODS
    public void provideEmail(String email){
        findElement(emailField).clear();
        findElement(emailField).sendKeys();
    }
    public void providePassword(String password){
        findElement(passwordField).clear();
        findElement(passwordField).sendKeys();
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
