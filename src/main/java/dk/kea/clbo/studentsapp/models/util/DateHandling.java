package dk.kea.clbo.studentsapp.models.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clbo on 02/12/2017.
 */
public class DateHandling {

    // Convert Enrollmentdate into the right format for mysql (yyyy-MM-dd)
    public static String convertDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
