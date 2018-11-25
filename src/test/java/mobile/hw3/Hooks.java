package mobile.hw3;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static mobile.hw3.Driver.getDriver;
import static mobile.hw3.PropertyFile.NATIVE_PROPERTY;
import static mobile.hw3.PropertyFile.WEB_PROPERTY;

public class Hooks {

    @BeforeClass(groups = "native")
    public void setNative() throws Exception {
        getDriver(NATIVE_PROPERTY);
        System.out.println("Driver is prepared");
    }

    @BeforeClass(groups = "web")
    public void setWeb() throws Exception {
        getDriver(WEB_PROPERTY);
        System.out.println("Driver is prepared");
    }

    @AfterClass(groups = {"native", "web"})
    public void tearDown() throws Exception {
        getDriver().quit();
        System.out.println("Driver is closed");
    }

}