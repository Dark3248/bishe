package wjw.bishe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjw.bishe.dao.UserDao;
import wjw.bishe.entity.*;
import wjw.bishe.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String id) {
        return userDao.getByUsername(id);
    }

    @Override
    public Student getStudent(String username) {
        return userDao.getStudent(username);
    }

    @Override
    public User login2(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }

    @Override
    public List<Internship> getAllInternship() {
        return this.userDao.getAllInternship();
    }

    @Override
    public List<Job> getAllJob() {
        return this.userDao.getAllJob();
    }

    @Override
    public List<Graduation> getAllGraduation() {
        return this.userDao.getAllGraduation();
    }

    @Override
    public List<Student> getAllStudent() {
        return this.userDao.getAllStudent2();
    }

    @Override
    public List<Student> getStudentByTeacher(String teacher) {
        return this.userDao.getStudentByTeacher(teacher);
    }
}
