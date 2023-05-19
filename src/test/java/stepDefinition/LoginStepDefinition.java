package stepDefinition;

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
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;


public class LoginStepDefinition {
    private WebDriver driver;
    private WebDriverWait wait;
    private String newProfileName = "Insert Profile Name";


    @Given("I open browser")
    public void openBrowser() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }


    @Given("I open Login Page")
    public void openLoginPage() {
        BaseDefinition.getThreadLocal().get("https://bbb.testpro.io/");


    }

    @When("I enter email {string}")
    public void enterEmail(String email) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email);
    }

    @And("I enter password {string}")
    public void enterPassword(String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.providePassword(password);
    }

    @And("I submit")
    public void submit() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.clickSubmitBtn();

    }

    @Then("I am logged in")
    public void loggedIn() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

    }

    @And("I enter incorrect password {string}")
    public void enterIncorrectPassword(String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
    }

    @Then("I still Login page")
    public void stillLoginPage() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(loginPage.getRegistrationLink().isDisplayed());

    }

    @When("I enter Not existing email {string}")
    public void enterNotExistingEmail(String email) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email);
    }

    @When("I enter Empty email {string}")
    public void enterEmptyEmail(String email) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email);
    }

    @And("I enter Empty password {string}")
    public void enterEmptyPassword(String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.providePassword(password);
    }

    @And("I provide current email")
    public void iProvideCurrentEmail() {
        WebElement currentEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileEmail")));
        currentEmail.clear();
        currentEmail.sendKeys("demo@class.com");
    }

    @And("I provide current password")
    public void iProvideCurrentPassword() {
        WebElement currentPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileCurrentPassword")));
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
        profileName.sendKeys("Insert Profile Name");
    }

    @And("I click save button")
    public void iClickSaveButton() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-submit")));
        saveButton.click();
    }

    @Then("I changed profile name")
    public void iChangedProfileName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        WebElement actualProfileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.view-profile>span")));
        String actualName = actualProfileName.getText();
        Assert.assertEquals(actualName, newProfileName);
    }

}
