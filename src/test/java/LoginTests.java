import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {


    @Test
    public void loginEmptyEmailPassword() {

        //navigateToPage();
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    //Happy Path - Login Test
    @Test
    public void loginValidEmailValidPassword() throws InterruptedException {

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
    }

    // Not a Happy Path - Negative Path
    @Test

    public void loginWithInvalidEmailValidPassword() throws InterruptedException {

        //Step1
        //navigateToPage();
        //Step2
        enterEmail("invalid@koel.io");
        //Step3
        enterPassword(password);
        //Step4
        submit();
        Thread.sleep(2000);
        //Expected Results - Assertions (URL should stay the same)
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void loginWithValidEmailEmptyPassword() throws InterruptedException {
        //navigateToPage();
        enterEmail(email);
        submit();

        Thread.sleep(2000); //sleep or pause for 2 seconds (adjust as needed)
        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

    //dataProvider allows to run 5 negative tests with data sets specified in BaseTest
    @Test(dataProvider = "NegativeLoginTestData", dataProviderClass = TestDataProvider.class)
    public void negativeLoginTests(String email, String password){
        enterEmail(email);
        enterPassword(password);
        submit();
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }
}
