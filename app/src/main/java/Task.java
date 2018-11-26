import java.sql.Time;
import java.util.Date;

public class Task {
    private String title;
    private long startDate;
    private long endDate;
    private long startTime;
    private long endTime;
    private long duration;

    public Task( String t, long s,long e, long s1,long e1){
        this.title=t;
        this.startDate=s;
        this.endDate=e;
        this.startTime = s1;
        this.endTime = e1;
    }
//    public long getDuration(){
//
//    }

}
