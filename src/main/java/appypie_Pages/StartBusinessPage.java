package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartBusinessPage extends Base_Class {

    WebDriver driver;

    public StartBusinessPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(xpath = "(//input[@id='appName'])[1]")
    private WebElement enterBusinessName;

    @FindBy(css = "#svid")
    private WebElement nextButton;

    @FindBy(xpath = "//a[@class='ng-binding']")
    List<WebElement> categoryList;

    @FindBy(xpath = "//button[contains(@ng-click, 'themeStyle')]")
    List<WebElement> themeList;

    @FindBy(xpath = "//li[@class='ng-scope']")
    List<WebElement> pickColorScheme;

    @FindBy(xpath = "//a[@class='android device']")
    private WebElement androidSimulator;

    @FindBy(xpath = "//a[@class='iphone device']")
    private WebElement iOSSimulator;

    @FindBy(css = ".cssloader")
    private WebElement loader;

    @FindBy(css = "#ai-preview")
    private WebElement aiPreviewApp;

    @FindBy(xpath = "//button[@ng-click='saveAndContinueSimulator()']")
    private WebElement saveAndContinueButton;

    @FindBy(css = ".btn.btn-success.btn-block.trial-btn.ng-scope")
    private WebElement buyNow;


    // Fill all the details like business name, category name, select app theme,
    // verify AI- preview and click on save and continue

    public PaymentPage enterBusinessDetails(String businessName, String categoryName, String themeType,
                                            String colorType) throws InterruptedException {
        waitForElementToBeVisible(enterBusinessName, 10).sendKeys(businessName);
        waitForElementToBeClickable(nextButton, 10).click();

        // click on category on the basis of entered category name
        waitForElementToBeVisible((WebElement) categoryList, 10);
        try {
            for (WebElement category : categoryList) {
                if (category.equals(categoryName))
                    category.click();
            }
        } catch (Exception e) {
            System.out.println("Error in entering categoryName :" + e.getMessage());
        }

        // choose theme  & color scheme
        waitForElementToBeVisible((WebElement) pickColorScheme, 10);

        // Assuming Theme & Element
        String selectedTheme = "";
        WebElement selectedThemeColor = null;

        // Find pre-selected payment method
        for (WebElement theme  : themeList) {
            String className = theme.getAttribute("class");
            if (className.contains("active")) {
                selectedThemeColor = theme;
                selectedTheme = extractThemeName(theme);
                System.out.println("Pre-selected payment method : " + selectedTheme);
                break;
            }
        }

        // Switch theme color if needed
        if (!selectedTheme.equals(themeType)) {
            boolean found = false;
            for (WebElement theme : themeList) {
                String themeName = extractThemeName(theme);
                if (themeName.equals(themeType)) {
                    System.out.println("switching to " + themeType + "-----");
                    theme.click();
                    found = true;
                    break;
                }
            }

            if (!found)
            {
                System.out.println("Invalid theme type!");
            }
        }
        // theme is already selected
        else
        {
            System.out.println("theme type already selected");
        }

        // Select color scheme on the basis of LIGHT theme
        if (themeType.equals("light"))
        {
            for (WebElement colorScheme : pickColorScheme)
            {
                if (colorScheme.equals(colorType))
                    colorScheme.click();

            }
        }

        // Select color scheme on the basis of DARK theme
        else if (themeType.equals("dark"))
        {
            for (WebElement colorScheme : pickColorScheme )
            {
                if (colorScheme.equals(colorType))
                    colorScheme.click();
            }
        }

        // Select device to test the app
        waitForElementToBeVisible(iOSSimulator, 20).click();
        waitForElementToBeDisappear(loader, 20);

        // wait for AI-preview pages and click on save & continue button
        waitForElementToBeVisible(aiPreviewApp, 20);
        waitForElementToBeClickable(saveAndContinueButton, 20).click();

        // perform double click on save&continue button
        Actions actions = new Actions(driver);
        actions.moveToElement(saveAndContinueButton).click().perform();
        Thread.sleep(3000);
        actions.moveToElement(saveAndContinueButton).click().perform();

        // click on buy now button
        waitForElementToBeClickable(buyNow, 20).click();
        PaymentPage paymentPage = new PaymentPage(driver);
        return paymentPage;



    }

    // function to extract theme name from element
    public static String extractThemeName(WebElement theme) {
        String text = theme.getText().toLowerCase();
        if (text.contains("light")) return "light";
        if (text.contains("dark")) return "dark";
        return "unknown";
    }
}
