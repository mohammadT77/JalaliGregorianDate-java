package ir.ma_web.jalali_gregorian_date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JGTimestamp extends JGDate{
    public final static SimpleDateFormat G_DEFAULT_TS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat J_DEFAULT_TS_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public final static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private Timestamp timestamp;
    public JGTimestamp() {
        this(Calendar.getInstance().getTime().getTime(),true);
    }
    public JGTimestamp(long time, boolean is_gregorian) {
        super(time,is_gregorian);
        timestamp = new Timestamp(getGTime());
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
        this.timestamp = jgtimestamp.timestamp;
    }

    @Override
    public String toGString() {
        return G_DEFAULT_TS_FORMAT.format(getGDate());
    }

    @Override
    public String toJString() {
        return super.toJString()+" "+TIME_FORMAT.format(getGDate());
    }

    @Override
    public String toString() {
        return default_gregorian?toGString():toJString();
    }
    
    @Override
    public boolean equals(Object obj) {
        return timestamp.equals(obj);
    }
}
