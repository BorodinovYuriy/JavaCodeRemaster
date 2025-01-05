package org.example.helpers;

import org.junit.jupiter.api.Assertions;

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
    static LocalDate parseDate(String dateString) {
        // Список форматов, которые могут встретиться
        String[] formats = {
                "M/d/yyyy",
                "M.d.yyyy",
                "MM/dd/yyyy",
                "MM.dd.yyyy",
                "yyyy-MM-dd",
                "yyyy.MM.dd",
                "d/M/yyyy",
                "d.M.yyyy",
                "dd/MM/yyyy",
                "dd.MM.yyyy",
                "MMMM d, yyyy",
                "MMM d, yyyy",
        };

        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Если формат не подошел, игнорируем и пробуем следующий
            }
        }
        return null;
    }
}
