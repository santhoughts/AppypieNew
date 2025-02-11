package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SandBoxPaymentPage extends Base_Class {
    WebDriver driver;

    public SandBoxPaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    // Pay with PayPal content details
    @FindBy(css = "#content")
    private WebElement contentDetails;

    @FindBy(xpath = "//input[@placeholder='Email or mobile number']")
    private WebElement emailOrMobileNumberInputField;

    @FindBy(css = "#btnNext")
    private WebElement nextButton;

    @FindBy(css = "#password")
    private WebElement passwordInputField;

    @FindBy(css = "#btnLogin")
    private WebElement loginButton;

    @FindBy(xpath = "//button[text()='Save and Continue']")
    private WebElement saveAndContinueButton;

    @FindBy(css = ".styles__SpinnerLoader--1hiEz")
    private WebElement spinnerLoader;

    @FindBy(xpath = "//a[text()='No, I will do it myself']")
    private WebElement noIWillDoItMySelf;


    // Enter PayPal credentials and submit the details
    public EditorHomePage paymentUsingPayPal(String payPalEmailOrNumber, String payPalPassword )
    {
        waitForElementToBeVisible(contentDetails, 10);
        waitForElementToBeVisible(emailOrMobileNumberInputField, 10).sendKeys(payPalEmailOrNumber);
        nextButton.click();
        waitForElementToBeVisible(passwordInputField, 10).sendKeys(payPalPassword);
        loginButton.click();
        waitForElementToBeVisible(spinnerLoader, 20);
        waitForElementToBeDisappear(spinnerLoader, 20);
        waitForElementToBeClickable(noIWillDoItMySelf, 20).click();
        EditorHomePage editorHomePage = new EditorHomePage(driver);
        return editorHomePage;


    }

}
