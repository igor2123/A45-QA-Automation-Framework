import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHomework extends BaseTest {


    @Test
    public void testMethod() throws InterruptedException {
        String playlistName = "kz-new playlist";
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        generateRandomName();
        enterPlaylistName();
        doesPlaylistExist();


        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='search']")));
        search.sendKeys("Pluto");

        WebElement viewAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.results h1 > button")));
        viewAll.click();


        WebElement firstSong = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section#songResultsWrapper td.title")));
        firstSong.click();


        WebElement addToButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section#songResultsWrapper button.btn-add-to")));
        addToButton.click();
    }
}
