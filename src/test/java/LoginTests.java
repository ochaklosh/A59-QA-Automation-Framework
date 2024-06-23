import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {


    private static final Log log = LogFactory.getLog(LoginTests.class);

    @Test
    public void loginEmptyEmailPassword() {

        //navigateToPage();
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    //Happy Path - Login Test
    @Test
    public void loginValidEmailValidPassword(){
        //Using Page Object Model  THE NEW WAY
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

/*     THE OLD WAY
    //Step1
        //navigateToPage();
    //Step2
        enterEmail(email);
        Thread.sleep(1000);
    //Step3
        enterPassword(password);
        Thread.sleep(1000);
    //Step4
        submit();
        //hard stop by java not supported by Selenium
        //Thread.sleep(4000);
    //Step5
        //Avatar Icon for Actual vs Expected
        //continue using explicit waits initialized in BaseTest class,
        // we are waiting for avatar to be either visible within 10 sec before throwing an exception
        WebElement avatarIcon = wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[class='avatar']")));
        //Here we are looking for the web element without giving it extra time to load
        //WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        //Assertions - Expected vs Actual
        Assert.assertTrue(avatarIcon.isDisplayed());
        */
    }

    // Not a Happy Path - Negative Path
    @Test

    public void loginWithInvalidEmailValidPassword(){
    //New Way with POM
        LoginPage loginPage = new LoginPage(driver);

        loginPage.provideEmail("invalid@koel.io");
        loginPage.providePassword(password);
        loginPage.clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), url);

    ////  OLD WAY
       /* //Step1
        //navigateToPage();
        //Step2
        enterEmail("invalid@koel.io");
        //Step3
        enterPassword(password);
        //Step4
        submit();
        Thread.sleep(2000);
        //Expected Results - Assertions (URL should stay the same)
        Assert.assertEquals(driver.getCurrentUrl(), url);*/
    }

    @Test
    public void loginWithValidEmailEmptyPassword(){
        //NEW WAY
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email);
        loginPage.clickSubmit();
        Assert.assertEquals(driver.getCurrentUrl(),url);
        /* OLD WAY
        //navigateToPage();

        enterEmail(email);
        submit();

        Thread.sleep(2000); //sleep or pause for 2 seconds (adjust as needed)
        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(),url); */
    }

    //dataProvider allows to run 5 negative tests with data sets specified in BaseTest
    @Test(dataProvider = "NegativeLoginTestData", dataProviderClass = TestDataProvider.class)
    public void negativeLoginTests(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        Assert.assertEquals(driver.getCurrentUrl(), url);

        /* OLD WAY
        enterEmail(email);
        enterPassword(password);
        submit();
        Assert.assertEquals(driver.getCurrentUrl(), url);*/
    }
}
