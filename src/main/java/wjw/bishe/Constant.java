package wjw.bishe;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    //return code
    public static int code_success = 1;
    public static int code_fail = 2;

    //user type
    public static int utype_student = 1;
    public static int utype_teacher = 2;
    public static int utype_admin1 = 3;
    public static int utype_admin2 = 4;
    public static int utype_super = 5;

    //examine 1:success 2:fail 3:not examine
    public static int examine_success = 1;
    public static int examine_fail = 2;
    public static int examine_unknown = 3;

    //file store path
    public static String storePath = "/root/test";
//    public static String storePath = "/Users/wjw/Desktop/test";

    //实习阶段
    public static int student_status1 = 1;
    //开题阶段
    public static int student_status2 = 2;
    //开题阶段(六次月报已提交或开题通过)
    public static int student_status3 = 3;
    //中期阶段
    public static int student_status4 = 4;
    //答辩阶段
    public static int student_status5 = 5;
    //离校阶段
    public static int student_status6 = 6;
    //毕业
    public static int student_status7 = 7;

    public static Map<String, String> teacher = new HashMap<>();
    static {
        teacher.put("张云晓", "95428");
        teacher.put("赵丹", "95055");
        teacher.put("刘佳", "95373");
        teacher.put("穆妍霖", "95737");
        teacher.put("秦洋", "95176");
        teacher.put("赵玲玲", "95736");
        teacher.put("95428", "张云晓");
        teacher.put("95055", "赵丹");
        teacher.put("95373", "刘佳");
        teacher.put("95737", "穆妍霖");
        teacher.put("95176", "秦洋");
        teacher.put("95736", "赵玲玲");
    }
}
