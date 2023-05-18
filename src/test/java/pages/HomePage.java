package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    String playlistName = "TestPro Playlist";

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By userAvatarIcon = By.cssSelector("img.avatar");

    public WebElement getUserAvatar() {
        return findElement(userAvatarIcon);
    }

    public void doubleClickPlaylist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        WebElement playlistElement = driver.findElement(By.cssSelector(".playlist:nth-child(3)"));
        actions.doubleClick(playlistElement).perform();
    }

    public void enterNewPlaylistName() {
        WebElement playlistInputField = driver.findElement(By.cssSelector("input[name='name']"));
        playlistInputField.sendKeys((Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE)));
        playlistInputField.sendKeys(playlistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public boolean doesPlaylistExist() {
        WebElement playlistElement = driver.findElement(By.xpath("//a[text()='" + playlistName + "']"));
        return playlistElement.isDisplayed();

    }

    public void openPlaylist() {
        WebElement emptyPlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".playlist:nth-child(3)")));
        emptyPlaylist.click();

    }

    public void deletePlaylist() {
        WebElement deletePlaylistButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-delete-playlist")));
        deletePlaylistButton.click();

    }

    public WebElement getDeletedPlaylistMsg() {
        By deletedPlaylistMsg = By.cssSelector(".deleted-playlist-message");
        wait.until(ExpectedConditions.visibilityOfElementLocated(deletedPlaylistMsg));
        return driver.findElement(deletedPlaylistMsg);

    }
}
