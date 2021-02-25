package wjw.bishe;

public class Constant {
    //return code
    public static int code_success = 1;
    public static int code_fail = 2;

    //user type
    public static int utype_student = 1;
    public static int utype_teacher = 2;
    public static int utype_admin1 = 3;
    public static int utype_admin2 = 4;

    //examine 1:success 2:fail 3:not examine
    public static int examine_success = 1;
    public static int examine_fail = 2;
    public static int examine_unknown = 3;

    //file store path
    public static String storePath = "E://test";

    //实习阶段
    public static int student_status1 = 1;
    //开题阶段（开题未通过）
    public static int student_status2 = 2;
    //开题阶段（开题通过）
    public static int student_status3 = 3;
    //中期阶段
    public static int student_status4 = 4;
    //答辩阶段
    public static int student_status5 = 5;
    //离校阶段
    public static int student_status6 = 6;
    //毕业
    public static int student_status7 = 7;

}
