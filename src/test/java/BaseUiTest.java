
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseUiTest {
    protected WebDriver webDriver;

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));

        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }

    @AfterEach
    public void after() {
        webDriver.quit();
    }
}
