package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInWithGooglePage extends Base_Class {
    WebDriver driver;


    public SignInWithGooglePage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='identifierId']")
    public WebElement googleEmailInputField;

    @FindBy(xpath = "//button[contains(@class, 'VfPpkd-LgbsSe-OWXEXe-k8QpJ')]")
    public WebElement nextButton;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement googlePasswordInputField;


    public String loginWithGoogleAccount(String googleAccountEmail, String googleAccountPassword)
    {
        waitForElementToBeVisible(googleEmailInputField, 10).sendKeys(googleAccountEmail);
        nextButton.click();
        waitForElementToBeVisible(googlePasswordInputField, 20).sendKeys(googleAccountPassword);
        nextButton.click();
        String actualTitle = getTitle();
        return actualTitle;

    }

    //Sign in - Google Accounts





}
