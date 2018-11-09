package mobile.hw2;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup {

    protected AndroidDriver driver;
    private String deviceName = "c746c9857cf5";
    private String emulator = "emulator-5554";

    protected void prepareNative() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Capabilities
        capabilities.setCapability("deviceName", deviceName);
//        capabilities.setCapability("deviceName", emulator);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("noReset", true);

        //Path to app
        File app = new File("src\\main\\resources\\ContactManager.apk");
        capabilities.setCapability("app", app.getAbsolutePath());

        //Init driver for local Appium server with capabilities have been set
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    protected void prepareAndroidWeb() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Capabilities
        capabilities.setCapability("deviceName", deviceName);
//        capabilities.setCapability("deviceName", emulator);
        capabilities.setCapability("platformName", "Android");

        //Specific web capabilities
        capabilities.setCapability("browserName", "Chrome");

        //Init driver for local Appium server with capabilities have been set
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
    }
}
