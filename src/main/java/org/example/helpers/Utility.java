package org.example.helpers;

import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility {
    public static Map<WebElement, WebElement> makeProductChoice(List<WebElement> allProdName, List<WebElement> allPrice) {
        if (allProdName == null || allPrice == null || allProdName.isEmpty() || allPrice.isEmpty() || allProdName.size() != allPrice.size()) {
            System.out.println(allProdName);
            System.out.println(allPrice);
            throw new IllegalArgumentException("Lists не должны быть Null, пустыми, разного размера");
        }
        int choice = Randomizer.randomNumber(allProdName.size() - 1);
        return new HashMap<>(Map.of(allProdName.get(choice), allPrice.get(choice)));
    }
}
