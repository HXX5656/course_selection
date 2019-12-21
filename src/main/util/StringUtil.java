package main.util;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return "".equals(str)||str==null||"null".equals(str);
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    public static void set_string(PreparedStatement ppst,int index,String arg,int types) {
        try {
            if(isEmpty(arg)) {
                ppst.setNull(index,types);
            }
            else {ppst.setString(index,arg);}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Map<String,String>  parse_course_code(String code){
        Map<String,String> result=new HashMap<>();
        result.put("dep_name",code.substring(0,2));
        result.put("course_id",code.substring(2,8));
        result.put("section_id",code.substring(9,11));
        return result;
    }
    public static String parse_idNumber(String id,boolean isTeacher) {
        String number=id;
        if(number.length()<6) {
            int delta = 6 - number.length();
            StringBuilder tmp=new StringBuilder("");
            for (int i = 0; i < delta; i++) {
                tmp.append("0");
            }
            number=tmp+number;
        }
        number=(isTeacher?"T"+number:"S"+number);
        return number;
    }

    /*课程设置
    * 参照复旦大学 本科生 上课表
    * */
    public static List<Map<String,String>> parse_time_and_place(String arrangement) {
        String[] strs=arrangement.split(";");
        List<Map<String,String>> result=new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            result.add(parse_sentence(strs[i].trim()));
        }
        return result;
    }
    private static Map<String,String> parse_sentence(String arg) {
        String[] str=arg.split("\\s+");
        Map<String,String> result=new HashMap<>();
        int day_of_week=0;
        switch (str[0].charAt(1)) {
            case '一':
                day_of_week=1;
                break;
            case '二':
                day_of_week=2;
                break;
            case '三':
                day_of_week=3;
                break;
            case '四':
                day_of_week=4;
                break;
            case '五':
                day_of_week=5;
                break;
            case '六':
                day_of_week=6;
                break;
            case '日':
                day_of_week=7;
                break;
        }
        result.put("day",day_of_week+"");
        if(str[1].charAt(0)=='第') {
            int from=Integer.parseInt(str[1].substring(1,3));
            int to=Integer.parseInt(str[1].substring(4,6));
            result.put("time_start_id",(from+13*(day_of_week-1)+4)+"");
            result.put("time_end_id",(to+13*(day_of_week-1)+4)+"");
        } else {
            String from=str[1].substring(0,2)+str[1].substring(3,5);
            String to=str[1].substring(6,8)+str[1].substring(9,11);
            if(from.equals("0800")) {
                result.put("time_start_id",(5*(day_of_week-1)+96)+"");
                result.put("time_end_id","");
            } else if(from.equals("1030")) {
                result.put("time_start_id",(5*(day_of_week-1)+97)+"");
                result.put("time_end_id","");
            } else if(from.equals("1330")) {
                result.put("time_start_id",(5*(day_of_week-1)+98)+"");
                result.put("time_end_id","");
            } else if(from.equals("1600")) {
                result.put("time_start_id",(5*(day_of_week-1)+99)+"");
                result.put("time_end_id","");
            } else if(from.equals("1830")) {
                result.put("time_start_id",(5*(day_of_week-1)+100)+"");
                result.put("time_end_id","");
            } else {
                result.put("time_start_id","");
                result.put("time_end_id","");
                result.put("start",from);
                result.put("end",to);
            }
        }
        String place="";
        if(str.length>2) {
            place=str[2];
        }
        result.put("place",place);
        return result;
    }
    private static String getStart(int num) {
        String time="0000";
        switch (num){
            case 1:
                time="0800";
                break;
            case 2:
                time="0855";
                break;
            case 3:
                time="0955";
                break;
            case 4:
                time="1050";
                break;
            case 5:
                time="1145";
                break;
            case 6:
                time="1330";
                break;
            case 7:
                time="1425";
                break;
            case 8:
                time="1525";
                break;
            case 9:
                time="1620";
                break;
            case 10:
                time="1715";
                break;
            case 11:
                time="1830";
                break;
            case 12:
                time="1925";
                break;
            case 13:
                time="2020";
                break;
        }
        return time;
    }
    private static String getEnd(int num) {
        String time="0000";
        switch (num){
            case 1:
                time="0845";
                break;
            case 2:
                time="0940";
                break;
            case 3:
                time="1040";
                break;
            case 4:
                time="1135";
                break;
            case 5:
                time="1230";
                break;
            case 6:
                time="1415";
                break;
            case 7:
                time="1510";
                break;
            case 8:
                time="1610";
                break;
            case 9:
                time="1705";
                break;
            case 10:
                time="1800";
                break;
            case 11:
                time="1915";
                break;
            case 12:
                time="2010";
                break;
            case 13:
                time="2105";
                break;
        }
        return time;
    }
}
