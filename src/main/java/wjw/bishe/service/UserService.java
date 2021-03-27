package wjw.bishe.service;

import wjw.bishe.entity.*;

import java.util.List;

public interface UserService {

    User login(String id);

    Student getStudent(String username);

    User login2(String username, String password);

    List<Internship> getAllInternship();

    List<Job> getAllJob();

    List<Graduation> getAllGraduation();

    List<Student> getAllStudent();

    List<Student> getStudentByTeacher(String teacher);

    List<User> getAllUsers();
}
