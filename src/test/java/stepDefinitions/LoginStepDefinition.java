package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageFactory.HomePage;
import pageFactory.LoginPage;

import java.time.Duration;

public class LoginStepDefinition {

    WebDriver driver;

    WebDriverWait wait;


    @Before("I open browser")     //Cucumber Before Hook
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    @And("I open Koel Login Page")
    public void iOpenKoelLoginPage() {
        driver.get("https://qa.koel.app/");
    }

    @When("I enter email {string}")
    public void iEnterEmail(String email) {
        //Example when we want to use our page objects
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email);
      /* WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated
               (By.cssSelector("[type='email']")));
       emailField.clear();
       emailField.sendKeys(email);  */
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[type='password']")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @And("I click submit")
    public void iClickSubmit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[type='submit']"))).click();
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        //Example of using Page Objects
        HomePage homePage = new HomePage(driver);
        homePage.getUserAvatar();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
      /*  Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("img.avatar"))).isDisplayed());     */
    }

    @After      //Cucumber After Hook
    public void closeBrowser(){
        driver.quit();
    }
}
