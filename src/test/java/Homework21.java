
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{
@Test
public void renamePlayList() throws InterruptedException{
    navigateToPage();
    provideEmail("demo@class.com");
    providePassword("te$t$tudent");
    clickSubmit();
    doubleClickChoosePlaylist();
    enterPlaylistName();
    Assert.assertTrue(doesPlaylistExist());


    }

}