package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BusinessDashboard extends Base_Class {
    WebDriver driver;

    public BusinessDashboard(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = "#searchAppStr")
    public WebElement searchByBusinessNameAndBID;

    @FindBy(css = ".col.m-b-15")
    List<WebElement> manageTabList;


    @FindBy(xpath = "(//a[@class='link-text ng-scope'])[1]")
    public WebElement manageAppButton;

    @FindBy(xpath = "(//a[@class='link-text ng-scope'])[2]")
    public WebElement manageWebsiteButton;

    @FindBy(xpath = "(//i[@class='icon-edit'])[2]")
    public WebElement editButton;


    @FindBy(css = "#loading-image")
    public WebElement loaderImage;

    @FindBy(css = "#loading-image")
    private WebElement loadingImage;

    @FindBy(xpath = "//body/iframe[1]")
    private WebElement frame;

    @FindBy(xpath = "//li[@class='dropdown']")
    private WebElement welcomeButton;

    @FindBy(xpath = "//a[@title='Start New Business']")
    private WebElement startNewBusinessButton;

    @FindBy(xpath = "(//a[@class='link-text'])[1]")
    private WebElement createNewAppButton;







    //  search app using BID and Manage App
    public void manageAPP(String BID) throws InterruptedException {
        waitForElementToBeVisible(searchByBusinessNameAndBID, 20).sendKeys(BID);
        searchByBusinessNameAndBID.sendKeys(Keys.ENTER);
        waitForElementToBeVisible(manageAppButton, 10).click();
        waitForElementToBeDisappear(loaderImage, 20);
        Thread.sleep(3000);
        waitForElementToBeClickable(editButton, 20).click();
        waitForElementToBeDisappear(loaderImage, 20);
        EditorHomePage homePage = new EditorHomePage(driver);

    }

    // Search app using BID and Manage Website
    public void manageWebsite(String BID)
    {
        waitForElementToBeVisible(searchByBusinessNameAndBID, 20).sendKeys(BID);
        searchByBusinessNameAndBID.sendKeys(Keys.ENTER);
        waitForElementToBeVisible(manageWebsiteButton, 10).click();
        waitForElementToBeVisible(editButton, 20).click();
        waitForElementToBeDisappear(loaderImage, 20);
        EditorHomePage homePage = new EditorHomePage(driver);

    }

   //  By click on the start new business and click on create new app on pop up screen
    public StartBusinessPage clickOnStartNewBusinessAndOnCreateNewApp()
    {
        waitForElementToBeVisible(welcomeButton, 10);
        Actions actions = new Actions(driver);
        actions.moveToElement(welcomeButton).perform();
        waitForElementToBeClickable(startNewBusinessButton, 10).click();
        waitForElementToBeVisible(createNewAppButton, 10).click();
        StartBusinessPage  startBusinessPage = new StartBusinessPage(driver);
        return startBusinessPage;
    }





}
