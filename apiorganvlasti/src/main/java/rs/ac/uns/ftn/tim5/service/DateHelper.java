package rs.ac.uns.ftn.tim5.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.model.util.Datum;

@Service
public class DateHelper {

    public String toDate(Datum datum) {
        Integer day = datum.getDan().getDay();
        String dayStr = day.toString();
        if(day < 10) {
            dayStr = "0" + dayStr;
        }
        Integer month = datum.getMesec().getMonth();
        String monthStr = month.toString();
        if(day < 10) {
            monthStr = "0" + monthStr;
        }

        return String.format(
                "%s.%s.%s",
                dayStr,
                monthStr,
                datum.getGodina().toString()
        );
    }
}
