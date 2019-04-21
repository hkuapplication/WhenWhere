package cs.hku.hk.whenwhere.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimestampHelper {

    //创建 SingleObject 的一个对象
    private static TimestampHelper instance = new TimestampHelper();

    //让构造函数为 private，这样该类就不会被实例化
    private TimestampHelper(){}

    //获取唯一可用的对象
    public static TimestampHelper getInstance(){
        return instance;
    }

    public int getDateTimestamp(int year,int month,int day){
        int result=0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            date = df.parse(year+"-"
                    +(month<=9?"0"+month:month)+"-"
                    +(day<=0?day:day));
        }catch(ParseException e){
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long timestamp = cal.getTimeInMillis();
        result = (int)(timestamp/1000);
        System.out.println("时间戳"+result);
        return result;
    }

    public int getMinuteTimestamp(int hour,int minute){
        return hour*3600+minute*60;
    }

}
