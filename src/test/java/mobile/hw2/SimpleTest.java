package mobile.hw2;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SimpleTest extends DriverSetup {

    //Prepare driver to run test(s)
    @BeforeClass
    public void setUp() throws Exception {
        prepareNative();
//        prepareAndroidWeb();
    }

    //Close driver on all tests completion
    @AfterClass
    public void tearDown() {
        driver.resetApp();
    }

    @Test(description = "Native Test")
    public void simplestTest() {
        By showInvisible = By.xpath("//android.widget.CheckBox[@content-desc=\"Show Invisible Contacts (Only)\"]");
        driver.findElement(showInvisible).click();

        driver.findElement(By.className("android.widget.CheckBox")).click();

        String app_package_name = "com.example.android.contactmanager:id/";
        By add_btn = By.id(app_package_name + "addContactButton");
        driver.findElement(add_btn).click();

        driver.findElement(By.id(app_package_name+"contactNameEditText")).sendKeys("New contact");
        driver.findElementByAccessibilityId("Save").click();

        System.out.println("Simplest Appium test done");
    }

    @Test(description = "Open website")
    public void webTest() throws InterruptedException {
        driver.get("http://iana.org");
        Thread.sleep(5000);
        System.out.println("Site opening done");
    }

}
