package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.List;

public abstract class ServicePageTestBase {

    protected List<String> menuServiceList = new ArrayList<>();
    {
        menuServiceList.add("Support");
        menuServiceList.add("Dates");
        menuServiceList.add("Complex Table");
        menuServiceList.add("Simple Table");
        menuServiceList.add("User Table");
        menuServiceList.add("Table with pages");
        menuServiceList.add("Different elements");
        menuServiceList.add("Performance");
    }

    @BeforeSuite
    public void beforeSuite() {
        Configuration.browser = "chrome";
    }

}
