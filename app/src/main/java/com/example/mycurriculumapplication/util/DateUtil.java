package com.example.mycurriculumapplication.util;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private final String TAG="DateUtil";
    private Calendar calendar;
    private String[] week_ch={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
    public DateUtil(){
         calendar = Calendar.getInstance();//取得目前時間
    }
    public String getNowadaysMonthAndDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        Date date=  calendar.getTime();
        String day=sdf.format(date);
        Log.i(TAG, "NowadaysMonthAndDate:"+day);
        return  day;
    }
    public String getNowadaysWeek() {
        int week = calendar.get(Calendar.DAY_OF_WEEK);//1~7
        Log.i(TAG, "NowadaysWeek:"+week_ch[week-1] + "," + week);
        return week_ch[week-1];
    }
}
