package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

        public static ExtentReports getObjectReport() {

            String path = System.getProperty("user.dir") + "\\reports\\index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("Appypie automation test report");
            reporter.config().setDocumentTitle("Test Results");

            // Main extent report
            ExtentReports extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Sanjeev yadav", "Software tester");
            return extent;
        }
}
