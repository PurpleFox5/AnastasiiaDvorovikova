package hw2.ex3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RefactorHW1 {

    private WebDriver driver;
    static final int COUNT = 4;
    static List<String> headerSectionsList = new ArrayList<>();
    static List<String> texts = new ArrayList<>();
    static final String MAIN_TEXT = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD" +
            " TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD" +
            " EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN " +
            "REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";

    static {
        headerSectionsList.add("HOME");
        headerSectionsList.add("CONTACT FORM");
        headerSectionsList.add("SERVICE");
        headerSectionsList.add("METALS & COLORS");

        texts.add("To include good practices\nand ideas from successful\nEPAM project");
        texts.add("To be flexible and\ncustomizable");
        texts.add("To be multiplatform");
        texts.add("Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
    }

    @BeforeMethod(alwaysRun = true)
        public void beforeMethod() {
        driver.manage().window().maximize();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        System.out.println(driver.getTitle());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println(System.currentTimeMillis());
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        driver.close();
    }

    @Test
    public void simpleTest() {
        //1. Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        //2. Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        //3. Perform login
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("[id = 'Name']")).sendKeys("epam");
        driver.findElement(By.cssSelector("[id = 'Password']")).sendKeys("1234");
        driver.findElement(By.cssSelector(".login [type = 'submit']")).click();

        //4. Assert User name in the left-top side of screen that user is loggined
        assertEquals(driver.findElement(By.cssSelector(".profile-photo")).getText(), "PITER CHAILOVSKII");

        //5. Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        //6. Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerSections = driver.findElements(By.cssSelector(".m-l8 > li > a"));
        assertEquals(headerSections.size(), COUNT);
        for (WebElement element : headerSections) {
            assertTrue(headerSectionsList.contains(element.getText()));
        }

        //7. Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> elements = driver.findElements(By.cssSelector(".icons-benefit"));
        assertEquals(elements.size(), COUNT);
        assertTrue(elements.stream().allMatch(WebElement::isDisplayed));

        //8. Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> txtElements = driver.findElements(By.cssSelector(".benefit-txt"));
        assertEquals(txtElements.size(), COUNT);
//        for (WebElement element : txtElements) {
//            assertTrue(texts.contains(element.getText()));
//        }

        //9. Assert a text of the main header
        WebElement mainHeader = driver.findElement(By.cssSelector("h3.main-title"));
//        assertEquals(mainHeader.getText(), "EPAM FRAMEWORK WISHES…");
        WebElement mainTxt = driver.findElement(By.cssSelector(".main-txt"));
        assertEquals(mainTxt.getText(), MAIN_TEXT);

        //10. Assert that there is the iframe in the center of page
        WebElement iframe = driver.findElement(By.cssSelector("iframe"));
        assertTrue(iframe.isDisplayed());

        //11. Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame("iframe");
        WebElement logo = driver.findElement(By.cssSelector(".epam-logo"));
        assertTrue(logo.isDisplayed());

        //12. Switch to original window back
        driver.switchTo().parentFrame();

        //13. Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.xpath("/html/body/div/div[2]/main/div[2]/h3[2]"));
        assertEquals(subHeader.getText(), "JDI GITHUB");

        //14. Assert that JDI GITHUB is a link and has a proper URL
        assertEquals(subHeader.findElement(By.cssSelector("h3:nth-child(3) > a")).getAttribute("href"), "https://github.com/epam/JDI");

        //15. Assert that there is Left Section
        assertTrue(driver.findElement(By.cssSelector(".uui-side-bar")).isDisplayed());

        //16. Assert that there is Footer
        assertTrue(driver.findElement(By.cssSelector(".footer-bg")).isDisplayed());

    }


}
