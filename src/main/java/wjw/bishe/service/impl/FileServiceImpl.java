package wjw.bishe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjw.bishe.Constant;
import wjw.bishe.dao.FileDao;
import wjw.bishe.dao.UserDao;
import wjw.bishe.entity.Monthly;
import wjw.bishe.entity.Student;
import wjw.bishe.service.FileService;

import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    private FileDao fileDao;
    private UserDao userDao;

    public FileServiceImpl(@Autowired FileDao fileDao,
                           @Autowired UserDao userDao) {
        this.fileDao = fileDao;
        this.userDao = userDao;
    }

    @Override
    public List<Monthly> getMonthly(String username) {
        List<Monthly> list = fileDao.getMonthly(username);
        list.sort(Comparator.comparingInt(Monthly::getNum));
        return list;
    }

    @Override
    public void updateMonthly(String username, int number) {
        fileDao.updateMonthly(username, number, Constant.examine_unknown, "");
    }

    @Override
    public void addStudent(String uid, String name, String teacher) {
        if (this.fileDao.getStudent(uid) != null)
            return;
        this.fileDao.addStudent(uid, name, teacher);
        this.fileDao.addUser(uid);
    }

    @Override
    public void changeStatus(String uid, int status) {
        if (status == 2) {
            Student student = this.userDao.getStudent(uid);
            if (student.getStatus() == Constant.student_status3) {
                this.userDao.changeStatus(uid, Constant.student_status4);
            } else if(student.getStatus() == Constant.student_status2) {
                this.userDao.changeStatus(uid, Constant.student_status3);
            }
        } else {
            this.userDao.changeStatus(uid, status);
        }
    }

    @Override
    public void changeBook(String uid) {
        this.fileDao.changeBook(uid);
        if (check(uid)) {
            this.userDao.changeStatus(uid, Constant.student_status7);
        }
    }

    @Override
    public void changePaper(String uid) {
        this.fileDao.changePaper(uid);
        if (check(uid)) {
            this.userDao.changeStatus(uid, Constant.student_status7);
        }
    }

    @Override
    public void changeTuition(String uid, int num) {
        this.fileDao.changeTuition(uid, num);
    }

    @Override
    public void changeGrade(String uid, int num) {
        this.fileDao.changeGrade(uid, num);
    }

    private boolean check(String uid) {
        Student student = this.fileDao.getStudent(uid);
        return student.isBook() && student.isPaper();
    }

    @Override
    public void createMonthly(String username, String path, int number, Date date) {
        fileDao.createMonthly(username, path, number, date, Constant.examine_unknown, "");
    }
}
