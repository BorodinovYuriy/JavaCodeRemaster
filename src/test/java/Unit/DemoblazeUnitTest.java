package Unit;

import org.example.helpers.Extractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DemoblazeUnitTest {
    @ParameterizedTest
    @CsvSource({"1-11-2000", "10/11/2000","10.1.2000","10 11 2000"})
    public void parseDateTest(String dateToParse){
        System.out.println(Extractor.parseDate(dateToParse));
        Assertions.assertNotNull(Extractor.parseDate(dateToParse),
                "Ошибка парсинга, null returned");
    }
    @ParameterizedTest
    @CsvSource({"s123 d s", "1s2s3  ","123   ","@123"})
    public void extractNumericIntegerValueTest(String dateToParse){
        System.out.println(Extractor.extractNumericIntegerValue(dateToParse));
        Assertions.assertNotNull(Extractor.extractNumericIntegerValue(dateToParse),
                "Ошибка парсинга числа: "+dateToParse);
    }


}
