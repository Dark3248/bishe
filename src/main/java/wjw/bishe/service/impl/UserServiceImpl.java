package wjw.bishe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjw.bishe.Constant;
import wjw.bishe.dao.UserDao;
import wjw.bishe.entity.*;
import wjw.bishe.service.UserService;

import java.util.ArrayList;
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
        return this.userDao.getAllStudent();
    }

    @Override
    public List<Student> getStudentByTeacher(String teacher) {
        List<String> directions = this.userDao.getDirection(teacher);
        List<Student> res = new ArrayList<>();
        for (String direction : directions) {
            res.addAll(this.userDao.getStudentByDirection(direction));
        }
        return res;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<Direction> getAllDirections() {
        List<Direction> list = userDao.getAllDirections();
        for (Direction direction : list) {
            direction.setTeacherName(Constant.teacher.get(direction.getTeacher()));
        }
        return list;
    }

    @Override
    public void changeDirection(String name, String teacher) {
        userDao.changeDirection(name, teacher);
    }
}
