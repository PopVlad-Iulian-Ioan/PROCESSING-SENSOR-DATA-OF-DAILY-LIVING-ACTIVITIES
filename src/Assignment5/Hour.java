package Assignment5;

public class Hour {
    public int hour;
    public int minute;
    public int second;
    public Hour(int hour,int minute,int second){
        this.hour=hour;
        this.minute=minute;
        this.second=second;
    }
    public Hour(String string){
        int i=0,n=string.length ();
        int count=0;
        while (i<n){
            //skip '-'
            if(string.charAt ( i )==':'){
                count++;
                i++;
            }
            if(count==0){
                hour=hour*10+string.charAt ( i )-48;
            }
            if(count==1){
                minute=minute*10+string.charAt ( i )-48;
            }
            if(count==2){
                second=second*10+string.charAt ( i )-48;
            }
            i++;
        }
    }

    @Override
    public String toString() {
        return  hour +
                ":" + minute +
                ":" + second ;
    }
}
