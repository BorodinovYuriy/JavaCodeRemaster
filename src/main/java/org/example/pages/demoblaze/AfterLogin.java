package org.example.pages.demoblaze;

import org.example.helpers.Utility;
import org.example.helpers.Validators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AfterLogin extends BasePage {
    @FindBy(xpath = "//div[@class='list-group']")
    private WebElement categoryAll;
    @FindBy(xpath ="//div[@class ='card-block'][*]//h4/a")
    private WebElement productNames;
    @FindBy(xpath ="//div[@class ='card-block'][*]//h5")

    private WebElement productPrices;
    private ProductCard card;
    public AfterLogin(WebDriver webDriver) {
        super(webDriver);
        this.card = new ProductCard(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public List<String> getCategoryListXpath() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(categoryAll))
                .stream()
                .map(element ->
                        element.findElements(By.xpath("./a[@id='itemc']")))
                .flatMap(List::stream)
                .map(webElement ->
                        "//a[@id='itemc' and text() ='" + webElement.getText() + "']")
                .collect(Collectors.toList());
    }
    private List<WebElement> getProdNameList(String categoryXpath) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
    }
    private List<WebElement> getProdPriceList(String categoryXpath) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
    }
    public Map<WebElement,WebElement> randomProduct(String categoryXpath){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(categoryXpath))).click();
        sleepSec(1);//- всё остальное не работает:( - выдаёт(например) то 1 то 9 Actual -7, ждать пробовал всего...
        List<WebElement> names = getProdNameList(categoryXpath);
        List<WebElement> prices = getProdPriceList(categoryXpath);
        return Utility.makeProductChoice(names,prices);
    }
    public boolean byAndCheck(Map<WebElement,WebElement> product){
        if (product == null || product.size() != 1) {
            throw new IllegalArgumentException("Map is null, empty, or contains more than one element.");
        }
        String productPriceInContainer = product.entrySet().iterator().next().getValue().getText();
        product.entrySet().iterator().next().getKey().click();
        String productPriceFromCard = card.getProductInfo().entrySet().iterator().next().getValue();
        card.addToCart();
        goHome();
        return Validators.compareSimpleIntegerPrices(productPriceInContainer,productPriceFromCard);
    }
}
