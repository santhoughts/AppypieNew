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
    private WebElement addFeaturePlusIcon;

    @FindBy(css = "#add-feature-panel")
    private WebElement addFeaturePanel;

    @FindBy(xpath = "(//input[@placeholder='Search feature'])[4]")
    private WebElement searchFeature;

    @FindBy(css = ".list-items.ng-scope")
    List<WebElement> featureList;

    @FindBy(css = ".feature-list-items.ng-scope")
    List<WebElement> categoryList;

    @FindBy(xpath = "//li[contains(@class,'list-items-feature')]//input[contains(@id, 'pagename')]")
    List<WebElement> addedFeatureList;


    @FindBy(xpath = "//div[@class='feature-list-folder']")
    private WebElement addFolder;


    // Add FEATURE
    // Click on add feature & search the feature and add the feature in the app
    public void addFeature(String featureName, String featureNameInList) throws InterruptedException {
        waitForElementToBeDisappear(appLoaderOnHomeEditorPage, 20);
        waitForElementToBeVisible(editorFrame, 20);
        addFeaturePlusIcon.click();
        waitForElementToBeVisible(addFeaturePanel, 10);
        waitForElementToBeClickable(searchFeature, 20).sendKeys(featureName);
        try {
            for (WebElement feature : featureList) {
                System.out.println(feature.getText());
                if (feature.getText().trim().equalsIgnoreCase(featureNameInList)) {
                    waitForElementToBeClickable(feature, 20).click();
                    return;
                }
            }

        } catch (Exception e) {
            System.out.println("feature not available " + e.getMessage());

        }


    }

    // Check whether the feature has been added to the app.
    public void openAnyAddedFeature(String addedfeatureName, String featureName, String featureNameList) {
        waitForElementToBeVisible(editorFrame, 20);
        try {
            for (WebElement addedFeature : addedFeatureList) {
                // if the feature already added then click on the feature and perform other action
                System.out.println(addedFeature);
                String addedFeature1 = addedFeature.getAttribute("value").trim();
                System.out.println(addedFeature1);
                if (addedFeature1.equalsIgnoreCase(addedfeatureName)) {
                    System.out.println("Feature all ready added perform action :" + addedfeatureName);
                    waitForElementToBeClickable(addedFeature, 10).click();
                    break;
                } else if (!addedFeature1.equalsIgnoreCase(addedfeatureName)) {
                    addFeature(featureName, featureNameList);
                    System.out.println("Feature successfully added ");
                }
            }

            // call add feature function if not available in the lis

        } catch (Exception e) {
            System.out.println("Feature not added :" + e.getMessage());
        }

    }

    // Get the added feature list
    public String listOfAddedFeatureName() {
        waitForElementToBeVisible(editorFrame, 20);
        try {
            for (WebElement addedFeature : addedFeatureList) {
                System.out.println(addedFeature);
                String addedFeature1 = addedFeature.getAttribute("value").trim();
                return addedFeature1;
            }

        } catch (Exception e) {
            System.out.println("Feature not added :" +e.getMessage());
        }
        return null;
    }
}

