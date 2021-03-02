package wjw.bishe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wjw.bishe.entity.Graduation;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Job;

@Mapper
@Repository
public interface FormDao {
    void createInternship(Internship internship);

    Internship getInternship(String uid);

    void createJob(Job job);

    Job getJob(String uid);

    void createGraduation(Graduation graduation);

    Graduation getGraduation(String uid);

    void deleteById(String uid);
}
