package hw4;

import base.PageTestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.DataPage;
import pageObjects.HomePage;

import static com.codeborne.selenide.Selenide.page;
import static enums.Titles.DATA_PAGE;
import static enums.Titles.HOME_PAGE;
import static enums.Users.PITER_CHAILOVSKII;

@Feature("HomeWork5 tests")
@Story("Data Page Testing")
@Listeners(AllureListener.class)
public class DataPageTest extends PageTestBase {

    private DataPage dataPage;
    private HomePage homePage;

    @BeforeClass
    public void beforeClass() {
        dataPage = page(DataPage.class);
        homePage = page(HomePage.class);
    }

    @Test
    public void dataPageSliderCheck() {
        //1. Open test site by URL
        homePage.openPage();

        //2. Assert Browser title
        homePage.checkTitle(HOME_PAGE);

        //3. Perform login
        homePage.login(PITER_CHAILOVSKII);

        //4. Assert User name in the left-top side of screen that user is loggined
        homePage.checkUserName(PITER_CHAILOVSKII);

        //5. Open through the header menu Service -> Dates Page
        homePage.clickHeaderService();
        homePage.openDataPage();
        dataPage.checkTitle(DATA_PAGE);

        //6. Using drag-and-drop set Range sliders. left sliders - the most left position,
        // right slider - the most right position
        dataPage.moveSliders(0, 100);

        //7. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(0, 100);

        //8. Using drag-and-drop set Range sliders. left sliders - the most left position,
        // right slider - the most left position.
        dataPage.moveSliders(0, 0);

        //9. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(0, 0);

        //10.Using drag-and-drop set Range sliders. left sliders - the most rigth position,
        // right slider - the most rigth position.
        dataPage.moveSliders(100, 100);

        //11. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(100, 100);

        //12. Using drag-and-drop set Range sliders.
        dataPage.moveSliders(30, 70);

        //13. Assert that for "From" and "To" sliders there are logs rows with corresponding values
        dataPage.checkLogs(30, 70);
    }

}
