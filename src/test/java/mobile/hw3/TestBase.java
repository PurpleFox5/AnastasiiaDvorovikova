package mobile.hw3;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static mobile.hw3.Driver.getDriver;

public class TestBase {
    @BeforeSuite(groups = "native")
    public void setNative() throws Exception {
        getDriver(PropertyFile.NATIVE_PROPERTY);
        System.out.println("Driver is prepared");
    }

    @BeforeSuite(groups = "web")
    public void setWeb() throws Exception {
        getDriver(PropertyFile.WEB_PROPERTY);
        System.out.println("Driver prepared");
    }

    @AfterSuite(groups = {"native", "web"})
    public void tearDown() throws Exception {
        getDriver().close();
        System.out.println("Driver is closed");
    }
}
