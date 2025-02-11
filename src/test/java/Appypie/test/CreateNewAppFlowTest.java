package Appypie.test;

import appypie_Pages.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;

public class CreateNewAppFlowTest extends BaseTest {

//Create New APP, Using all the basic details
    @Test(dataProvider = "getPaymentCredentialsAndBusinessRequirement")
    public void createNewAPP(HashMap<String, String> Input) throws IOException, InterruptedException {

        try {
            LandingPage landingPage = new LandingPage(driver);
            // Goto login page through click on login button
            LoginPage loginPage = landingPage.goToLoginPage();

            // enter login credentials & login into application
            HomePage homePage = loginPage.loginWithValidCredentials(Input.get("loginEmail"),
                    Input.get("loginPassword"));

            // click on get started on move on business dashboard
            BusinessDashboard businessDashboard = homePage.clickOnGetStartedOrManageOnHomePage();

            // create app through profile section
            StartBusinessPage startBusinessPage = businessDashboard.clickOnStartNewBusinessAndOnCreateNewApp();

            // enter all the details like business name, choose category, choose theme
            PaymentPage paymentPage = startBusinessPage.enterBusinessDetails(Input.get("businessName"), Input.get("CategoryName"),
                    Input.get("themeType"), Input.get("colorType"));

            // Select paymentType and move on sandbox page to make payment
            SandBoxPaymentPage sandBoxPaymentPage = paymentPage.paymentMethodsSelection(Input.get("paymentType"));

            // Make payment using selection of payment type
            EditorHomePage editorHomePage = sandBoxPaymentPage.paymentUsingPayPal(Input.get("payPalEmailOrNumber"), Input.get("payPalPassword"));

            System.out.println("Test passed successfully");

        } catch (Exception e) {
            System.err.println("Test failed due to :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }
    
    @DataProvider
    public Object[][] getPaymentCredentialsAndBusinessRequirement() throws IOException {
        return getDataByIndices("PaymentCredentials.json", 0);
    }

}


