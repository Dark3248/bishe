package wjw.bishe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjw.bishe.dao.UserDao;
import wjw.bishe.entity.Student;
import wjw.bishe.entity.User;
import wjw.bishe.service.UserService;

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
}
