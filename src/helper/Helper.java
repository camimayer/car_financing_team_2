package helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helper {
    public static LocalDate dateConverter(String dateText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //convert String to LocalDate
        return LocalDate.parse(dateText, formatter);
    }

}
