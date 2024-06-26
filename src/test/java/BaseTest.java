import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
    public static WebDriver driver = null;
    ChromeOptions options = new ChromeOptions();
    //initialization of driver wait
    public WebDriverWait wait;
    //initialization of the Fluent wait object / Rarely Used
    public Wait<WebDriver> fluentWait;
    //Actions
    public Actions actions = null;

    String url = "https://qa.koel.app/";
    String email = "demo@testpro.io";
    String password = "te$t$tudent";
    //String email = "oksana.chaklosh@testpro.io";
    //String password = "8qUBYosp";

//We set up a new driver using the manager it has to be before the suite before when our suite is initialized
//then we launch the browser before our methods are executed which is our test cases


    @BeforeSuite
    static void setupClass() {
    //This is where we have to set up a new driver when we are using a different web browser
       // WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

//In before method we had Chrome options, we had arguments and then we had the driver
// We needed these 3 lines ChromeOptions options = new ChromeOptions(); options.addArguments("--remote-allow-origins=*");
//                and        driver = new ChromeDriver(options);

//For Firefox we only need   driver = new FirefoxDriver();

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String baseURL) throws MalformedURLException {
        //Pre-Condition
        //Added ChromeOptions argument below to fix websocket error
        /*--remote-allow-origins=* argument suggests allowing remote origins. It means that
        the application will accept requests from any remote origin, which can be helpful for testing
        scenarios where requests are made from various sources.
        */
       options.addArguments("--remote-allow-origins=*");
       /*--disable-notifications argument suggests disabling notifications. This could be useful during
       automated testing to prevent interruptions from notifications that may interfere with the testing process.
        */
       options.addArguments("--disable-notifications");
       /*--incognito argument indicates launching the application in incognito mode. Incognito mode typically
       means that the browser doesn't store browsing history or cookies, which can be beneficial for testing
       scenarios where a clean browsing session is required.
        */
       options.addArguments("--incognito");
       options.addArguments("--start-maximized");
       /*This setting seems to be specific to ChromeDriver, a tool for automated testing with Google Chrome.
       It suggests excluding the switch enable-automation, which is a Chrome feature indicating that Chrome is
       being controlled by automated test software. By excluding this switch, it can help avoid detection by
       websites or applications that may behave differently when they detect automated testing.
        */
       options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        //driver = new ChromeDriver(options);
        //driver = new FirefoxDriver();
    //Method to pick browser dynamically
        driver =  pickBrowser(System.getProperty("browser"));

        //Example of implicit wait condition with timeout of 10 sec before throwing an exception
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        actions = new Actions(driver);
        //Example of using waits explicitly maximum time 10 sec, we initialized before line 16
        wait =new WebDriverWait(driver, Duration.ofSeconds(20));
        //Example of using a Fluent waits which is more flexible, But Rarely used
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(200));
        navigateToPage(baseURL);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
        }

    protected void navigateToPage(String url) {
        driver.get(url);
    }

    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        //We are setting up for Grid what kind of browser or other modifiers that browser needs
        DesiredCapabilities caps = new DesiredCapabilities();
        //address we got from command promt after runing the grid
        String gridURL = "http://192.168.1.226:4444";

        switch(browser){
            case"firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver();
            //Grid cases
            case "grid-edge":
                caps.setCapability("browserName","MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-notifications");

                return driver = new ChromeDriver(chromeOptions);
        }
    }
    protected void enterEmail(String email) {
        WebElement emailField = wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        //WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    protected void enterPassword(String password) {
        WebElement passwordField = wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        //WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys("te$t$tudent");
    }

    protected void submit() {
        //WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        WebElement submit = wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submit.click();
    }
}

    //This is a change
