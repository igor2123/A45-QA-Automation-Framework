import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"baseUrl"})
    public void launchBrowser() {
//        if (System.getProperty("os.name").toLowerCase().contains("win")) {
//            System.setProperty("WebDriver.chrome.driver", "chromedriver.exe");
//        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }


    public void navigateToPage() {
        //System.out.println("hello");
        String url = "https://bbb.testpro.io/";
        driver.get(url);
    }

    public static void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public static void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public static void clickSubmit() throws InterruptedException {
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
        Thread.sleep(2000);
    }

        public WebElement getDeletedPlaylistmsg(){
            return driver.findElement(By.cssSelector("div.success.show"));

        }
        public void openPlaylist() throws InterruptedException {
            WebElement emptyPlaylist = driver.findElement(By.cssSelector(".playlist:nth-child(3)"));
            emptyPlaylist.click();
            Thread.sleep(2000);
        }
            public void deletePlaylist() throws InterruptedException {
                WebElement deletePlaylistButton = driver.findElement(By.cssSelector(".btn-delete-playlist"));
                deletePlaylistButton.click();
                Thread.sleep(2000);
            }
        }












