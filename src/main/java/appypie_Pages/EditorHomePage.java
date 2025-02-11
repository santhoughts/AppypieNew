package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditorHomePage extends Base_Class {
    WebDriver driver;

    public EditorHomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(css = "#iframe")
    private WebElement editorFrame;

    @FindBy(xpath = "//div[@class='app-loader']")
    private WebElement appLoaderOnHomeEditorPage;

    @FindBy(xpath = "(//div[contains(@class, 'btn-group dropend')])[1]")
    private WebElement addFeatureButton;

    @FindBy(css = "#add-feature-panel")
    private WebElement addFeaturePanel;

    @FindBy(xpath = "(//input[@placeholder='Search feature'])[4]")
    private WebElement searchFeature;

    @FindBy(css = ".list-items.ng-scope")
    List<WebElement> featureList;

    @FindBy(css = ".feature-list-items.ng-scope")
    List<WebElement> categoryList;

    @FindBy(xpath = "//li[contains(@class,'list-items-feature')]")
    List<WebElement> addedFeatureList;

    @FindBy(xpath = "//div[@class='feature-list-folder']")
    private WebElement addFolder;


    // Add FEATURE
    // Click on add feature & search the feature and add the feature in the app
    public void addFeature(String featureName) throws InterruptedException {
        waitForElementToBeDisappear(appLoaderOnHomeEditorPage, 20);
        waitForElementToBeVisible(editorFrame, 20);
        addFeatureButton.click();
        waitForElementToBeVisible(addFeaturePanel, 10);
        waitForElementToBeClickable(searchFeature, 20).sendKeys(featureName);
        try {
            for (WebElement feature : featureList) {
                if (feature.getText().trim().equalsIgnoreCase(featureName)) {
                    waitForElementToBeClickable(feature, 20).click();
                    return;
                }
            }

        } catch (Exception e) {
            System.out.println("feature not available " +e.getMessage());

        }


    }
}

