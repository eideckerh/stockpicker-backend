package de.stockpicker.backend.util;

import de.stockpicker.backend.client.alphavantage.webservice.batch.Quote;
import de.stockpicker.backend.client.alphavantage.webservice.batch.Response;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class DateUtil {
    private static final String datetimeFormat = "yyyy-MM-dd HH:mm:ss";

    public static ZonedDateTime convertStringToZonedDate(String dateTime, String timeZone) {
        DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern(datetimeFormat).toFormatter().withZone(ZoneId.of(timeZone));

        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime, df);
        ZonedDateTime local = zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/Berlin"));
        return local;
    }

    public static Response convertBatchResponse(Response response) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(datetimeFormat);

        List<Quote> quotes = response.getQuotes();

        int quoteCount = quotes.size();
        for (int i = 0; i < quoteCount; i++) {
            quotes.get(i).setTimestamp(
                    convertStringToZonedDate(
                            quotes.get(i).getTimestamp(),
                            response.getMetaData().getTimezone()
                    ).format(f)
            );
        }

        return response;
    }
}
