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
        dataPage.moveSliders(0, 100);

        //7. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(0);
        dataPage.checkLogs(100);

        //8. Using drag-and-drop set Range sliders. left sliders - the most left position,
        // right slider - the most left position.
        dataPage.moveSliders(100,100);

        //9. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(100);
        dataPage.checkLogs(100);

        //10.Using drag-and-drop set Range sliders. left sliders - the most rigth position,
        // right slider - the most rigth position.
        dataPage.moveSliders(0,0);

        //11. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(0);
        dataPage.checkLogs(0);

        //12. Using drag-and-drop set Range sliders.
        dataPage.moveSliders(30,70);

        //13. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(30);
        dataPage.checkLogs(70);
    }

}
