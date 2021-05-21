package wjw.bishe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wjw.bishe.entity.*;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    User getByUsername(String username);

    Student getStudent(String uid);

    User getByUsernameAndPassword(String username, String password);

    void changeStatus(String uid, int status);

    List<Student> getStudentStatus1();

    List<Internship> getAllInternship();

    List<Job> getAllJob();

    List<Graduation> getAllGraduation();

    List<Student> getAllStudent();

    List<Student> getStudentByDirection(String direction);

    List<User> getAllUsers();

    List<String> getDirection(String teacher);

    List<Direction> getAllDirections();

    void changeDirection(String name, String teacher);
}
