package hw4;

import base.DataPageTestBase;
import enums.Users;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.DataPage;

import static com.codeborne.selenide.Selenide.page;

public class DataPageTest extends DataPageTestBase {

    private DataPage dataPage;

    @BeforeClass
    public void beforeClass() {
        dataPage = page(DataPage.class);
    }

    @Test
    public void dataPageSliderCheck() {
        //1. Open test site by URL
        dataPage.openPage();

        //2. Assert Browser title
        dataPage.checkTitle();

        //3. Perform login
        dataPage.login(Users.PITER_SHAILOVSKII);

        //4. Assert User name in the left-top side of screen that user is loggined
        dataPage.checkUserName();

        //5. Open through the header menu Service -> Dates Page
        dataPage.openPageDifferentElements();

        //6. Using drag-and-drop set Range sliders. left sliders - the most left position,
        // right slider - the most right position
        dataPage.moveLeftSlider(0);
        dataPage.moveRightSliders(100);

        //7. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(0, 100);

        //8. Using drag-and-drop set Range sliders. left sliders - the most left position,
        // right slider - the most left position.
        dataPage.moveLeftSlider(100);
        dataPage.moveRightSliders(100);

        //9. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(100, 100);

        //10.Using drag-and-drop set Range sliders. left sliders - the most rigth position,
        // right slider - the most rigth position.
        dataPage.moveLeftSlider(0);
        dataPage.moveRightSliders(0);

        //11. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(0, 0);

        //12. Using drag-and-drop set Range sliders.
        dataPage.moveRightSliders(70);
        dataPage.moveLeftSlider(30);

        //13. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(30, 70);
    }

}
