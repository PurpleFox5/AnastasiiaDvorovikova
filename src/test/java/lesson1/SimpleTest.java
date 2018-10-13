package lesson1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;

public class SimpleTest {
    @Test
    public void simpleTest() {
        setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");

        //1. Open Br
        WebDriver driver = new ChromeDriver();
        //На полный экран
        driver.manage().window().maximize();
//        driver.manage().window().fullscreen();
        //Задержка (выжидается драйвером)
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //2. Navigate
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        //3. Assart
        assertEquals(driver.getTitle(), "Home Page");

        //4.Login иконка - 2 поля - кнопка
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("[id = 'Name']")).sendKeys("epam");
        driver.findElement(By.cssSelector("[id = 'Password']")).sendKeys("1234");
        driver.findElement(By.cssSelector(".login [type = 'submit']")).click();


        //Проверка значения текста
        WebElement mainTitle = driver.findElement(By.cssSelector("h3.main-title"));
        assertEquals(mainTitle.getText(), "EPAM FRAMEWORK WISHES…");
        driver.close();
//        driver.quit();
    }
}
