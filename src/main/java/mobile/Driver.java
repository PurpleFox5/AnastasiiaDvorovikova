package mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class Driver {

    private static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle = null;
    private static DesiredCapabilities capabilities;

    // Properties to be read
    public static String AUT; // (mobile) app
    public static String SUT; // site under testing
    private static String TEST_PLATFORM;
    private static String DRIVER;

    private static Properties currentProps = new Properties();

    private Driver() {
        AUT = currentProps.getProperty("aut");
        String t_sut = currentProps.getProperty("sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = currentProps.getProperty("platform");
        DRIVER = currentProps.getProperty("driver");
    }

    public static AppiumDriver getDriver(PropertyFile propertyFile) throws Exception {
        try (FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + propertyFile)) {
            currentProps.load(in);
        }
        // Init driver for local Appium server with capabilities have been set
        if (driverSingle == null) {
            prepareDriver();
        }
        return driverSingle;
    }

    public static AppiumDriver getDriver() throws Exception {
        if (!currentProps.isEmpty() && driverSingle == null) {
            prepareDriver();
        }
        return driverSingle;
    }

    public static WebDriverWait getDriverWait() throws Exception {
        // Set an object to handle timeouts
        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driverSingle, 10);
        }
        return waitSingle;
    }

    //Initialize driver with appropriate capabilities depending on platform and application
    private static void prepareDriver() throws Exception {
        new Driver();
        capabilities = new DesiredCapabilities();
        String browserName;

        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
                // default Android emulator
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);
        // Setup type of application: mobile, web (or hybrid)
        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        } else {
            throw new Exception("Unclear type of mobile app");
        }
        // Init driver for local Appium server with capabilities have been set
        driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);
    }
}
