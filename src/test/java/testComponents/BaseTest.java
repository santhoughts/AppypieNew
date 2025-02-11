package testComponents;

import appypie_Pages.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

public class BaseTest {

    public WebDriver driver;


    public void intializeDriver() throws IOException {

        // Properties class is used to extract some value from the GlobalData properties
        // for localSystem path we use System.getProperty("user.dir")
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream
                (System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalData.properties");
        prop.load(fis);

        // This is used for Chrome browser from global properties
        //String browserName=prop.getProperty("browser");

        // This line of code (Browser ternary) is used for set parameter on run time from the command line
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
                : prop.getProperty("browser");

        //Chrome browser
        // Use chrome options for headless mode
        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-cache");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900)); // full screen

        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Tools\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            // Edge

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    // Initialize driver & Launch application
    @BeforeTest(alwaysRun = true)
    public LandingPage launchApplication() throws IOException
    {
        intializeDriver();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    // Read jason data form filePath-----------------------------------------------------------
    // change the file name if we want to insert the data in
    private static final String [] DATA_FILE_PATH = {
            System.getProperty("user.dir") + "\\src\\test\\Data\\SignUp&LoginCredentials.json",
            System.getProperty("user.dir") + "\\src\\test\\Data\\LoginData.json",
            System.getProperty("user.dir") + "\\src\\test\\Data\\PaymentCredentials.json"

        };

    // Reads JSON data from all available files and associates data with their file paths
    public static List<Map<String, String>> getTestData() throws IOException {
        List<Map<String, String>> allFile = new ArrayList<>();

        for (String filePath : DATA_FILE_PATH)
        {
            File file = new File(filePath);
            if (file.exists())
            {
                System.out.println("reading test data from file :" +filePath);
                String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
                ObjectMapper mapper = new ObjectMapper();

                // Read data and store with an extra key indicating source file
                List<HashMap<String, String>> fileData =  mapper.readValue(jsonContent,
                        new TypeReference<List<HashMap<String, String>>>() {});

                for (HashMap<String, String> entry : fileData)
                {
                    entry.put("source_file", filePath); // Track which file this data came from
                    allFile.add(entry);

                }
            }
        }

        if (allFile.isEmpty())
        {
            throw new IOException("No valid test data found in the specified path.");
        }

        return allFile;
    }


    // Fetch specific test data by indices, including the source file
    public static Object[][] getDataByIndices(String fileNameFilter, int... indices) throws IOException {
        List<Map<String, String>> alldata = getTestData();

        // filter data by file name
       List<Map<String, String>> filteredData =
               alldata.stream().filter(data-> data.get("source_file").contains(fileNameFilter)).toList();

        // Convert filtered data into Object[][] for DataProvider
        Object[][] result = new Object[indices.length][1];
        for (int i = 0; i < indices.length; i++) {
            int index = indices[i];
            if (index >= 0 && index < filteredData.size()) {
                result[i][0] = filteredData.get(index);
            } else {
                System.out.println("Warning: Index " + index + " is out of bounds.");
            }
        }
        return result;

        }


//----------------------------------------------------------------------------------------------------------

    // for driver close
    //@AfterTest(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();
    }

    // Taking Screenshot
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source =ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

    }


    }

