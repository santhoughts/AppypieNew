package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends Base_Class {
    WebDriver driver;

    // We create the constructor class for initialization
    // (If we execute this class first one constructor is executed)
    public LandingPage(WebDriver driver) {

            super(driver);
            this.driver = driver;

        }

    //Elements
    @FindBy(xpath = "//a[@class='signup-btn-top']")
    private WebElement loginButton;

    @FindBy(css = ".signup-btn-top.bord")
    private WebElement joinButton;


    // for login page
    public LoginPage goToLoginPage()
    {
        loginButton.click();
        LoginPage loginPage= new LoginPage(driver);
        return loginPage;

    }

    // For SignUp
    public SignUpPage goToSignUpPage()
    {
        joinButton.click();
        SignUpPage signUpPage= new SignUpPage(driver);
        return signUpPage;

    }


    public void goTo()
    {
       // driver.get("https://www.appypie.com/");
        driver.get("https://www.appypie.com/");
    }
}
