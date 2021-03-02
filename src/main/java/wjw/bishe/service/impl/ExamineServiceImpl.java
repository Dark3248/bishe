package wjw.bishe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjw.bishe.Constant;
import wjw.bishe.dao.ExamineDao;
import wjw.bishe.dao.FileDao;
import wjw.bishe.dao.UserDao;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Monthly;
import wjw.bishe.entity.Student;
import wjw.bishe.request.ExamineMonthlyRequest;
import wjw.bishe.request.ExamineRequest;
import wjw.bishe.response.ExamineMonthlyResponse;
import wjw.bishe.service.ExamineService;

import java.io.File;
import java.util.Comparator;
import java.util.List;

@Service
public class ExamineServiceImpl implements ExamineService {

    private ExamineDao examineDao;
    private UserDao userDao;
    private FileDao fileDao;

    public ExamineServiceImpl(@Autowired ExamineDao examineDao,
                              @Autowired UserDao userDao,
                              @Autowired FileDao fileDao) {
        this.examineDao = examineDao;
        this.userDao = userDao;
        this.fileDao = fileDao;
    }

    @Override
    public List<Internship> getGrade(String uid) {
        return this.examineDao.getGrade(uid);
    }

    @Override
    public List<Internship> getTuition() {
        return this.examineDao.getTuition();
    }

    @Override
    public List<Internship> getOther() {
        return this.examineDao.getOther();
    }

    @Override
    public List<String> getStu() {
        return this.examineDao.getStu();
    }

    @Override
    public List<Monthly> getMonthly(String uid) {
        List<Monthly> list = examineDao.getMonthly(uid);
        list.sort(Comparator.comparingInt(Monthly::getNum));
        return list;
    }

    @Override
    public void examine(ExamineRequest request) {
        String username = request.getUid();
        File resume = new File(Constant.storePath + "/resume/" + username);
        File insurance = new File(Constant.storePath + "/insurance/" + username);
        File tuition = new File(Constant.storePath + "/tuition/" + username);
        File grade = new File(Constant.storePath + "/grade/" + username);
        File contract = new File(Constant.storePath + "/contract/" + username);
        File liangfang = new File(Constant.storePath + "/liangfang/" + username);
        if (request.getType() == Constant.utype_teacher) {
            if (request.getExamineStatus() == 2) {
                delete(grade);
            }
            this.examineDao.examine1(request);
        } else if (request.getType() == Constant.utype_admin1) {
            if (request.getExamineStatus() == 2) {
                delete(tuition);
            }
            this.examineDao.examine2(request);
        } else {
            if (request.getExamineStatus() == 2) {
                delete(resume);
                delete(insurance);
                delete(contract);
                delete(liangfang);
            }
            this.examineDao.examine3(request);
        }
    }

    private void delete(File f) {
        for (String file : f.list()) {
            new File(f.getPath() + "/" + file).delete();
        }
    }

    @Override
    public void examine2(ExamineMonthlyRequest request) {
        this.examineDao.examineMonthly(request);
        String uid = request.getUid();
        if (check2(uid)) {
            Student student = this.userDao.getStudent(uid);
            if (student.getStatus() == Constant.student_status3) {
                this.userDao.changeStatus(uid, Constant.student_status4);
            } else if(student.getStatus() == Constant.student_status2) {
                this.userDao.changeStatus(uid, Constant.student_status3);
            }
        }
    }

    boolean check2(String uid) {
        List<Monthly> list = this.fileDao.getMonthly(uid);
        if (list.size() == 6) {
            return list.get(0).getExamineStatus() == Constant.examine_success &&
                    list.get(1).getExamineStatus() == Constant.examine_success &&
                    list.get(2).getExamineStatus() == Constant.examine_success &&
                    list.get(3).getExamineStatus() == Constant.examine_success &&
                    list.get(4).getExamineStatus() == Constant.examine_success &&
                    list.get(5).getExamineStatus() == Constant.examine_success;
        }
        return false;
    }
}
