package org.example.pages.demoblaze;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class ProductCard extends BasePage{
    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(xpath = "//h2[@class='name']")
    private WebElement productTitle;
    @FindBy(xpath = "//h3[@class='price-container']")
    private WebElement productPrice;

    public ProductCard(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }
    public Map<String, String> getProductInfo() {
        String title = wait.until(ExpectedConditions.visibilityOf(productTitle)).getText();
        String price = wait.until(ExpectedConditions.visibilityOf(productPrice)).getText();
        return Map.of(title, price);
    }
    public void addToCart() {
        wait.until(ExpectedConditions.visibilityOf(addToCartButton)).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

}
