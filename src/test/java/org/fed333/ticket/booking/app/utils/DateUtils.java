package org.fed333.ticket.booking.app.utils;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * Parses date from a string.
     * @param dateAsString string in dd-MM-yyyy date format
     * @return {@link Date} object from the given string
     * */
    @SneakyThrows
    public static Date parseDate(String dateAsString) {
        return new SimpleDateFormat("dd-MM-yyyy").parse(dateAsString);
    }

}
