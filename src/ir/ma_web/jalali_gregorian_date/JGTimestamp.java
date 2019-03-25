package ir.ma_web.jalali_gregorian_date;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
    public JGTimestamp(JGDate date,Time time) {
        super(date);
        this.time = new Time(time.getTime());
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


    public String toGDateString() {
        return super.toGString();
    }
    public String toJDateString() {
        return super.toJString();
    }
    public String toDateString() {
        return super.toString();
    }
    public String toDateString(Locale locale) {
        return super.toString(locale);
    }
    @Override
    public String toGString() {
        return super.toGString()+" "+time.toString();
    }

    @Override
    public String toJString() {
        return super.toJString()+" "+time.toString();
    }

    @Override
    public String toString(Locale locale){
        return locale.equals(new Locale("fa"))?toJString():toGString();
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
        if(obj instanceof JGTimestamp) return time.equals(((JGTimestamp) obj).time);
        if(obj instanceof Long) return time.getTime()==(Long)obj;
        if(obj instanceof Timestamp) return time.getTime()==((Timestamp)obj).getTime();
        return false;
    }
}
