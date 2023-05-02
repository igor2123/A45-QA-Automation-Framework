import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.UUID;

public class BaseTests {
    public static WebDriver driver;
    public static Actions actions = null;
    public static WebDriverWait wait;
    public static String url = null;
   String playlistName = "TestPro Playlist";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @DataProvider(name = "IncorrectLoginData")
    public static Object[][] getDataFromDataProviders() {

        return new Object[][]{
                {"invalid@mail.com", "invalidPass"},
                {"demo@class.com", ""},
                {"", ""}
        };
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) {
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);

        url = BaseURL;
        navigateToPage();

    }

        @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public static void navigateToPage() {
        driver.get(url);

    }

    public static void provideEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']")));
        emailField.sendKeys(email);
    }

    public static void providePassword(String password) {

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
        passwordField.sendKeys(password);
    }

    public static void clickSubmit()  {
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        submitButton.click();


    }


    public static void clickSaveButton() {
        WebElement saveButton = driver.findElement(By.cssSelector("button.btn-submit"));
        saveButton.click();
    }

    public static void provideProfileName(String randomName) {
        WebElement profileName = driver.findElement(By.cssSelector("[name='name']"));
        profileName.clear();
        profileName.sendKeys(randomName);
    }

    public static void provideCurrentPassword(String password) {
        WebElement currentPassword = driver.findElement(By.cssSelector("[name='current_password']"));
        currentPassword.clear();
        currentPassword.sendKeys(password);

    }

    public static String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void clickAvatarIcon() {
        WebElement avatarIcon = driver.findElement(By.cssSelector("img.avatar"));
        avatarIcon.click();
    }


    public void doubleClickPlaylist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        WebElement playlistElement = driver.findElement(By.cssSelector(".playlist:nth-child(3)"));
        actions.doubleClick(playlistElement).perform();
    }

    public void enterPlaylistName() {
        WebElement playlistInputField = driver.findElement(By.cssSelector("input[name='name']"));
        playlistInputField.sendKeys((Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE)));
        playlistInputField.sendKeys(playlistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public boolean doesPlaylistExist() {
        WebElement playlistElement = driver.findElement(By.xpath("//a[text()='" + playlistName + "']"));
        return playlistElement.isDisplayed();
    }
}

