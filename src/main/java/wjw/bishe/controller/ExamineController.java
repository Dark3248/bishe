package wjw.bishe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wjw.bishe.entity.Examine;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Monthly;
import wjw.bishe.request.ExamineMonthlyRequest;
import wjw.bishe.request.ExamineRequest;
import wjw.bishe.response.ExamineMonthlyResponse;
import wjw.bishe.response.ExamineResponse1;
import wjw.bishe.response.ExamineResponse2;
import wjw.bishe.response.ExamineResponse3;
import wjw.bishe.service.ExamineService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/examine")
public class ExamineController {

    private ExamineService examineService;

    public ExamineController(@Autowired ExamineService examineService) {
        this.examineService = examineService;
    }

    @GetMapping("/getGrade")
    public List<ExamineResponse1> getGrade(@RequestParam String username) {
        List<Internship> list = this.examineService.getGrade(username);
        List<ExamineResponse1> res = new ArrayList<>();
        for (Internship internship : list) {
            String path = internship.getGradePath();
            String uid = internship.getUid();
            String name = internship.getName();
            String[] gradePaths = path.split(";");
            ExamineResponse1 response = new ExamineResponse1();
            response.setUid(uid);
            response.setName(name);
            response.setGradePath(Arrays.asList(gradePaths));
            res.add(response);
        }
        return res;
    }

    @GetMapping("/getTuition")
    public List<ExamineResponse2> getTuition() {
        List<Internship> list = this.examineService.getTuition();
        List<ExamineResponse2> res = new ArrayList<>();
        for (Internship internship : list) {
            String path = internship.getTuitionPath();
            String uid = internship.getUid();
            String name = internship.getName();
            String[] tuitionPaths = path.split(";");
            ExamineResponse2 response = new ExamineResponse2();
            response.setUid(uid);
            response.setName(name);
            response.setTuitionPath(Arrays.asList(tuitionPaths));
            res.add(response);
        }
        return res;
    }

    @GetMapping("/getOther")
    public List<ExamineResponse3> getOther() {
        List<Internship> list = this.examineService.getOther();
        List<ExamineResponse3> res = new ArrayList<>();
        for (Internship internship : list) {
            String uid = internship.getUid();
            String name = internship.getName();
            ExamineResponse3 response = new ExamineResponse3();
            String[] resumePaths = internship.getResumePath().split(";");
            String[] insurancePaths = internship.getInsurancePath().split(";");
            String[] contractPaths = internship.getContractPath().split(";");
            response.setUid(uid);
            response.setName(name);
            response.setResumePath(Arrays.asList(resumePaths));
            response.setInsurancePath(Arrays.asList(insurancePaths));
            response.setContractPath(Arrays.asList(contractPaths));
            if (internship.getLiangfangPath() == null) {
                response.setLiangfangPath(new ArrayList<>());
            } else {
                response.setLiangfangPath(Arrays.asList(internship.getLiangfangPath().split(";")));
            }
            res.add(response);
        }
        return res;
    }

    @GetMapping("/getMonthly")
    public List<ExamineMonthlyResponse> getMonthly() {
        List<String> studentList = this.examineService.getStu();
        List<ExamineMonthlyResponse> res = new ArrayList<>();
        for (String student : studentList) {
            List<Monthly> monthlyList = this.examineService.getMonthly(student);
            ExamineMonthlyResponse response = new ExamineMonthlyResponse();
            response.setUid(student);
            response.setMonthlyList(monthlyList);
            res.add(response);
        }
        return res;
    }

    @GetMapping("getExamine")
    public List<Examine> getExamine(@RequestParam String username) {
        return this.examineService.getExamine(username);
    }

    @PostMapping("/")
    public void examine(@RequestBody ExamineRequest request) {
        this.examineService.examine(request);
    }

    @PostMapping("examineMonthly")
    public void examineMonthly(@RequestBody ExamineMonthlyRequest request) {
        this.examineService.examine2(request);
    }
}
