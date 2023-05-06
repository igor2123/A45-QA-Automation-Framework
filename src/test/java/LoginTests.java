
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public static void loginEmptyEmailEmptyPasswordTest() {
    WebDriver driver = new ChromeDriver();

    String url = "https://bbb.testpro.io/";
    driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();
    }
    }

