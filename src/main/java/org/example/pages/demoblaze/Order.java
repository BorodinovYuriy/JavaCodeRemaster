package org.example.pages.demoblaze;

import org.example.helpers.Data;
import org.example.helpers.Validators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;

public class Order  extends BasePage{
    @FindBy(xpath = "//button[@onclick='purchaseOrder()']")
    private WebElement purchaseButton;
    @FindBy(xpath = "//div[@class='sweet-alert  showSweetAlert visible']")
    private WebElement sweetAlert;
    @FindBy(xpath = "//p[@class='lead text-muted ']")
    private WebElement dateField;
    @FindBy(xpath = "//button[text()='OK']")
    private WebElement oK;
    @FindBy(xpath = "//input[@id='name']")
    private WebElement name;
    @FindBy(xpath = "//input[@id='country']")
    private WebElement country;
    @FindBy(xpath = "//input[@id='city']")
    private WebElement city;
    @FindBy(xpath = "//input[@id='card']")
    private WebElement card;
    @FindBy(xpath = "//input[@id='month']")
    private WebElement month;
    @FindBy(xpath = "//input[@id='year']")
    private WebElement year;

    public Order(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public Order placeOrder() {
        wait.until(ExpectedConditions.visibilityOf(name)).sendKeys(Data.makeName());
        wait.until(ExpectedConditions.visibilityOf(country)).sendKeys(Data.makeCountry());
        wait.until(ExpectedConditions.visibilityOf(city)).sendKeys(Data.makeCity());
        wait.until(ExpectedConditions.visibilityOf(card)).sendKeys(Data.makeCard());
        wait.until(ExpectedConditions.visibilityOf(month)).sendKeys(Data.makeMonth());
        wait.until(ExpectedConditions.visibilityOf(year)).sendKeys(Data.makeYear());
        return this;
    }
    public boolean purchaseAndCheckDate() {
        wait.until(ExpectedConditions.visibilityOf(purchaseButton)).click();
        wait.until(ExpectedConditions.visibilityOf(sweetAlert));
        wait.until(ExpectedConditions.visibilityOf(oK));

        String paragraphText = wait.until(ExpectedConditions.visibilityOf(dateField)).getText();
        String dateString = "";

        if(paragraphText.contains("Date:")){
            int startIndex = paragraphText.indexOf("Date:") + "Date:".length();
            dateString = paragraphText.substring(startIndex).trim();
        }
        LocalDate currentDate = LocalDate.now();

        System.out.println(dateString+"- Это дата на форме");
        System.out.println(currentDate+"- Это локальная дата");

        return Validators.compareDates(currentDate,dateString);

    }
}
