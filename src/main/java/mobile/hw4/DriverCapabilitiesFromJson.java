package mobile.hw4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import mobile.hw3.PropertyFile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverCapabilitiesFromJson {

    private static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle = null;
    private static DesiredCapabilities capabilities;

    // Properties to be read
    private static String AUT; // (mobile) app
    static String SUT; // site under testing
    private static String TEST_PLATFORM;
    private static String DRIVER;
    private static String DEVICE_NAME;
    private static String APP_PACKAGE;
    private static String APP_ACTIVITY;
    private static String UDID;

    private static Capabilities capabilitiesJson;

    private DriverCapabilitiesFromJson() {
        AUT = capabilitiesJson.getAut();
        String t_sut = capabilitiesJson.getSut();
        SUT = t_sut == null ? null : "https://" + t_sut;
        TEST_PLATFORM = capabilitiesJson.getPlatform();
        DRIVER = capabilitiesJson.getDriver();
        DEVICE_NAME = capabilitiesJson.getDeviceName();
        APP_PACKAGE = capabilitiesJson.getAppPackage();
        APP_ACTIVITY = capabilitiesJson.getAppActivity();
        UDID = capabilitiesJson.getUdid();
    }

    static AppiumDriver getDriver(PropertyFile propertyFile) throws Exception {
        Map<String, Capabilities> map = new HashMap<>();
        try (Reader reader = new FileReader("src\\main\\resources\\capabilities.json")) {
            Gson gson = new GsonBuilder().create();
            map = gson.fromJson(reader, new TypeToken<Map<String, Capabilities>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        capabilitiesJson = map.get(propertyFile.toString());

        // Init driver for local Appium server with capabilities have been set
        if (driverSingle == null) {
            prepareDriver();
        }
        return driverSingle;
    }

    public static AppiumDriver getDriver() throws Exception {
        if (capabilitiesJson!=null && driverSingle == null) {
            prepareDriver();
        }
        return driverSingle;
    }

    static WebDriverWait getDriverWait() {
        // Set an object to handle timeouts
        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driverSingle, 20);
        }
        return waitSingle;
    }

    //Initialize driver with appropriate capabilities depending on platform and application
    private static void prepareDriver() throws Exception {
        new DriverCapabilitiesFromJson();
        capabilities = new DesiredCapabilities();
        String browserName;

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case "Android":
                browserName = "Chrome";
                break;
            case "iOS":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
                capabilities.setCapability(MobileCapabilityType.UDID, UDID);
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
        } else if (APP_PACKAGE != null) {
            capabilities.setCapability("appPackage", APP_PACKAGE);
//            capabilities.setCapability("autoLaunch",true);
            capabilities.setCapability("appActivity", APP_PACKAGE + APP_ACTIVITY);
        } else {
            throw new Exception("Unclear type of mobile app");
        }

        // Init driver for local Appium server with capabilities have been set
        switch (TEST_PLATFORM) {
            case "Android":
                driverSingle = new AndroidDriver(new URL(DRIVER), capabilities);
                break;
            case "iOS":
                driverSingle = new IOSDriver(new URL(DRIVER), capabilities);
                break;
            default:
                driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);
                break;
        }
    }
}
