package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Base_Class {
    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = ".ObDc3.ZYOIke")
    public WebElement twoStepVerifcationText;

    @FindBy(xpath = "//div[contains(@class, 'card-col-build')]")
    public WebElement manageBuildTab;

    @FindBy(xpath = "//div[contains(@class, 'card-col-build')] //a[contains(@class, 'link-tex')]")
    public WebElement getStartedOrManageButton;



    // get title name of homepage
    public String getHomepageTitleName()
    {
        waitForElementToBeDisappear(twoStepVerifcationText, 20);
        waitForElementToBeVisible(manageBuildTab, 20);
        String actualTitleOfHomePage = getTitle();
        return actualTitleOfHomePage;
    }

    public BusinessDashboard clickOnGetStartedOrManageOnHomePage()
    {
        waitForElementToBeVisible(getStartedOrManageButton, 20).click();
        BusinessDashboard businessDashboard = new BusinessDashboard(driver);
        return businessDashboard;


    }


}
