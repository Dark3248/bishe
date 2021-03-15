package wjw.bishe.controller;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wjw.bishe.Constant;
import wjw.bishe.entity.*;
import wjw.bishe.response.LoginResponse;
import wjw.bishe.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    //登录验证
    @GetMapping("/login")
    public LoginResponse login(@RequestParam("username") String username) {
        LoginResponse loginResponse = new LoginResponse();
        User user = userService.login(username);
        if (user != null) {
            if (user.getUtype() == Constant.utype_student) {
                Student student = userService.getStudent(username);
                loginResponse.setName(student.getName());
                loginResponse.setStatus(student.getStatus());
            } else if (user.getUtype() == Constant.utype_teacher) {
                loginResponse.setName("班主任");
            } else if (user.getUtype() == Constant.utype_admin1) {
                loginResponse.setName("学籍管理员");
            } else if (user.getUtype() == Constant.utype_admin2) {
                loginResponse.setName("部门管理员");
            }
            loginResponse.setCode(Constant.code_success);
            loginResponse.setMsg("登录成功");
            loginResponse.setUsername(user.getUsername());
            loginResponse.setUtype(user.getUtype());
        } else {
            loginResponse.setCode(Constant.code_fail);
            loginResponse.setMsg("登录失败");
        }
        return loginResponse;
    }

    @PostMapping("/login2")
    public LoginResponse login2(HttpServletRequest request) {
        LoginResponse loginResponse = new LoginResponse();
        User user = userService.login2(request.getParameter("username"), request.getParameter("password"));
        if (user != null) {
            loginResponse.setCode(Constant.code_success);
            loginResponse.setMsg("登录成功");
            int type = user.getUtype();
            if (type == Constant.utype_teacher) {
                loginResponse.setName("班主任");
            } else if (type == Constant.utype_admin1) {
                loginResponse.setName("学籍管理员");
            } else if (type == Constant.utype_admin2) {
                loginResponse.setName("部门管理员");
            } else {
                loginResponse.setName("超级管理员");
            }
            loginResponse.setUtype(type);
            loginResponse.setUsername(user.getUsername());
        } else {
            loginResponse.setCode(Constant.code_fail);
            loginResponse.setMsg("登录失败");
        }
        return loginResponse;
    }

    @GetMapping("/getBook")
    public boolean getBook(@RequestParam String username) {
        Student student = this.userService.getStudent(username);
        return student.isBook();
    }

    @GetMapping("/getPaper")
    public boolean getPaper(@RequestParam String username) {
        Student student = this.userService.getStudent(username);
        return student.isPaper();
    }

    @GetMapping("/getInternship")
    public List<Internship> getInternship() {
        return this.userService.getAllInternship();
    }

    @GetMapping("/getJob")
    public List<Job> getJob() {
        return this.userService.getAllJob();
    }

    @GetMapping("/getGraduation")
    public List<Graduation> getGraduation() {
        return this.userService.getAllGraduation();
    }

    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent(@RequestParam int type, @RequestParam String teacher) {
        if (type == 1) {
            return this.userService.getAllStudent();
        } else {
            return this.userService.getStudentByTeacher(teacher);
        }
    }
}
