package kinoteka.util;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String format(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
