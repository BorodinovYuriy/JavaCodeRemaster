package org.example.helpers;

import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    //Для API
    public static Long parseAndReturnTimeDiff(String checkedTime){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            ZonedDateTime actualDateTime = ZonedDateTime.parse(
                    checkedTime,
                    formatter.withZone(ZoneId.of("UTC")));
            ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
            return Math.abs(Duration.between(actualDateTime, currentDateTime).getSeconds());
        } catch (DateTimeParseException e) {
            return null;
        }

    }
}
