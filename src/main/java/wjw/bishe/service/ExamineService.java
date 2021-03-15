package wjw.bishe.service;

import wjw.bishe.entity.Examine;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Monthly;
import wjw.bishe.request.ExamineMonthlyRequest;
import wjw.bishe.request.ExamineRequest;

import java.util.List;

public interface ExamineService {

    List<Internship> getGrade(String uid);

    List<Internship> getTuition();

    List<Internship> getOther();

    List<String> getStu();

    List<Monthly> getMonthly(String uid);

    List<Examine> getExamine(String uid);

    void examine(ExamineRequest request);

    void examine2(ExamineMonthlyRequest request);

}
