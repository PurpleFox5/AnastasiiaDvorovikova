package runners;

import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

@CucumberOptions(features = "src/test/java/hw6", tags = {"not @ignore"}, glue = {"pageObjects", "entries", "configurations"})
public class CucumberHWRunner extends AbstractTestNGCucumberTests {

    @AfterMethod
    public void afterClass() {
        closeWebDriver();
    }

    @BeforeSuite
    public void beforeSuite() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

}
