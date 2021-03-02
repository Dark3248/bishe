package wjw.bishe.service;

import wjw.bishe.entity.Monthly;

import java.util.Date;
import java.util.List;

public interface FileService {

    void createMonthly(String username, String path, int number, Date date);

    List<Monthly> getMonthly(String username);

    void updateMonthly(String username, int number);

    void addStudent(String uid, String name, String teacher);

    void changeStatus(String uid, int status);

    void changeBook(String uid);

    void changePaper(String uid);

}
