package mobile.hw3;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static mobile.hw3.Driver.getDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SimpleNativeTests extends Hooks {

    @Test(description = "Click on button 'Add contact', check elements, check virtual keyboard",
            groups = "native")
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
        if (getDriver() instanceof AndroidDriver) {
            assertTrue(((AndroidDriver) getDriver()).isKeyboardShown());
        } else if (getDriver() instanceof IOSDriver) {
            assertTrue(((IOSDriver) getDriver()).isKeyboardShown());
        }
        System.out.println("Simplest Native test done");
    }

}