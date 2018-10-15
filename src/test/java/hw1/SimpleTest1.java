package hw1;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class SimpleTest1 {
    @Test
    public void simpleTest() {
        WebDriver driver = new ChromeDriver();

        //1. Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        //2. Assert Browser title
        Assert.assertEquals(driver.getTitle(), "Home Page");

        //3. Perform login
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("[id = 'Name']")).sendKeys("epam");
        driver.findElement(By.cssSelector("[id = 'Password']")).sendKeys("1234");
        driver.findElement(By.cssSelector(".login [type = 'submit']")).click();

        //4. Assert User name in the left-top side of screen that user is loggined
        Assert.assertEquals(driver.findElement(By.cssSelector(".profile-photo")).getText(), "PITER CHAILOVSKII");

        //5. Assert Browser title
        Assert.assertEquals(driver.getTitle(), "Home Page");

        //6. Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerSections = driver.findElements(By.cssSelector(".m-l8 > li > a"));
        Assert.assertEquals(headerSections.get(0).getText(), "HOME");
        Assert.assertEquals(headerSections.get(1).getText(), "CONTACT FORM");
        Assert.assertEquals(headerSections.get(2).getText(), "SERVICE");
        Assert.assertEquals(headerSections.get(3).getText(), "METALS & COLORS");

        //7. Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> elements = driver.findElements(By.cssSelector(".icons-benefit"));
        Assert.assertEquals(elements.size(), 4);
        Assert.assertTrue(elements.stream().allMatch(WebElement::isDisplayed));

        //8. Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> txtElements = driver.findElements(By.cssSelector(".benefit-txt"));
        Assert.assertEquals(txtElements.get(0).getText(), "To include good practices\n" +
                                                              "and ideas from successful\n" +
                                                              "EPAM project");
        Assert.assertEquals(txtElements.get(1).getText(), "To be flexible and\ncustomizable");
        Assert.assertEquals(txtElements.get(2).getText(), "To be multiplatform");
        Assert.assertEquals(txtElements.get(3).getText(), "Already have good base\n" +
                                                              "(about 20 internal and\n" +
                                                              "some external projects),\n" +
                                                              "wish to get more…");


        //9. Assert a text of the main header
        WebElement mainHeader = driver.findElement(By.cssSelector("h3.main-title"));
        assertEquals(mainHeader.getText(), "EPAM FRAMEWORK WISHES…");
        WebElement mainTxt = driver.findElement(By.cssSelector(".main-txt"));
        assertEquals(mainTxt.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD" +
                " TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD" +
                " EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN " +
                "REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");

        //10. Assert that there is the iframe in the center of page
        WebElement iframe = driver.findElement(By.cssSelector("iframe"));
        Assert.assertTrue(iframe.isDisplayed());

        //11. Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame("iframe");
        Point logoPosition = driver.findElement(By.cssSelector(".epam-logo")).getLocation();
        Assert.assertEquals(logoPosition.getX() + logoPosition.getY(), 0);

        //12. Switch to original window back
        driver.switchTo().parentFrame();

        //13. Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.xpath("/html/body/div/div[2]/main/div[2]/h3[2]"));
        assertEquals(subHeader.getText(), "JDI GITHUB");

        //14. Assert that JDI GITHUB is a link and has a proper URL
        Assert.assertEquals(subHeader.findElement(By.cssSelector("h3:nth-child(3) > a")).getAttribute("href"), "https://github.com/epam/JDI");

        //15. Assert that there is Left Section
        Assert.assertTrue(driver.findElement(By.cssSelector(".uui-side-bar")).isDisplayed());

        //16. Assert that there is Footer
        Assert.assertTrue(driver.findElement(By.cssSelector(".footer-bg")).isDisplayed());

        //17. Close Browser
        driver.close();
    }


}
