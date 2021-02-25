package wjw.bishe.service;

import wjw.bishe.entity.Student;
import wjw.bishe.entity.User;

public interface UserService {

    User login(String id);

    Student getStudent(String username);

    User login2(String username, String password);
}
