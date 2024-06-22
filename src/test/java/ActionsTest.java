import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllSongsPage;
import pages.HomePage;
import pages.LoginPage;

public class ActionsTest extends BaseTest{
    //Test #1 Contextual click
    @Test
    public void playsong(){

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongsPage = new AllSongsPage(driver);

        loginPage.login();

        //Choose All Songs
        homePage.chooseAllSongsList();
        //Contextual Click on the first song
        allSongsPage.contextClickFirstSong();
        //Click on Play from menu
        allSongsPage.choosePlayOption();
       // Assert.assertTrue((allSongsPage.isPlaying()));

    }
}
