package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeSuite;

public abstract class DataPageTestBase {

    @BeforeSuite
    public void beforeSuite() {
        Configuration.browser = "chrome";
    }


    public int getSliderPosition(int width, SelenideElement slider, int position) {

        int current = Integer.parseInt(slider.getText());
        if (position < current){
            position -= 1;
        }
        int xOffset = width * (position - current) / 100;
        return xOffset;
    }


}
