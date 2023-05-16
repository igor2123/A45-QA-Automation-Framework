import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.UUID;

public class ProfileTests {


    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    @Given("I open browser")
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @After
    public void iCloseBrowser() {
        driver.quit();

    }


    @Given("I open Login Page")
    public void openLoginPage() {
        driver.get("https://bbb.testpro.io");
    }

        @When("I enter email \"demo@class.com")
    public void iEnterEmailDemoClassCom() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.clear();
        emailField.sendKeys("demo@class.com");

    }

    @And("I enter password \"te$t$tudent")
    public void iEnterPasswordTe$t$tudent() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.clear();
        passwordField.sendKeys("te$t$tudent");

    }

    @And("I submit button")
    public void iSubmitButton() {
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submit.click();

    }

    @Then("I am logged in")
    public void iAmLoggedIn() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed());

    }

    @And("I provide current email \"demo@class.com")
    public void iProvideCurrentEmailDemoClassCom() {
        WebElement currentEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileEmail")));
        currentEmail.clear();
        currentEmail.sendKeys("demo@class.com");
    }

    @And("I provide current password \"te$t$tudent")
    public void iProvideCurrentPasswordTe$t$tudent() {
        WebElement currentPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileNewPassword")));
        currentPassword.clear();
        currentPassword.sendKeys("te$t$tudent");
    }

    @And("I click avatar icon")
    public void iClickAvatarIcon() {
        WebElement avatarIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img.avatar")));
        avatarIcon.click();
    }


    @And("I change profile name")
    public void iChangeProfileName() {
        WebElement profileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileName")));
        profileName.clear();
        profileName.sendKeys(generateRandomName());
    }

    @And("I click save button")
    public void iClickSaveButton() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-submit")));
        saveButton.click();
    }

    @Then("I changed profile name")
    public void iChangedProfileName() {
        WebElement actualProfileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.view-profile>span")));
        Assert.assertEquals(actualProfileName.getText(), generateRandomName());
    }
    private static String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");



    }


}


