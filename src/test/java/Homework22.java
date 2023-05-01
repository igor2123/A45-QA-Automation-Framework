import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework22 extends BaseTests {

    @Test
    public void renamePlaylist() throws InterruptedException{
        String playlistName = "TestPro Playlist";
        HomePage homePage = new HomePage(driver);
        navigateToPage();
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        homePage.doubleClickPlaylist();
        homePage.enterNewPlaylistName(playlistName);
        Assert.assertTrue(homePage.doesPlaylistExist(playlistName));
    }

}