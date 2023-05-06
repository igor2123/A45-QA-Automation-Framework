import java.net.URI;
import java.net.MalformedURLException;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTest {
    // Initialize the url as an empty string
    public static String url = "";
    // Initialize the driver variable as null
    private static WebDriver driver = null;
    // Initialize the wait variable as null
    private static WebDriverWait wait = null;
    // Initialize the actions variable as null
    private static Actions actions = null;

    @BeforeSuite
    public void setupClass() {
        // Set up the ChromeDriver
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL", "browser"})
    public void launchBrowser(String BaseURL, String browser) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        // Check which browser was passed as a parameter
        driver = pickBrowser(browser);
        // Set the implicit wait time to 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Initialize the wait object with a timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Initialize the actions object with the driver
        actions = new Actions(driver);
        // Set the url variable to the passed BaseURL
        url = BaseURL;
        navigateToPage();
    }

    private void navigateToPage() {
        driver.get(url);
    }

    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        // Initialize the DesiredCapabilities object
        DesiredCapabilities caps = new DesiredCapabilities();
        // Initialize the gridURL string
        String gridURL = "http://192.168.12.105:4444";

        switch (browser) {
            case "firefox":
                // Set up the FirefoxDriver
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "Edge":
                // Set up the EdgeDriver
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case "grid-edge":
                // Set up the RemoteWebDriver for Edge on the Selenium Grid
                caps.setCapability("browserName", "MicrosoftEdge");
                return new RemoteWebDriver((URI.create(gridURL).toURL()), caps);

            case "grid-firefox":
                // Set up the RemoteWebDriver for Firefox on the Selenium Grid
                caps.setCapability("browserName", "Firefox");
                return new RemoteWebDriver((URI.create(gridURL).toURL()), caps);

            default:
                // Set up the ChromeDriver by default
                return new ChromeDriver();
        }
    }
}
