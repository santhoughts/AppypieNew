package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OTP_VerificationPage extends Base_Class {
    WebDriver driver;

    public OTP_VerificationPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@placeholder ='Enter verification code']")
    private WebElement enterOTP;

    @FindBy(xpath = "//span[text()='Resend Code']")
    public WebElement resendOTP;

    @FindBy(xpath = "//span[contains(text(), 'Resend in')]")
    public WebElement resendIn;

    @FindBy(xpath = "//button[@class='login-btns']")
    private WebElement confirmButton;

    @FindBy(xpath = "//p[text()='Please enter a valid OTP']")
    public WebElement errorMessageForNotEnterOTP;

    @FindBy(xpath = "//p[contains(text(), 'Invalid verification code.')]")
    public WebElement errorMessageForEnterWrongOTP;

    @FindBy(xpath = "//p[contains(text(), 'We sent an email with a verification code to')]")
    public WebElement textWithEmailForVerificationCode;



    // For entering verification code
    public HomePage enterVerificationCode(String OTP)
    {
        enterOTP.sendKeys(OTP);
        confirmButton.click();
        HomePage homePage = new HomePage(driver);
        return homePage;

    }

    public void validationErrorMessageVerifyForOTPage(String otp)
    {
        // This block of code executed when otp is available, if not then it directly click on confirm button
        try {
            if (otp != null && !otp.isEmpty())
            {
                waitForElementToBeVisible(enterOTP, 10).sendKeys(otp);
            }
            confirmButton.click();

        } catch (Exception e)
        {
            System.out.println("Error in entering OTP :" +e.getMessage());
        }

    }

    // Click on resend code button
    public void clickOnResendOTP()
    {
        waitForElementToBeVisible(resendOTP, 65).click();
    }

    // get the email from the otp page and verify it
    public String getEmailFromTheOTPPage()
    {
        // Get the email from the verification page to verify the email
        String text = waitForElementToBeVisible(textWithEmailForVerificationCode, 10).getText();
        String[] text1 = text.split("to");
        String email= text1[1].trim();
        return email;
    }

}





