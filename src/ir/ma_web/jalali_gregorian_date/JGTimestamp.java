package ir.ma_web.jalali_gregorian_date;


import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JGTimestamp extends JGDate {
    public final static SimpleDateFormat G_DEFAULT_TS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat J_DEFAULT_TS_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//    public final static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private Time time;
    public JGTimestamp() {
        this(Calendar.getInstance().getTime().getTime(),true);
    }
    public JGTimestamp(long time, boolean is_gregorian) {
        super(time,is_gregorian);
        this.time = new Time(time);
    }
    public JGTimestamp(String string, boolean is_gregorian){
        this(Timestamp.valueOf(string.replace('/','-')).getTime(),is_gregorian);
    }
    public JGTimestamp(String string){
        this(string,true);
    }
    public JGTimestamp(Timestamp timestamp, boolean is_gregorian) {
        this(timestamp.getTime(),is_gregorian);
    }
    public JGTimestamp(Timestamp timestamp) {
        this(timestamp,true);
    }
    public JGTimestamp(JGTimestamp jgtimestamp) {
        super(jgtimestamp);
        this.time = jgtimestamp.time;
    }

    public Time getTimeObject(){
        return time;
    }

    @Override
    public String toGString() {
        return super.toGString()+" "+time.toString();
    }

    @Override
    public String toJString() {
        return super.toJString()+" "+time.toString();
    }


    public String toTimeString() {
        return time.toString();
    }

    @Override
    public String toString() {
        return default_gregorian?toGString():toJString();
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)&&time.equals(obj);
    }
}
