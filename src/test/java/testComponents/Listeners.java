package testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReportNG;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReportNG.getObjectReport();
    @Override
    public void onTestStart(ITestResult result)
    {
        test = extent.createTest(result.getMethod().getMethodName());// get the test name & create test report for that test
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        test.fail(result.getThrowable());
        String filePath = null;

        // gave live to driver in Listeners using result, because result store all information related to test
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); // add screenshot of failed test

    }

    @Override
    public void onTestSkipped(ITestResult result)
    {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {

    }

    @Override
    public void onFinish(ITestContext context)
    {
        extent.flush();
    }

    @Override
    public void onStart(ITestContext context)
    {

    }



}
