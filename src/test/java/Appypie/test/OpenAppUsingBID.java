package Appypie.test;

import appypie_Pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;

public class OpenAppUsingBID extends BaseTest {


    @Test(dataProvider = "googleLoginCredentials")
    public void openApp(HashMap<String, String> Input) {
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage.goToLoginPage();
        loginPage.clickOnsignInWithEmailButton();
        SignInWithGooglePage signInWithGooglePage = new SignInWithGooglePage(driver);
        try {
            signInWithGooglePage.loginWithGoogleAccount(Input.get("googleAccountEmail"), Input.get("googleAccountPassword"));
            HomePage homePage = new HomePage(driver);
           String actualTitle = homePage.getHomepageTitleName();
           Assert.assertEquals(actualTitle, "Personal Information | Appy Pie", "actual title not match with expected");
            homePage.clickOnGetStartedOrManageOnHomePage();
            BusinessDashboard businessDashboard = new BusinessDashboard(driver);
            businessDashboard.manageAPP(Input.get("BID"));
        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Unexpected error occured " + e.getMessage();

        }
    }

    @DataProvider
    public Object[][] googleLoginCredentials() throws IOException {
        return getDataByIndices("SignUp&LoginCredentials.json", 3);
    }

    // add feature & save the application
    @Test(dependsOnMethods = "openApp", dataProvider = "featureName")
    public void addFeatureInApp(HashMap<String, String> Input) throws InterruptedException {
        EditorHomePage editorHomePage = new EditorHomePage(driver);
        editorHomePage.addFeature(Input.get("featureName"), Input.get("featureNameInList"));
         String addedFeature = editorHomePage.listOfAddedFeatureName();
         Assert.assertEquals(addedFeature,"Form Builder");

    }

    // Provide feature name for the test
    @DataProvider
    public Object[][] featureName() throws IOException {
        return getDataByIndices("SignUp&LoginCredentials.json", 4);


    }

    // Verify the added feature, if not then add the feature and open the feature
    @Test(dependsOnMethods = "openApp", dataProvider = "featureName")
    public void openAddedFeature(HashMap<String, String> Input)
    {
        // create instance
        EditorHomePage editorHomePage = new EditorHomePage(driver);

        // open added feature, if not then add and open the app
        editorHomePage.openAnyAddedFeature(Input.get("featureName"),Input.get("addedfeatureName"), Input.get("featureNameInList"));

    }
}
