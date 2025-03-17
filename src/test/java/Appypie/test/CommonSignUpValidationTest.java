package Appypie.test;

import appypie_Pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;
import utilityPages.WindowHandleUtil;

import java.io.IOException;
import java.util.HashMap;

public class CommonSignUpValidationTest extends BaseTest {

    // validate common signup functionality By using Email & Password
    @Test(dataProvider = "signUpDataProvider")
    public void validateCommonSignupWithEmailPassword(HashMap<String, String> input) throws IOException {

        // for launch the application
        LandingPage landingPage = launchApplication();

        // move to signup page to enter email and password
        SignUpPage signUpPage = landingPage.goToSignUpPage();
        signUpPage.signUpWithValidData(input.get("email"), input.get("password"));

        // Get window handle and move to different tab to extract the otp form the yopmail
        WindowHandleUtil handleUtil = new WindowHandleUtil(driver);
        String childHandle = handleUtil.openNewTabAndGetHandle();
        handleUtil.switchToWindow(childHandle);

        // create instance of yopmail page & call the getOTP method to extract the otp from the yopmail page
        YopmailPage yopmailPage = new YopmailPage(driver);
        String OTP = yopmailPage.getOTPFromYopmail(input.get("emailPrefix"));
        String[] handles = handleUtil.windowHandle();
        String parentWindowID = handles[0];
        String retrieveChildWindowID = handles[1];

        // after extracting otp move to the parent window
        handleUtil.switchToWindow(parentWindowID);
        OTP_VerificationPage otpVerificationPage = new OTP_VerificationPage(driver);
        HomePage homePage = otpVerificationPage.enterVerificationCode(OTP);


    }

    // Use dataprovider to take data from the json file
    @DataProvider
    public Object[][] signUpDataProvider() throws IOException {
        return getDataByIndices("SignUp&LoginCredentials.json", 0);
    }

    // Verify all the required error message which has displayed on the signup page
    @Test
    public void validateCommonSignupRequiredErrorMessage() {
        LandingPage landingPage = new LandingPage(driver);
        SignUpPage signUpPage = landingPage.goToSignUpPage();
        try {
            // verify error message for email field to not enter the email
            signUpPage.validationErrorVerifyForSignupPage("", "");
            String errorMessage = signUpPage.emailRequiredMessage.getText();
            Assert.assertTrue(signUpPage.emailRequiredMessage.isDisplayed(), "Email required error not displayed");

            // Verify error message for not enter password
            signUpPage.validationErrorVerifyForSignupPage("san@yopmail.com", "");
            Assert.assertTrue(signUpPage.passwordRequiredMessage.isDisplayed(), "Password required error no displayed");

            // Verify error message for enter invalid password
            signUpPage.clearEmailField();
            signUpPage.validationErrorVerifyForSignupPage("san@yopmail.com", "Sanjeev");
            Assert.assertTrue(signUpPage.passwordValidationMessage.isDisplayed(), "Password validation error not displayed");


        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Unexpected error occurred: " + e.getMessage();
        }

    }

    // Verify all the required error message which has displayed on the otp verification page
    @Test
    public void validateOTPVerificationRequiredErrorMessage() throws IOException {

        OTP_VerificationPage otpVerificationPage = new OTP_VerificationPage(driver);
        LandingPage landingPage = launchApplication();
        SignUpPage signUpPage = landingPage.goToSignUpPage();
        try {

            // verify error message for not enter otp
            signUpPage.signUpWithValidData("san@yopmail.com", "Test@123");
            otpVerificationPage.validationErrorMessageVerifyForOTPage("");
            Assert.assertTrue(otpVerificationPage.errorMessageForNotEnterOTP.isDisplayed(), "Otp validation error not displayed");

            // get email from the otp page & verify with entered email
            String email = otpVerificationPage.getEmailFromTheOTPPage();
            Assert.assertEquals(email, "san@yopmail.com");

            // Verify error message for enter wrong OTP
            otpVerificationPage.validationErrorMessageVerifyForOTPage("12345678");
            Assert.assertTrue(otpVerificationPage.errorMessageForEnterWrongOTP.isDisplayed(), "Otp validation error not displayed for wrong OTP");

            // Verify the resendOTP is working
            otpVerificationPage.clickOnResendOTP();
            Assert.assertTrue(otpVerificationPage.resendIn.isDisplayed(), "Message for resend In---sec is not displayed");

        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Unexpected error occurred" + e.getMessage();
        }

    }

    //Verify hide and unhidden password
    @Test
    public void verifyViewIconFunctionality() throws IOException {
        LandingPage landingPage = launchApplication();
        SignUpPage signUpPage = landingPage.goToSignUpPage();
        try {
            String[] inputType = signUpPage.verifyHideAndUnhidePasswor("san@yopmail.com", "Test@123");
            Assert.assertEquals(inputType[0], "password");
            Assert.assertEquals(inputType[1], "text");
        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Unexpected erro occured" + e.getMessage();

        }


    }
}
