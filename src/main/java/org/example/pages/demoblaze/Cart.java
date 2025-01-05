package org.example.pages.demoblaze;

import org.example.helpers.Validators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Cart  extends BasePage{
    @FindBy(xpath = "//a[@id='cartur']")
    private WebElement cartButton;
    @FindBy(xpath = "//div[@class='table-responsive']")
    private WebElement tableResponsive;
    @FindBy(xpath = "//h3[@id='totalp']")
    private WebElement totalPrise;
    @FindBy(xpath = "//button[@type='button' and text()='Place Order']")
    private WebElement placeOrderButton;

    private final By priseListPath;//@FindBy - не дожидается всех элементов

    public Cart(WebDriver webDriver) {
        super(webDriver);
        this.priseListPath = By.xpath("//div[@class='table-responsive']//tbody//tr/td[3]");
        PageFactory.initElements(webDriver, this);
    }
    public Cart loadCartPage() {
        wait.until(ExpectedConditions.visibilityOf(cartButton)).click();
        return this;
    }
    public boolean checkTotalSum(){
        List<WebElement> allProductPrices = wait.
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(priseListPath));
        return Validators.checkSum(
                            allProductPrices,
                            wait.until(ExpectedConditions.visibilityOf(totalPrise))
        );
    }
    public void makeOrderClick() {
        placeOrderButton.click();
    }
}
