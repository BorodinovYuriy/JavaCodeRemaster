
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class BaseUiTest {
    protected WebDriver webDriver;

//    @BeforeEach
//    public void before() {
//        System.setProperty("webdriver.gecko.driver", "/home/aditone/Программы/webdriver/geckodriver");
//        FirefoxOptions options = new FirefoxOptions();
//        webDriver = new FirefoxDriver(options);
//        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
//
//        webDriver.manage().window().maximize();
//
//    }

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "/home/aditone/Программы/webdriver/chromedriver/chromedriver-linux64/chromedriver");

        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }




    @AfterEach
    public void after() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}