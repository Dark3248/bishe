package wjw.bishe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wjw.bishe.entity.Graduation;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Job;
import wjw.bishe.service.FormService;

@RestController
@RequestMapping("/form")
public class FormController {

    private FormService formService;

    public FormController(@Autowired FormService formService) {
        this.formService = formService;
    }

    @PostMapping("/internship")
    public void internship(@RequestBody Internship internship) {
        this.formService.createInternship(internship);
    }

    @GetMapping("/getInternship")
    public Internship getInternship(@RequestParam String username) {
        return formService.getInternship(username);
    }

    @PostMapping("/job")
    public void job(@RequestBody Job job) {
        this.formService.createJob(job);
    }

    @GetMapping("/getJob")
    public Job getJob(@RequestParam String username) {
        return this.formService.getJob(username);
    }

    @PostMapping("/graduation")
    public void graduation(@RequestBody Graduation graduation) {
        this.formService.createGraduation(graduation);
    }

    @GetMapping("/getGraduation")
    public Graduation getGraduation(@RequestParam String username) {
        return this.formService.getGraduation(username);
    }
}
