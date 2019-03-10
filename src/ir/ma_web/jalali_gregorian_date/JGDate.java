package ir.ma_web.jalali_gregorian_date;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class JGDate{
	public final static String[] JALALI_DAYS = {
            "یکشنبه",
            "دوشنبه",
            "سه شنبه",
            "چهارشنبه",
            "پنجشنبه",
            "جمعه",
            "شنبه"
    };
    public final static String[] JALALI_MONTHS = {
            "فروردین",
            "اردیبهشت",
            "خرداد",
            "تیر",
            "مرداد",
            "شهریور",
            "مهر",
            "آبان",
            "آذر",
            "دی",
            "بهمن",
            "اسفند"
    };
    public final static String[] GREGORIAN_SHORT_DAYS = {
            "Sun",
            "Mon",
            "Tue",
            "Wed",
            "Thu",
            "Fri",
            "Sat"
    };
    public final static String[] GREGORIAN_SHORT_MONTHS = {
           "Jan",
           "Feb",
           "Mar",
           "Apr",
           "May",
           "Jun",
           "Jul",
           "Aug",
           "Sep",
           "Oct",
           "Nov",
           "Dec"
    };
    public final static String[] GREGORIAN_DAYS = {
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
    };
    public final static String[] GREGORIAN_MONTHS = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "Decembe"
    };
    public final static SimpleDateFormat G_DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat J_DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
  
    protected Date jDate;
    protected Date gDate;
    protected boolean default_gregorian;


    public JGDate() {
        this(Calendar.getInstance().getTime().getTime(),true);
    }
    public JGDate(long time, boolean is_gregorian) {
        default_gregorian = is_gregorian;
        Calendar calendar = Calendar.getInstance();
        if(is_gregorian){
            gDate = new Date(time);
            calendar.setTime(gDate);
            int[] j = {calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH)};
            int[] i =   JDF_DateConverter.gregorian_to_jalali(j[0],j[1],j[2]);
            jDate = Date.valueOf(JDF_DateConverter.toString(i));
        } else {
            jDate = new Date(time);
            calendar.setTime(jDate);
            int[] j = {calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH)};
            int[] i =   JDF_DateConverter.jalali_to_gregorian(j[0],j[1],j[2]);
            gDate = Date.valueOf(JDF_DateConverter.toString(i));
        }
    }
    public JGDate(String string, boolean is_gregorian){
        this(Date.valueOf(string.replace('/','-')).getTime(),is_gregorian);
    }
    public JGDate(Date date, boolean is_gregorian) {
        this(date.getTime(),is_gregorian);
    }
    public JGDate(JGDate date) {
        this.gDate = date.gDate;
        this.jDate = date.jDate;
        this.default_gregorian = date.default_gregorian;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Long) return gDate.getTime()==(Long) obj;
        else if(obj instanceof Date) return gDate.equals(obj)||jDate.equals(obj);
        else if(obj instanceof String) return G_DEFAULT_DATE_FORMAT.format(jDate).equals(obj)||toJString().equals(obj)||toGString().equals(obj);
        else return false;
    }

    public int getDayOfWeek(){
        return getGCalendar().get(Calendar.DAY_OF_WEEK)-1;
    }

    public long getGTime(){
        return gDate.getTime();
    }
    public long getJTime(){
        return jDate.getTime();
    }

    public Date getGDate(){
        return gDate;
    }
    public Date getJDate(){
        return jDate;
    }
    public String getGDay_string(){
        return GREGORIAN_DAYS[getDayOfWeek()];
    }
    public String getGDay_shortString(){
        return GREGORIAN_SHORT_DAYS[getDayOfWeek()];
    }
    public String getJDay_string(){
        return JALALI_DAYS[getDayOfWeek()];
    }
    public String getGMonth_string(){
        return GREGORIAN_MONTHS[getGMonth()-1];
    }
    public String getGMonth_shortString(){
        return GREGORIAN_MONTHS[getGMonth()-1];
    }
    public String getJMonth_string(){
        return JALALI_MONTHS[getJMonth()-1];
    }
    public Calendar getGCalendar(){
        Calendar c = Calendar.getInstance();
        c.setTime(gDate);
        return c;
    }
    public Calendar getJCalendar(){
        Calendar c = Calendar.getInstance();
        c.setTime(jDate);
        return c;
    }


    public String toGString(){
        return G_DEFAULT_DATE_FORMAT.format(getGDate());
    }
    public String toJString(){
        return J_DEFAULT_DATE_FORMAT.format(getJDate());
    }

    public int getGYear(){
        return getGCalendar().get(Calendar.YEAR);
    }
    public int getJYear(){
        return getJCalendar().get(Calendar.YEAR);
    }

    public int getGMonth(){
        return getGCalendar().get(Calendar.MONTH)+1;
    }
    public int getJMonth(){
        return getJCalendar().get(Calendar.MONTH)+1;
    }
    public int getGDay(){
        return getGCalendar().get(Calendar.DAY_OF_MONTH);
    }
    public int getJDay(){
        return getJCalendar().get(Calendar.DAY_OF_MONTH);
    }



    @Override
    public String toString() {
        return default_gregorian?toGString():toJString();
    }
}
