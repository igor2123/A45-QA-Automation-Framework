import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddSongToPlaylistTest{

    @Test
    public void testAddSongToPlaylist() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Log in to website
        driver.get("https://bbb.testpro.io/");
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
        emailInput.sendKeys("demo@class.com");
        passwordInput.sendKeys("te$t$tudent");
        loginButton.click();

        // Search for a song
        WebElement searchInput = driver.findElement(By.id("search-input"));
        WebElement searchButton = driver.findElement(By.id("search-button"));
        searchInput.sendKeys("Pluto");
        searchButton.click();

        // View all search results
        WebElement viewAllButton = driver.findElement(By.cssSelector(".view-all-button"));
        viewAllButton.click();

        // Add the first song to a playlist
        WebElement firstSong = driver.findElement(By.cssSelector(".search-result:first-child .add-to-button"));
        firstSong.click();
        WebElement addPlaylistButton = driver.findElement(By.cssSelector(".add-to-modal .add-playlist-button"));
        addPlaylistButton.click();
        WebElement playlistNameInput = driver.findElement(By.cssSelector(".add-to-modal .playlist-name-input"));
        WebElement createPlaylistButton = driver.findElement(By.cssSelector(".add-to-modal .create-playlist-button"));
        String playlistName = "My Playlist " + System.currentTimeMillis();
        playlistNameInput.sendKeys(playlistName);
        createPlaylistButton.click();

        // Verify that the notification message appears
        WebElement notification = driver.findElement(By.cssSelector(".notification"));
        assertEquals(true, notification.isDisplayed());

        // Verify that the notification message has the correct text
        String expectedMessage = "Added 1 song into " + playlistName;
        String actualMessage = notification.getText();
        assertEquals(expectedMessage, actualMessage);

        // Close the browser
        driver.quit();
    }
}
