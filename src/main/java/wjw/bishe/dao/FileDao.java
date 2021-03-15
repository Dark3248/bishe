package wjw.bishe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wjw.bishe.entity.Monthly;
import wjw.bishe.entity.Student;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface FileDao {

    void createMonthly(String id, String path, int number, Date date, int examineStatus, String examineContent);

    List<Monthly> getMonthly(String id);

    void updateMonthly(String id, int number, int examineStatus, String examineContent);

    void addStudent(String uid, String name, String teacher);

    void addUser(String username);

    void changeBook(String uid);

    void changePaper(String uid);

    void changeTuition(String uid, int num);

    void changeGrade(String uid, int num);

    Student getStudent(String uid);
}
