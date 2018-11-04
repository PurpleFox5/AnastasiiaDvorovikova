package hw8;

import org.testng.annotations.Test;

import static site.JDISite.homePage;
import static site.JDISite.login;
import static site.JDISite.metalColorPage;

public class MetalColorsFormTest extends MetalColorsTestInit {

    @Test
    public void metalColorsTest() {
        homePage.open();
        login();
        homePage.checkOpened();
        homePage.checkUserName();
        homePage.openMetalColorsPage();

    }

}
