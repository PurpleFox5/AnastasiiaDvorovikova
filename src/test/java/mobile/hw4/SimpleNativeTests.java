package mobile.hw4;

import io.appium.java_client.android.AndroidDriver;
import mobile.hw4.PropertyFile;
import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import static mobile.hw4.DriverCapabilitiesFromJson.getDriver;
//import static mobile.hw4.Driver.getDriver;

@Test
public class SimpleNativeTests {

    @BeforeSuite
    public void setNative() throws Exception {
//        getDriver(PropertyFile.NATIVE_PROPERTY);
        getDriver(PropertyFile.ANDROID_NATIVE); //Capabilities from JSON File
        System.out.println("Driver is prepared");
    }

    @AfterSuite
    public void tearDown() throws Exception {
        getDriver().quit();
        System.out.println("Driver is closed");
    }


    @Test(description = "Click on button 'Add contact', check elements, check virtual keyboard")
    public void simplestTest() throws Exception {
        String app_package_name = "com.example.android.contactmanager:id/";

        getDriver().findElement(By.id(app_package_name + "addContactButton")).click();

        //Check Target Account, Contact Name, Contact phone appears
        assertEquals(getDriver().findElementByAccessibilityId("Target Account").getText(), "Target Account");
        assertTrue(getDriver().findElement(By.id(app_package_name + "accountSpinner")).isDisplayed());
        assertEquals(getDriver().findElementByAccessibilityId("Contact Name").getText(), "Contact Name");
        assertTrue(getDriver().findElement(By.id(app_package_name + "contactNameEditText")).isDisplayed());
        assertEquals(getDriver().findElementByAccessibilityId("Contact Phone").getText(), "Contact Phone");
        assertTrue(getDriver().findElement(By.id(app_package_name + "contactPhoneEditText")).isDisplayed());
        assertEquals(getDriver().findElementByAccessibilityId("Contact Email").getText(), "Contact Email");
        assertTrue(getDriver().findElement(By.id(app_package_name + "contactEmailEditText")).isDisplayed());

        //Check virtual keyboard appears
        assertTrue(((AndroidDriver) getDriver()).isKeyboardShown());

        System.out.println("Simplest Native test done");
    }
}

