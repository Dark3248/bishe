package wjw.bishe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjw.bishe.Constant;
import wjw.bishe.dao.FileDao;
import wjw.bishe.entity.Monthly;
import wjw.bishe.service.FileService;

import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    private FileDao fileDao;

    public FileServiceImpl(@Autowired FileDao fileDao) {
        this.fileDao = fileDao;
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
    public void addStudent(String uid, String name) {
        this.fileDao.addStudent(uid, name);
        this.fileDao.addUser(uid);
    }

    @Override
    public void createMonthly(String username, String path, int number, Date date) {
        fileDao.createMonthly(username, path, number, date, Constant.examine_unknown, "");
    }
}
