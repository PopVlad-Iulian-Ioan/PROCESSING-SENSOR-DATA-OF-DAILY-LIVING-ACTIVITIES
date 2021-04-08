package Assignment5;

import java.time.LocalTime;

public class DayHour {
    Day day;
    Hour hour;
    public DayHour(Day day, Hour hour){
        this.day=day;
        this.hour=hour;
    }

    @Override
    public String toString() {
        return day + " " + hour;
    }
}
