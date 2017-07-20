package seleniumjunit.Try;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by ievgen.shchepa on 7/18/2017.
 */
public class TodoMvcTest {
    private static ChromeDriver driver;
    WebElement element;


    @BeforeClass
    public static void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void tasksMainFlow() {

        driver.get("https://todomvc4tasj.herokuapp.com/");
        driver.findElement(By.id("new-todo")).sendKeys("a" + "\n");

        driver.findElement(By.id("new-todo")).sendKeys("b" + "\n");
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}
