import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.UUID;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    private static WebDriver driver;
    public static Actions actions = null;
    public static WebDriverWait wait;
    private static String url = "";
    private ThreadLocal<WebDriver> threadDriver;

    @BeforeMethod
    @Parameters({"BaseURL", "browser"})
    public void launchBrowser(@Optional String baseURL, @Optional String browser) throws MalformedURLException {
        driver = pickBrowser(browser);
        driver.get(baseURL);
        url = baseURL;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        threadDriver = ThreadLocal.withInitial(() -> driver);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private WebDriver newLambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("113.0");
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "igor.smirnov");
        ltOptions.put("accessKey", "kMwcMPmvrxyWEdOZXjWp5lVQMGrSs3tIESg45t2z21o8Rnkgc0");
        ltOptions.put("project", "Untitled");
        ltOptions.put("name", "Test1");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }

    WebDriver getDriver() {
        return threadDriver.get();
    }

    private WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String gridURL = "http://192.168.12.105:4444";

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.addArguments("-private");
                return new FirefoxDriver(optionsFirefox);
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "grid-firefox":
                capabilities.setCapability("browserName", "firefox");
                return new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);
            case "grid-edge":
                capabilities.setCapability("browserName", "edge");
                return new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);
            case "grid-chrome":
                capabilities.setCapability("browserName", "chrome");
                return new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);
            case "cloud":
                return newLambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications", "--remote-allow-origins=*", "--incognito", "--start-maximized");
                return new ChromeDriver(options);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            threadDriver.remove();
        }
    }

    public static void provideEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        emailField.sendKeys(email);
    }

    public static void providePassword(String password) {

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
        passwordField.sendKeys(password);
    }

    public static void clickSubmit() throws InterruptedException {
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

    public void doubleClickChoosePlaylist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        WebElement playlistElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();
    }


      public void enterPlaylistName() {
      WebElement playlistInputField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='name']")));
      playlistInputField.sendKeys((Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE)));
      playlistInputField.sendKeys();
      playlistInputField.sendKeys(Keys.ENTER);
      }

      public boolean doesPlaylistExist() {
          WebElement playlistElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + "" + "']")));
          return playlistElement.isDisplayed();

      }

}

