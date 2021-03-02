package wjw.bishe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wjw.bishe.entity.Student;
import wjw.bishe.entity.User;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    User getByUsername(String username);

    Student getStudent(String uid);

    User getByUsernameAndPassword(String username, String password);

    void changeStatus(String uid, int status);

    List<Student> getAllStudent();
}
