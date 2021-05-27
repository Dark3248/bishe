package wjw.bishe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wjw.bishe.entity.Graduation;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Job;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface FormDao {
    void createInternship(Internship internship);

    Internship getInternship(String uid);

    void createJob(Job job);

    Job getJob(String uid);

    void createGraduation(Graduation graduationp);

    Graduation getGraduation(String uid);

    void deleteById(String uid);

    List<Internship> getAllInternship();

    List<Job> getAllJob();

    List<Graduation> getAllGraduation();
}
