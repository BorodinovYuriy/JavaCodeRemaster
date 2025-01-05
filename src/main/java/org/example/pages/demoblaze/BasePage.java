package org.example.pages.demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected String baseUrl;

    @FindBy(xpath = "//a[text() = 'Home ' and @href='index.html']")
    protected WebElement home;
//    @FindBy(xpath = "//a[@id='nameofuser']")
    private String confirmLogin = "//a[@id='nameofuser']";
    @FindBy(xpath = "//div[@id='footc']")
    private WebElement footer;




    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 30);
        this.baseUrl = "https://www.demoblaze.com/";
        PageFactory.initElements(webDriver, this);
    }
    public void openPage(){
        webDriver.get(baseUrl);
    }
    public boolean waitConfirm() {
//        return wait.until(ExpectedConditions.elementToBeClickable(confirmLogin)).isDisplayed();
        WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmLogin)));
        return until.isDisplayed();
    }
    public void goHome(){
        wait.until(ExpectedConditions.visibilityOf(home)).click();
        waitConfirm();
    }
    public void goFooter(){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(footer);
    }
    public void sleepSec(int sec){
        //Вынужденная мера, когда не за что зацепиться
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
