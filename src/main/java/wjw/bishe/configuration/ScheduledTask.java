package wjw.bishe.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wjw.bishe.Constant;
import wjw.bishe.dao.ExamineDao;
import wjw.bishe.dao.UserDao;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Student;

import java.util.List;

@Configuration
@EnableScheduling
public class ScheduledTask {

    @Autowired
    ExamineDao examineDao;

    @Autowired
    UserDao userDao;

    //暂时设定为一分钟执行一次，正式部署的时候改为一个月执行一次
    @Scheduled(cron = "0 0/1 * * * ?")
    private void task() {
        List<Student> list = this.userDao.getAllStudent();
        for (Student student : list) {
            if (check(student.getUid())) {
                userDao.changeStatus(student.getUid(), Constant.student_status2);
            }
        }
    }

    private boolean check(String uid) {
        Internship internship = this.examineDao.getIntern(uid);
        if (internship == null)
            return false;
        return internship.getExamineStatus1() == Constant.examine_success &&
                internship.getExamineStatus2() == Constant.examine_success &&
                internship.getExamineStatus3() == Constant.examine_success;
    }

}
