package fct.unl.pt.instagramplus.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String getAtualDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(new Date());
    }

    public static String getDate(long timemilis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(new Date(timemilis));
    }

    public static String addHoursToDate(String actualDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        try {
            Date dt = simpleDateFormat.parse(actualDate);

            dt.setTime(dt.getTime()+( 24 * 60 * 60 * 1000));
            return simpleDateFormat.format(dt);
        } catch (ParseException e) {
            return actualDate;
        }


    }

}
