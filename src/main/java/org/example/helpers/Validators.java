package org.example.helpers;

import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

public class Validators {

    public static boolean compareSimpleIntegerPrices(String price1, String price2) {
        if (price1 == null || price2 == null) {
            System.out.println("One or both price strings are null.");
            return false;
        }
        String extractedPrice1 = Extractor.extractNumericIntegerValue(price1);
        String extractedPrice2 = Extractor.extractNumericIntegerValue(price2);

        if (extractedPrice1 != null && extractedPrice2 != null) {
            return extractedPrice1.equals(extractedPrice2);
        } else {
            System.out.println("Could not extract numeric value from one or both strings.");
            return false;
        }
    }
    public static boolean checkSum(List<WebElement> allProductPrices, WebElement total) {
        boolean result = false;
        if (allProductPrices == null || total == null || allProductPrices.isEmpty()) {
            System.out.println("Invalid input: product list is null, empty, or total element is null.");
        } else{
            try{
                int sumOfProducts = allProductPrices.stream()
                        .map(p -> Extractor.extractNumericIntegerValue(p.getText()))
                        .filter(Objects::nonNull)
                        .mapToInt(Integer::parseInt)
                        .sum();
                String totalPrise = Extractor.extractNumericIntegerValue(total.getText());

                if (totalPrise!=null){
                    result =  Integer.parseInt(totalPrise) == sumOfProducts;
                }

            } catch (Exception e){
                System.out.println("Exception while extraction :"+ e.getMessage());
            }
        }
        return result;
    }
    public static boolean compareDates(LocalDate currentDate, String dateString) {
        if (currentDate == null || dateString == null || dateString.isEmpty()) {
            System.out.println("Неправильное значение аргументов при попытке сравнить даты");
            return false;
        }

        LocalDate parsedDate = Extractor.parseDate(dateString);
        if (parsedDate == null) {
            System.out.println("Ошибка парсинга - не соответствие заданным шаблонам: " + dateString);
            return false;
        }
        return currentDate.isEqual(parsedDate);
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
