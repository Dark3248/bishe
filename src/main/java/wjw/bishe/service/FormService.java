package wjw.bishe.service;

import wjw.bishe.entity.Graduation;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Job;

public interface FormService {
    void createInternship(Internship internship);

    Internship getInternship(String username);

    void createJob(Job job);

    Job getJob(String username);

    void createGraduation(Graduation graduation);

    Graduation getGraduation(String username);
}
