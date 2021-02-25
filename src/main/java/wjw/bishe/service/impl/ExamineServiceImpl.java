package wjw.bishe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjw.bishe.Constant;
import wjw.bishe.dao.ExamineDao;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Monthly;
import wjw.bishe.request.ExamineMonthlyRequest;
import wjw.bishe.request.ExamineRequest;
import wjw.bishe.response.ExamineMonthlyResponse;
import wjw.bishe.service.ExamineService;

import java.util.List;

@Service
public class ExamineServiceImpl implements ExamineService {

    private ExamineDao examineDao;

    public ExamineServiceImpl(@Autowired ExamineDao examineDao) {
        this.examineDao = examineDao;
    }

    @Override
    public List<Internship> getGrade(String uid) {
        return this.examineDao.getGrade(uid);
    }

    @Override
    public List<Internship> getTuition() {
        return this.examineDao.getTuition();
    }

    @Override
    public List<Internship> getOther() {
        return this.examineDao.getOther();
    }

    @Override
    public List<String> getStu() {
        return this.examineDao.getStu();
    }

    @Override
    public List<Monthly> getMonthly(String uid) {
        return this.examineDao.getMonthly(uid);
    }

    @Override
    public void examine(ExamineRequest request) {
        if (request.getType() == Constant.utype_teacher) {
            this.examineDao.examine1(request);
        } else if (request.getType() == Constant.utype_admin1) {
            this.examineDao.examine2(request);
        } else {
            this.examineDao.examine3(request);
        }
    }

    @Override
    public void examine2(ExamineMonthlyRequest request) {
        this.examineDao.examineMonthly(request);
    }
}
