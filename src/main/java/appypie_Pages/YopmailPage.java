package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YopmailPage extends Base_Class {
    WebDriver driver;

    public YopmailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    @FindBy(xpath = "//input[@placeholder='Enter your inbox here']")
    private WebElement enterEmailName;

//    @FindBy(css = "#ycptcpt")
//    public WebElement enterEmailName;

    @FindBy(css = ".material-icons-outlined.f24.ycptbutgray")
    private WebElement crossIconButton;

    @FindBy(css = ".material-icons-outlined.f36")
    private WebElement forwardArrowIconButton;

    @FindBy(css = "#refresh")
    private WebElement refreshIconButton;

    @FindBy(css = "#ifmail")
    private WebElement mainFrame;

    @FindBy(xpath = "//div[@id='mail']")
    private WebElement emailContent;

    @FindBy(css = ".ellipsis.nw.b.f18")
    private WebElement verifyAppypieAccount;




    public String getOTPFromYopmail(String emailName) {
        driver.get("https://yopmail.com/en/");
        waitForElementToBeVisible(enterEmailName, 20);
        enterEmailName.sendKeys(emailName);
        forwardArrowIconButton.click();
        driver.switchTo().frame(mainFrame);
        String text = verifyAppypieAccount.getText();
        String[] message = text.split(":");
        String OTP = message[1].trim();
        return OTP;

       // String yopmailEmailText = emailContent.getText();
//        // use regex to match the pattern and extract OTP from the yopmail frame page
//        Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b");
//        Matcher otpMatch = otpPattern.matcher(yopmailEmailText);
//        String OTP = null;
//        if (otpMatch.find())
//        {
//            OTP = otpMatch.group();
//        }
//        System.out.println(OTP);
//        driver.close();
//
//        return OTP;
    }
}

