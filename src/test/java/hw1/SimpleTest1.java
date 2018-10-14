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
        Assert.assertTrue(driver.findElement(By.cssSelector(".uui-navigation.nav.navbar-nav.m-l8")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector(".uui-navigation.nav.navbar-nav.m-l8")).getText(),
                "HOME\n" +
                        "CONTACT FORM\n" +
                        "SERVICE\n" +
                        "METALS & COLORS");

        //7. Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> elements = driver.findElements(By.cssSelector(".icons-benefit"));
        Assert.assertEquals(elements.size(), 4);
        Assert.assertTrue(elements.stream().allMatch(WebElement::isDisplayed));

        //8. Assert that there are 4 texts on the Index Page under icons and they have proper text

        //9. Assert a text of the main header
        WebElement mainHeader = driver.findElement(By.cssSelector("h3.main-title"));
        assertEquals(mainHeader.getText(), "EPAM FRAMEWORK WISHES…");
        WebElement mainTxt = driver.findElement(By.cssSelector(".main-txt"));
        assertEquals(mainTxt.getText().substring(0, 11), "LOREM IPSUM");
        assertEquals(mainTxt.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD" +
                " TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD" +
                " EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN " +
                "REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");

        //10. Assert that there is the iframe in the center of page
        int windowPosition = driver.manage().window().getSize().getHeight();
        WebElement iframe = driver.findElement(By.cssSelector("iframe")); //Correct
        System.out.println(windowPosition + " " + iframe.getAttribute("margin") + " " + (windowPosition / 2));
        System.out.println(iframe.getLocation().getY());

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
        String innerHtml = subHeader.getAttribute("innerHTML");
        String pref = "";
        Matcher matcher = Pattern.compile("http[\\w:/.]+").matcher(innerHtml);
        if(matcher.find()) {
            pref = matcher.group();
        }
        Assert.assertEquals(pref, "https://github.com/epam/JDI");


        //17. Close Browser
        driver.close();
    }


}
