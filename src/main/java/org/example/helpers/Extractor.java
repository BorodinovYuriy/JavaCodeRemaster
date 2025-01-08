package org.example.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Extractor {
    public static String extractNumericIntegerValue(String price) {
        try {
            // Удаляем все символы, кроме цифр и пробелы в начале и конце строки.
            String numericPart = price.trim().replaceAll("[^\\d]", "");
            if (!numericPart.isEmpty()) {
                return numericPart;
            } else {
                System.out.println("Could not extract a numeric value from string:"+price);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception while extraction :"+ e.getMessage());
            return null;
        }
    }
    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }

        String normalizedDateString = dateString.trim().replaceAll("[\\s,-]", "/");
        // Список форматов, которые могут встретиться
        String[] formats = {
                "M/d/yyyy",
                "MM/dd/yyyy",
                "yyyy/MM/dd",
                "d/M/yyyy",
                "dd/MM/yyyy",
                "M.d.yyyy",
                "MM.dd.yyyy",
                "yyyy.MM.dd",
                "d.M.yyyy",
                "dd.MM.yyyy",
                "MMMM d, yyyy",
                "MMM d, yyyy",
        };
        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
                return LocalDate.parse(normalizedDateString, formatter);
            } catch (DateTimeParseException e) {
                // Если формат не подошел, игнорируем и пробуем следующий
            }
        }
        System.out.println("Could not parse date string:" + dateString);
        return null;
    }
}
