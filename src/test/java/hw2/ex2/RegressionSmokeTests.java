package hw2.ex2;

import base.TestBaseHW;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegressionSmokeTests extends TestBaseHW {

    @Test(groups = "Regression")
    public void test1InGroupSmoke() {
        WebDriver driver = new ChromeDriver();

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
        assertEquals(headerSections.size(), 4);
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
        for (int i = 0; i < COUNT; i++){
            assertEquals(txtElements.get(i).getText(), (texts.get(i)));
        }

        //9. Assert a text of the main header
        WebElement mainHeader = driver.findElement(By.cssSelector("h3.main-title"));
        assertEquals(mainHeader.getText(), "EPAM FRAMEWORK WISHES…");
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

        //17. Close Browser
        driver.close();
    }

    @Test(groups = "Regression")
    public void test2InGroupSmoke() {
        WebDriver driver = new ChromeDriver();

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
        for (WebElement element : txtElements) {
            assertTrue(texts.contains(element.getText()));
        }

        //9. Assert a text of the main header
        WebElement mainHeader = driver.findElement(By.cssSelector("h3.main-title"));
        assertEquals(mainHeader.getText(), "EPAM FRAMEWORK WISHES…");
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

        //17. Close Browser
        driver.close();
    }

    @Test(groups = "Smoke")
    public void test3InGroupSmoke() {
        WebDriver driver = new ChromeDriver();

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
        for (int i = 0; i < COUNT; i++) {
            assertEquals(txtElements.get(i).getText(), texts.get(i));
        }

        //9. Assert a text of the main header
        WebElement mainHeader = driver.findElement(By.cssSelector("h3.main-title"));
        assertEquals(mainHeader.getText(), "EPAM FRAMEWORK WISHES…");
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

        //17. Close Browser
        driver.close();
    }
}
