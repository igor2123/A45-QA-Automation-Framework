package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage {

    private final By playlistName = By.cssSelector(".playlist:nth-child(3)");
    private final By playlistNameField = By.cssSelector("[name='name']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void doubleClickPlaylist() {
        doubleClick(playlistName);
    }
    public void enterNewPlaylistName(String playlistName) {
        findElement(playlistNameField).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, playlistName, Keys.ENTER);
    }

    public boolean doesPlaylistExist(String playlistName) {
        By newPlaylist = By.xpath("//a[text()='" + playlistName + "']");
        return findElement(newPlaylist).isDisplayed();
    }

    public void clickAvatarIcon() {
    }
}


