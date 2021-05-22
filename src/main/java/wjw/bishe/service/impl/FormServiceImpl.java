package wjw.bishe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjw.bishe.Constant;
import wjw.bishe.dao.FormDao;
import wjw.bishe.entity.Graduation;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Job;
import wjw.bishe.service.FormService;

import java.util.Date;
import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    private FormDao formDao;

    public FormServiceImpl(@Autowired FormDao formDao) {
        this.formDao = formDao;
    }

    @Override
    public void createInternship(Internship internship) {
        this.formDao.deleteById(internship.getUid());
        if (internship.getExamineStatus1() != 1) {
            internship.setExamineContent1("");
            internship.setExamineStatus1(Constant.examine_unknown);
        }
        if (internship.getExamineStatus2() != 1) {
            internship.setExamineContent2("");
            internship.setExamineStatus2(Constant.examine_unknown);
        }
        if (internship.getExamineStatus3() != 1) {
            internship.setExamineContent3("");
            internship.setExamineStatus3(Constant.examine_unknown);
        }
        formDao.createInternship(internship, new Date());
    }

    @Override
    public Internship getInternship(String username) {
        return formDao.getInternship(username);
    }

    @Override
    public void createJob(Job job) {
        this.formDao.createJob(job, new Date());
    }

    @Override
    public Job getJob(String username) {
        return this.formDao.getJob(username);
    }

    @Override
    public void createGraduation(Graduation graduation) {
        this.formDao.createGraduation(graduation, new Date());
    }

    @Override
    public Graduation getGraduation(String username) {
        return this.formDao.getGraduation(username);
    }

    @Override
    public List<Internship> getAllInternship() {
        return this.formDao.getAllInternship();
    }

    @Override
    public List<Job> getAllJob() {
        return this.formDao.getAllJob();
    }

    @Override
    public List<Graduation> getAllGraduation() {
        return this.formDao.getAllGraduation();
    }
}
