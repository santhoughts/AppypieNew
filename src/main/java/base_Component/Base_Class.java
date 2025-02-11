package base_Component;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Base_Class {

    WebDriver driver;
    Logger log;
    public Base_Class(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.log = LogManager.getLogManager().getLogger(String.valueOf(this.getClass()));
    }

    @FindBy(xpath = "//a[contains(@class, 'appyslim-ui-profile-user')]")
    public WebElement welcomeUserProfile;

    @FindBy(xpath = "//a[@title='Start New Business']")
    public WebElement startNewBusiness;

    @FindBy(xpath = "//h4[text()='Start your business journey online']")
    public WebElement startBusinessJourneyPopUpText;

    @FindBy(css = ".link-text")
    List<WebElement> listOfLinkTextToCreateAppAndWebsite;

    @FindBy(xpath = "//input[@placeholder='Enter business name']")
    public WebElement enterBusinessName;

    @FindBy(xpath = "(//a[text()='Next'])[1]")
    public WebElement nextButton;

    @FindBy(xpath = "//h1[text()='Choose the category that fits best']")
    public WebElement chooseCategoryThatFitsBestText;

    @FindBy (xpath = "//a[@class='ng-binding']")
    List <WebElement> categoriesList;

    @FindBy(xpath = "//h3[text()='Pick a color scheme you like ']")
    public WebElement pickColorElementText;

    




    // provide some wait for element to appear
    public WebElement waitForElementToBeClickable(WebElement webelement, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webelement));
        return element;
    }

    public WebElement waitForElementToBeVisible(WebElement webelement, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(webelement));
        return element;
    }

    public Boolean waitForElementToBeDisappear(WebElement webElement, int seconds)
    {
       WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(seconds));
      Boolean element = wait.until(ExpectedConditions.invisibilityOf(webElement));
      return element;
    }



    // For window scroll
    public void windowScroll()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    //


    // for all window handles---------------------------------------------------------------------------
    public Set<String> getAllWindowHandles()
    {
        return driver.getWindowHandles();
    }

    // for current window handles
    public String getCurrentWindowHandle()
    {
        return driver.getWindowHandle();
    }

    // open new tab and get the window handles
    public String openNewTabAndGetHandle()
    {
        // Get the parent window handle
        String parentWindowId = getCurrentWindowHandle();

        // Open a new tab
        driver.switchTo().newWindow(WindowType.TAB);

        // Get all window handles
        Set<String> handles = getAllWindowHandles();
        Iterator<String> it = handles.iterator();

        String childWindowId = null;

        // Iterate through handles to find the new window handle
        //it.hasNext to check the child window is available or not
        while (it.hasNext()) {
            String handle = it.next();
            if (!handle.equals(parentWindowId)) {
                childWindowId = handle;
                break;
            }
        }

        return childWindowId;
    }

    public void switchToWindow(String handle)
    {
        driver.switchTo().window(handle);
    }

    //----------------------------------------------------------------------------------------------------

    // gor getting the title of any webpage
    public String getTitle()
    {
        String actualTitle = driver.getTitle();
        return actualTitle;
    }

    //Hover the  mouse on welcome Button and click on Start new businees button



    }

