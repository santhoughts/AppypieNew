package appypie_Pages;

import base_Component.Base_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class PaymentPage extends Base_Class {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//li[contains(@ng-click, 'setPaymentType')]")
    List<WebElement> paymentMethods;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastName;

    @FindBy(xpath = "//textarea[@placeholder='Address']")
    private WebElement billingAddress;

    @FindBy(xpath = "//input[@placeholder='City']")
    private WebElement billingCity;

    @FindBy(xpath = "//input[@placeholder='Zip / Postal Code']")
    private WebElement postalCode;

    @FindBy(xpath = "//select[@ng-model='billing.state']")
    private WebElement billingState;

    @FindBy(xpath = "//option[contains(@ng-repeat, 'stateList')]")
    private WebElement stateList;

    @FindBy(xpath = "//select[@ng-model='billing.country']")
    private WebElement billingCountry;

    @FindBy(xpath = "//input[@placeholder=Phone']")
    private WebElement billingPhone;

    @FindBy(css = ".continue-btn.purchaseBtn.ng-scope")
    private WebElement completeSecurePaymentButton;


    // PaymentMethods selection  Stripe / PayPal

    public SandBoxPaymentPage paymentMethodsSelection(String paymentType) {

        // Assuming paymentMethod & Element
        String selectedPayment = "";
        WebElement selectedMethod = null;

        // Find pre-selected payment method
        for (WebElement method : paymentMethods) {
            String className = method.getAttribute("ng-class");
            if (className.contains("current")) {
                selectedMethod = method;
                selectedPayment = extractPaymentName(method);
                System.out.println("Pre-selected payment method : " + selectedPayment);
                break;
            }
        }

        // Switch payment method if needed
        if (!selectedPayment.equals(paymentType)) {
            boolean found = false;
            for (WebElement method : paymentMethods) {
                String methodName = extractPaymentName(method);
                if (methodName.equals(paymentType)) {
                    System.out.println("switching to " + paymentType + "-----");
                    method.click();
                    found = true;
                    break;
                }
            }

            if (!found)
            {
                System.out.println("Invalid payment method entered!");
            }
        }
            // payment method is already selected
            else
            {
                System.out.println("Payment method already selected");
            }

            // Filling all the details based on the paymentType selection
            if (paymentType.equals("paypal"))
            {
                fillPayPalPaymentDetails("Rakesh", "Yadav", "noida","Alabama",
                        "Delhi", "11464", "97647647648");
            }

            else if (paymentType.equals("stripe"))
            {
                // fillStripePaymentDetails
            }

        // click on complete secure button for payment page
            completeSecurePaymentButton.click();
            SandBoxPaymentPage sandBoxPaymentPage = new SandBoxPaymentPage(driver);
            return sandBoxPaymentPage;
    }


    // function to extract payment name from element
    public static String extractPaymentName(WebElement method) {
        String text = method.getText().toLowerCase();
        if (text.contains("stripemethod")) return "stripemethod";
        if (text.contains("paypalmethod")) return "paypalmethod";
        return "unknown";
    }


    // Fill PayPal Details
    public void fillPayPalPaymentDetails(String name, String titleName, String address,
                                         String state, String cityName, String pincode, String phone)
    {
        System.out.println("Filling paypal Payment details ");
        waitForElementToBeVisible(firstName,10);
        firstName.sendKeys(name);
        lastName.sendKeys(titleName);
        billingAddress.sendKeys(address);
        Select selectCity = new Select(billingState);
        waitForElementToBeVisible(stateList, 10);
        selectCity.selectByVisibleText(state);
        billingCity.sendKeys(cityName);
        postalCode.sendKeys(pincode);
        billingPhone.sendKeys(phone);
        System.out.println("PayPal payment details filled!");

    }

    // Fill Stripe Details
    


}
