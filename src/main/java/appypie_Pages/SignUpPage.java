package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends Base_Class {
    WebDriver driver;

    public SignUpPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "email")
    public WebElement signupEmailAddress;

    @FindBy(id = "password")
    public WebElement signupPassword;

    @FindBy(css = ".login-btns.hrefCur")
    private WebElement signUpButton;

    @FindBy(xpath = "//p[text()='Email Address / Phone Number is required']")
    public WebElement emailRequiredMessage;

    @FindBy(xpath = "//p[text()='Password  is required']")
    public WebElement passwordRequiredMessage;

    @FindBy(css = "//p[text()='Please enter a valid email']")
    public WebElement invalidEmailMessage;

    @FindBy(xpath = "//p[contains(text(), 'Your password must be minimum 8 characters')]")
    public WebElement passwordValidationMessage;

    @FindBy(xpath = "(//input[@type='text'])[2]")
    public WebElement passwordVisible;

    @FindBy(css = "input[type='password']")
    public WebElement passwordHidden;

    @FindBy(xpath = "(//img[contains(@class, 'view-pass icon')])[1]")
    public WebElement viewIcon;




    // SignUp with correct email & password
    public OTP_VerificationPage signUpWithValidData(String email, String password)
    {
        signupEmailAddress.sendKeys(email);
        signupPassword.sendKeys(password);
        signUpButton.click();
        OTP_VerificationPage otpVerificationPage = new OTP_VerificationPage(driver);
        return otpVerificationPage;

    }

    // Verify Error message for signup Flow field
    public void validationErrorVerifyForSignupPage(String email, String password) throws InterruptedException {
        waitForElementToBeVisible(signupEmailAddress, 20);
        Thread.sleep(3000);
        if (email != null && !email.isEmpty())
        {
            try {
                waitForElementToBeVisible(signupEmailAddress,10).sendKeys(email);
            }
            catch (Exception e)
            {
                System.out.println("Error in entering email: " +e.getMessage());
            }
        }

        if (password != null && !password.isEmpty())
        {
            try {
                waitForElementToBeVisible(signupPassword,10).sendKeys(password);
            }
            catch (Exception e)
            {
                System.out.println("Error in entering Password: " +e.getMessage());
            }
        }
        signUpButton.click();



    }

    // Method to clear email  field
    public void clearEmailField()
    {
        waitForElementToBeVisible(signupEmailAddress,10).clear();
    }

    // Method to view the password
    public String[] verifyHideAndUnhidePasswor(String email, String password)
    {
        // get the attribute value and verify
        waitForElementToBeVisible(signupEmailAddress,10).sendKeys(email);
        waitForElementToBeVisible(signupPassword,10).sendKeys(password);
        String inputTypeBeforeClick = waitForElementToBeVisible(passwordHidden, 5).getAttribute("type");
        viewIcon.click();
        String inputTypeAfterClick = waitForElementToBeVisible(passwordVisible, 5).getAttribute("type");
        return new String[] {inputTypeBeforeClick, inputTypeAfterClick};

    }




}
