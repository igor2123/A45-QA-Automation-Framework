package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "button[type='submit']")
    protected WebElement submitButtonLocator;
    @FindBy(css = "[type='email']")
    protected WebElement emailField;
    @FindBy(css = "[type='password']")
    protected WebElement passwordField;
    @FindBy(css = "a#hel")
    protected WebElement registrationLink;
    @FindBy(css = ".fa-sign-out")
    protected WebElement logOutLocator;

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    public LoginPage clickSubmitBtn() {
        submitButtonLocator.click();
        return this;
    }

    public LoginPage provideEmail(String email) {
         emailField.sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void provideLoginSucceed() {
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmitBtn();
    }

    public WebElement getRegistrationLink() {
        return registrationLink;
    }

    public LoginPage clicklogOut() {
        logOutLocator.click();
        return this;
    }
}

