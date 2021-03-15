package wjw.bishe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wjw.bishe.entity.Examine;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Monthly;
import wjw.bishe.request.ExamineMonthlyRequest;
import wjw.bishe.request.ExamineRequest;

import java.util.List;

@Mapper
@Repository
public interface ExamineDao {
    Internship getIntern(String uid);

    List<Internship> getGrade(String uid);

    List<Internship> getTuition();

    List<Internship> getOther();

    List<String> getStu();

    List<Monthly> getMonthly(String uid);

    List<Examine> getExamine(String uid);

    void examine1(ExamineRequest request);

    void examine2(ExamineRequest request);

    void examine3(ExamineRequest request);

    void examineMonthly(ExamineMonthlyRequest request);

    void addExamine(Examine examine);
}
