package io.seamhealthv1.simplecrudapplication.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class generateDateTime {

    public String generateDate(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = myDateObj.format(dateTimeFormatter);
        return  dateTime;
    }
}
