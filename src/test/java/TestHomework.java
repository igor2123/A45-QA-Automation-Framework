import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHomework extends BaseTests {


    @Test
    public void testMethod() throws InterruptedException {
        String playlistName = "kz-new playlist";
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();


        WebElement search = driver.findElement(By.cssSelector("[type='search']"));
        search.sendKeys("Pluto");

        WebElement viewAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.results h1 > button")));
        viewAll.click();


        WebElement firstSong = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section#songResultsWrapper td.title")));
        firstSong.click();


        WebElement addToButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section#songResultsWrapper button.btn-add-to")));
        addToButton.click();


        // search the playlist and click
        WebElement playlist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'kz-new playlist')]"));
        playlist.click();

        WebElement notif = driver.findElement(By.cssSelector("div.success.show"));
        Assert.assertEquals(notif.getText(), "Added 1 song into \"kz-new playlist.\"");

    }
      }
