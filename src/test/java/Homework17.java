import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework17 extends BaseTest {
    @Test
    public void addSongPlaylist() throws InterruptedException {
        String newSongAddedNotificationText = "I added one song";

    }

    public void searchSong(String SongTitleKeyword) throws InterruptedException {
        WebElement searchField = driver.findElement(By.cssSelector("div#SearchForm input[type = search]"));
        searchField.sendKeys(SongTitleKeyword);
        Thread.sleep(2000);

        navigateToPage();
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        searchSong("Dark Days");
        clickViewAllBtn();
        selectFirstSongResult();
        clickAddToBtn();
        choosePlaylist();
        String newSongAddedNotificationText = "Your new song has been added to the playlist.";
        Assert.assertTrue(getNotificationText().contains(newSongAddedNotificationText));
        driver.quit();
    }
        public void clickViewAllBtn() throws InterruptedException {
            WebElement viewAllSearchResults = driver.findElement(By.cssSelector("div.results section.songs h1 button"));
            viewAllSearchResults.click();
            Thread.sleep(2000);
        }
        public void selectFirstSongResult() throws InterruptedException {
            WebElement firstSongResult = driver.findElement(By.cssSelector("section#songResultWrapper tr.song-item td.title"));
            firstSongResult.click();
        }
        public void clickAddToBtn() throws InterruptedException {
            WebElement addToBtn = driver.findElement(By.cssSelector("button.btn-add-to"));
            addToBtn.click();
            Thread.sleep(2000);
        }
        public void choosePlaylist() throws InterruptedException {
            WebElement playlistElement = driver.findElement(By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(),'Igor Smirnov')]"));
            playlistElement.click();
            Thread.sleep(2000);
        }
        public String getNotificationText() {
            WebElement notificationElement = driver.findElement(By.cssSelector("div.success.show"));
            return notificationElement.getText();
        }



       }


