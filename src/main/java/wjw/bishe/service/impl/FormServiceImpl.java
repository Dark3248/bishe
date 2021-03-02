package wjw.bishe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjw.bishe.Constant;
import wjw.bishe.dao.FormDao;
import wjw.bishe.entity.Graduation;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Job;
import wjw.bishe.service.FormService;

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
        formDao.createInternship(internship);
    }

    @Override
    public Internship getInternship(String username) {
        return formDao.getInternship(username);
    }

    @Override
    public void createJob(Job job) {
        this.formDao.createJob(job);
    }

    @Override
    public Job getJob(String username) {
        return this.formDao.getJob(username);
    }

    @Override
    public void createGraduation(Graduation graduation) {
        this.formDao.createGraduation(graduation);
    }

    @Override
    public Graduation getGraduation(String username) {
        return this.formDao.getGraduation(username);
    }
}
