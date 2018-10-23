package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeSuite;

public abstract class DataPageTestBase {

    @BeforeSuite
    public void beforeSuite() {
        Configuration.browser = "chrome";
    }


    public int getSliderPosition(SelenideElement sliderTrack, SelenideElement slider, int position) {
        int xOffset = 0;
        if (position >= 0 && position <= 100) {
            int step = sliderTrack.getSize().width / 4;
            int sliderCenterPx = (int) (Integer.parseInt(slider.getCssValue("left")
                    .replaceAll("px", "")) + slider.getSize().width / 2);
            int currectPosition = sliderCenterPx / step + 1;
            xOffset = (int)((position - currectPosition) * step);

        }
        return xOffset;
    }


}
