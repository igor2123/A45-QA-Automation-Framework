package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[type='email']")
    private WebElement emailField;

    @FindBy(css = "[type='password']")
    private WebElement passwordField;

    @FindBy(css = "[type='submit']")
    private WebElement submitBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage provideEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmit() {
        submitBtn.click();
        return this;
    }

    public void login() {
        provideEmail("demo@class.com")
                .providePassword("te$t$tudent")
                .clickSubmit();
    }
}


