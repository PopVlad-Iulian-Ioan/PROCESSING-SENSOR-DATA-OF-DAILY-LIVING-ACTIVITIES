package Assignment5;

import java.util.Objects;

public class Day {
    public int year=0;
    public int month=0;
    public int day=0;
    public Day(int year,int month,int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }
    public Day(String string){
        int i=0,n=string.length ();
        int count=0;
        while (i<n){
            //skip '-'
            if(string.charAt ( i )=='-'){
                count++;
                i++;
            }
            if(count==0){
                year=year*10+string.charAt ( i )-48;
            }
            if(count==1){
                month=month*10+string.charAt ( i )-48;
            }
            if(count==2){
                day=day*10+string.charAt ( i )-48;
            }
            i++;
        }
    }
    @Override
    public String toString() {
        return year +
                "-" + month +
                "-" + day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Day day1 = (Day) o;
        return year == day1.year &&
                month == day1.month &&
                day == day1.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash ( year , month , day );
    }
}
