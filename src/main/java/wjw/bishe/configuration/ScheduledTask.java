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
import wjw.bishe.request.ExamineRequest;

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

    @Scheduled(cron = "0 0/1 * * * ?")
    private void task2() {
        List<Student> list = this.userDao.getAllStudent();
        for (Student student : list) {
            Internship internship = this.examineDao.getIntern(student.getUid());
            if (internship == null)
                continue;
            ExamineRequest request = new ExamineRequest();
            request.setUid(student.getUid());

            if (student.isTuition() && student.isGrade()) {
                request.setExamineStatus(1);
                request.setExamineContent("审批通过");
                this.examineDao.examine1(request);
                this.examineDao.examine2(request);
            } else if (!student.isGrade() && student.isTuition()) {
                request.setExamineStatus(2);
                request.setExamineContent("成绩不合格，请联系相关老师");
                this.examineDao.examine1(request);
                request.setExamineStatus(1);
                request.setExamineContent("审批通过");
                this.examineDao.examine2(request);
            } else if (student.isGrade() && !student.isTuition()) {
                request.setExamineStatus(2);
                request.setExamineContent("学费没结清，请联系相关老师");
                this.examineDao.examine2(request);
                request.setExamineStatus(1);
                request.setExamineContent("审批通过");
                this.examineDao.examine1(request);
            } else {
                request.setExamineStatus(2);
                request.setExamineContent("学费没结清，请联系相关老师");
                this.examineDao.examine2(request);
                request.setExamineStatus(2);
                request.setExamineContent("成绩不合格，请联系相关老师");
                this.examineDao.examine1(request);
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
