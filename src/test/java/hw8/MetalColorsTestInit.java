package hw8;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.testng.annotations.BeforeSuite;
import site.JDISite;

@SuppressWarnings("unchecked")
public class MetalColorsTestInit extends TestNGBase {
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        WebSite.init(JDISite.class);
    }
}
