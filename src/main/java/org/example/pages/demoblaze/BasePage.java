package org.example.pages.demoblaze;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected Faker faker;
    protected String baseUrl;
    protected By home;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 30);
        this.faker = new Faker();
        this.baseUrl = "https://www.demoblaze.com/";
        this.home = By.xpath("//a[@href='index.html' and @class='nav-link']");
    }
    public BasePage openPage(){
        webDriver.get(baseUrl);
        return this;
    }
    public WebElement waitAndGetWebElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public List<WebElement> waitAndGetWebElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    public WebElement getWebElement(By locator){
        return webDriver.findElement(locator);
    }
    public void clickElement(String xPath){
        waitAndGetWebElement(By.xpath(xPath)).click();
    }
    public BasePage goHomeAndWait(BeforeLogin beforeLogIn) {
        waitAndGetWebElement(home).click();
        beforeLogIn.waitConfirmLogin();
        return this;
    }

}
