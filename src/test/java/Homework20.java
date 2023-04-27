import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20 extends BaseTest{
    @Test
    public void deletePlaylistTest()  {


        navigateToPage();
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        openPlaylist();
        deletePlaylist();

        WebElement deletedplaylistmsg = getDeletedPlaylistmsg();
        Assert.assertTrue(deletedplaylistmsg.isDisplayed());



    }
}


