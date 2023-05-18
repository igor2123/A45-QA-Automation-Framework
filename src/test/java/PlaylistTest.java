import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class PlaylistTest extends BaseTest {
    @Test
    public static void renamePlaylist() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        homePage.doubleClickPlaylist();
        homePage.enterNewPlaylistName();
        Assert.assertTrue(homePage.doesPlaylistExist());
    }

    @Test
    public static void deletePlaylist() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.login();
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        homePage.openPlaylist();
        homePage.deletePlaylist();
        homePage.getDeletedPlaylistMsg();
        WebElement deletedPlaylistMsg = null;
        Assert.assertTrue(deletedPlaylistMsg.isDisplayed());


    }



}



