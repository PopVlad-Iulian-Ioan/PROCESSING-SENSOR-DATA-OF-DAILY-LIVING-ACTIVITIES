package Assignment5;

public class MonitoredData {
    DayHour start;
    DayHour end;
    String activity;
    public MonitoredData(DayHour start,DayHour end,String activity){
        this.start=start;
        this.end=end;
        this.activity=activity;
    }
    public Hour duration(){
        int hour,minute,second;

        if(start.hour.hour>end.hour.hour)
            hour=24-start.hour.hour+end.hour.hour;
        else
            hour=end.hour.hour-start.hour.hour;
        if(start.hour.minute>end.hour.minute)
            minute=60-start.hour.minute+end.hour.minute;
        else
            minute=end.hour.minute-start.hour.minute;
        if(start.hour.second>end.hour.second)
            second=60-start.hour.second+end.hour.second;
        else
            second=end.hour.second-start.hour.second;
        return new Hour ( hour,minute,second );
    }
    public double durationInMinutes(){
        Hour duration=duration ();
        return (duration.hour*3600+duration.minute*60+duration.second)/60;
    }

    public String getActivity() {
        return activity;
    }
    public String getDay(){
        return start.day.toString ();
    }
    @Override
    public String toString() {
        return  start + " " + end + " " + activity;
    }
}
