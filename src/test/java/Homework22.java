import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework22 extends BaseTests {

    @Test
    public void renamePlaylist() throws InterruptedException{
        String playlistName = "TestPro Playlist";
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

          loginPage.login();
          homePage.doubleClickPlaylist();
          homePage.enterNewPlaylistName(playlistName);
          Assert.assertTrue(homePage.doesPlaylistExist(playlistName));
    }

}