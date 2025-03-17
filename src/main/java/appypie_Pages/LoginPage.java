package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends Base_Class {
    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;

    }

    @FindBy(xpath = "//input[@placeholder='Enter Email Address / Mobile Number']")
    public WebElement inputFieldForLoginEmailAndNumber;

    @FindBy(id = "password")
    private WebElement inputFieldForLoginPassword;

    @FindBy(css = ".login-btns")
    private WebElement loginButtons;

    @FindBy(css = ".sign-in-google")
    private WebElement signInWithGoogleButtons;

    @FindBy(css = ".VV3oRb.YZVTmd.SmR8")
    public List<WebElement> gmailAccountList;

    @FindBy(xpath = "//div[@class='yAlK0b']")
    public List<WebElement> emailId;

    @FindBy(css = "div>ul>li:nth-last-child(1)")
    public WebElement useAnotherAccount;

    @FindBy(xpath = "//p[text()='Email Address / Phone Number is required']")
    public WebElement emailRequiredMessageForLogin;

    @FindBy(xpath = "//p[text()='Password  is required']")
    public WebElement passwordRequiredMessageForLogin;

    @FindBy(xpath = "//p[contains(text(), 'Your password must be minimum 8 characters')]")
    public WebElement passwordValidationMessageForLogin;

    @FindBy(css = "//p[text()='Please enter a valid email']")
    public WebElement invalidEmailMessageForLogin;

    @FindBy(xpath = "//p[text()='Incorrect username or password']")
    public WebElement incorrectUsernameAndPassword;



   // By emailIdElement = By.ByXPath("//div[@class='yAlK0b']");



    // Login with valid credentials
    public HomePage loginWithValidCredentials(String loginEmail, String loginPassword) throws InterruptedException {
        inputFieldForLoginEmailAndNumber.sendKeys(loginEmail);
        waitForElementToBeVisible(inputFieldForLoginPassword, 10).sendKeys(loginPassword);
        Thread.sleep(10000); // this wait is used to click on captcha manually
        loginButtons.click();
        HomePage homePage = new HomePage(driver);
        return homePage;
    }

    // Sign with Google
    public SignInWithGooglePage clickOnsignInWithEmailButton()
    {
        waitForElementToBeVisible(signInWithGoogleButtons, 10).click();
        SignInWithGooglePage signInWithGooglePage = new SignInWithGooglePage(driver);
        return signInWithGooglePage;

    }

    // Verify error message for Login flow field
    public void validationErrorVerifyForLoginPage(String email, String password) throws InterruptedException {
        waitForElementToBeVisible(inputFieldForLoginEmailAndNumber, 20);
        Thread.sleep(3000);
        if (email != null && !email.isEmpty())
        {
            try {
                waitForElementToBeVisible(inputFieldForLoginPassword,10).sendKeys(email);
            }
            catch (Exception e)
            {
                System.out.println("Error in entering email: " +e.getMessage());
            }
        }

        if (password != null && !password.isEmpty())
        {
            try {
                waitForElementToBeVisible(inputFieldForLoginPassword,10).sendKeys(password);
            }
            catch (Exception e)
            {
                System.out.println("Error in entering Password: " +e.getMessage());
            }
        }
        loginButtons.click();



    }

    // Method to clear email  field
    public void clearEmailField()
    {
        waitForElementToBeVisible(inputFieldForLoginEmailAndNumber,10).clear();
    }

    // Login With Email OTP
    public void loginWithEmailOTP(String loginEmail)
    {
        inputFieldForLoginEmailAndNumber.sendKeys(loginEmail);
    }






}
