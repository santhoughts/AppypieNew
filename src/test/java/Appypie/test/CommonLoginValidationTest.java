package Appypie.test;

import appypie_Pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import testComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Driver;
import java.util.HashMap;

public class CommonLoginValidationTest extends BaseTest {


    // Login with Email & Password
    @Test(dataProvider = "loginDataProvider")
    public void LoginSuccessfullValidation(HashMap<String, String> input) throws IOException, InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage.goToLoginPage();
        HomePage homePage = loginPage.loginWithValidCredentials(input.get("loginEmail"), input.get("loginPassword"));


    }

    // provide data for login
    @DataProvider
    public Object[][] loginDataProvider() throws IOException {
        return getDataByIndices("SignUp&LoginCredentials.json", 1);
    }


    //  Sign In using google
    @Test(dataProvider = "googleLoginCredentials")
    public void signInWithGoogle(HashMap<String, String> input) throws IOException {

        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage.goToLoginPage();
        SignInWithGooglePage signInWithGooglePage = loginPage.clickOnsignInWithEmailButton();
        signInWithGooglePage.loginWithGoogleAccount(input.get("googleAccountEmail"),
                input.get("googleAccountPassword"));
        String actualTitle = signInWithGooglePage.getTitle();
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, "Sign in - Google Accounts",
                "actual title not match with expected");
        HomePage homePage = new HomePage(driver);
        String actualHomePageTitle = homePage.getHomepageTitleName();
        Assert.assertEquals(actualHomePageTitle, "Personal Information | Appy Pie", "Homepage title not match");
    }

    // provide data for login
    @DataProvider
    public Object[][] googleLoginCredentials() throws IOException {
        return getDataByIndices("SignUp&LoginCredentials.json", 3);
    }


    // Verify all the required error message which has displayed on the login page
    @Test
    public void validateCommonLoginRequiredErrorMessage()
    {
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage.goToLoginPage();
        try {
            // verify error message for email field to not enter the email
            loginPage.validationErrorVerifyForLoginPage("", "");
            String errorMessage = loginPage.emailRequiredMessageForLogin.getText();
            Assert.assertTrue(loginPage.emailRequiredMessageForLogin.isDisplayed(), "Email required error not displayed");

            // Verify error message for not enter password
            loginPage.validationErrorVerifyForLoginPage("san@yopmail.com", "");
            Assert.assertTrue(loginPage.passwordRequiredMessageForLogin.isDisplayed(), "Password required error no displayed");

            // Verify error message for enter invalid password
            loginPage.clearEmailField();
            loginPage.validationErrorVerifyForLoginPage("san@yopmail.com", "Sanjeev");
            Assert.assertTrue(loginPage.incorrectUsernameAndPassword.isDisplayed(), "Password validation error not displayed");


        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Unexpected error occurred: " + e.getMessage();
        }

    }



}


