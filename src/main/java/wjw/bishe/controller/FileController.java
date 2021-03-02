package wjw.bishe.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wjw.bishe.Constant;
import wjw.bishe.entity.Monthly;
import wjw.bishe.response.FileResponse;
import wjw.bishe.response.MonthlyResponse;
import wjw.bishe.response.ValidateResponse;
import wjw.bishe.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    public FileController(@Autowired FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/uploadMonthly")
    public int storeMonthly(@RequestParam("file") MultipartFile file,
                            @RequestParam("username") String username,
                            @RequestParam("number") int num) {
        if (!file.isEmpty()) {
            String saveFileName = "monthly_" + username + "_" + num + ".pdf";
            String filePath = "/monthly/" + username + "/" + saveFileName;
            File saveFile = new File(Constant.storePath + filePath);

            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                fileService.createMonthly(username, filePath, num, new Date());
                return Constant.code_success;
            } catch (Exception e) {
                return Constant.code_fail;
            }
        } else {
            return Constant.code_fail;
        }
    }

    @GetMapping("/getMonthly")
    public MonthlyResponse getMonthly(@RequestParam("username") String username) {
        List<Monthly> list = fileService.getMonthly(username);
        return new MonthlyResponse(Constant.code_success, "获取成功", list);
    }

    @PostMapping("/updateMonthly")
    public int updateMonthly(@RequestParam("file") MultipartFile file,
                             @RequestParam("username") String username,
                             @RequestParam("number") int num) {
        if (!file.isEmpty()) {
            String saveFileName = "monthly_" + username + "_" + num + ".pdf";
            String filePath = "/monthly/" + username + "/" + saveFileName;
            File saveFile = new File(Constant.storePath + filePath);

            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                fileService.updateMonthly(username, num);
                return Constant.code_success;
            } catch (Exception e) {
                return Constant.code_fail;
            }
        } else {
            return Constant.code_fail;
        }
    }

    @PostMapping("/internship")
    public FileResponse uploadResume(@RequestParam("file") MultipartFile file,
                                     @RequestParam("username") String username,
                                     @RequestParam("type") String type) {
        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String filePath = "/" + type + "/" + username + "/" + originalFilename;
            File saveFile = new File(Constant.storePath + filePath);

            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return new FileResponse(Constant.code_success, filePath);
            } catch (Exception e) {
                return new FileResponse(Constant.code_fail, "");
            }
        } else {
            return new FileResponse(Constant.code_fail, "");
        }
    }

    @PostMapping("/remove")
    public void remove(HttpServletRequest request) {
        String path = request.getParameter("path");
        File file = new File(Constant.storePath + path);
        if (file.exists()) {
            file.delete();
        }
    }

    @GetMapping("/removeAll")
    public void removeAll(@RequestParam String username) {
        File resume = new File(Constant.storePath + "/resume/" + username);
        File insurance = new File(Constant.storePath + "/insurance/" + username);
        File tuition = new File(Constant.storePath + "/tuition/" + username);
        File grade = new File(Constant.storePath + "/grade/" + username);
        File contract = new File(Constant.storePath + "/contract/" + username);
        File liangfang = new File(Constant.storePath + "/liangfang/" + username);
        if (resume.exists()) {
            delete(resume);
        }
        if (insurance.exists()) {
            delete(insurance);
        }
        if (tuition.exists()) {
            delete(tuition);
        }
        if (grade.exists()) {
            delete(grade);
        }
        if (contract.exists()) {
            delete(contract);
        }
        if (liangfang.exists()) {
            delete(liangfang);
        }
    }

    private void delete(File f) {
        for (String file : f.list()) {
            new File(f.getPath() + "/" + file).delete();
        }
    }

    @GetMapping("/validate")
    public ValidateResponse validate(@RequestParam String username,
                                     @RequestParam int type) {
        File resume = new File(Constant.storePath + "/resume/" + username);
        File insurance = new File(Constant.storePath + "/insurance/" + username);
        File tuition = new File(Constant.storePath + "/tuition/" + username);
        File grade = new File(Constant.storePath + "/grade/" + username);
        File contract = new File(Constant.storePath + "/contract/" + username);
        File liangfang = new File(Constant.storePath + "/liangfang/" + username);
        StringBuilder strResume = new StringBuilder();
        StringBuilder strInsurance = new StringBuilder();
        StringBuilder strTuition = new StringBuilder();
        StringBuilder strGrade = new StringBuilder();
        StringBuilder strContract = new StringBuilder();

        if (resume.exists() && resume.list().length > 0 &&
                insurance.exists() && insurance.list().length > 0 &&
                tuition.exists() && tuition.list().length > 0 &&
                grade.exists() && grade.list().length > 0 &&
                contract.exists() && contract.list().length > 0) {

            for (String file : resume.list()) {
                strResume.append("/resume/").append(username).append('/').append(file).append(";");
            }
            for (String file : insurance.list()) {
                strInsurance.append("/insurance/").append(username).append('/').append(file).append(";");
            }
            for (String file : tuition.list()) {
                strTuition.append("/tuition/").append(username).append('/').append(file).append(";");
            }
            for (String file : grade.list()) {
                strGrade.append("/grade/").append(username).append('/').append(file).append(";");
            }
            for (String file : contract.list()) {
                strContract.append("/contract/").append(username).append('/').append(file).append(";");
            }
        } else {
            return new ValidateResponse(Constant.code_fail, "", "", "", "", "", "");
        }
        if (type == 1) {
            return new ValidateResponse(Constant.code_success, strResume.toString(), strInsurance.toString(),
                    strTuition.toString(), strGrade.toString(), strContract.toString(), "");
        } else {
            if (liangfang.exists() && liangfang.list().length > 0) {
                StringBuilder strLiangfang = new StringBuilder();
                for (String file : liangfang.list()) {
                    strLiangfang.append("/liangfang/").append(username).append('/').append(file).append(";");
                }
                return new ValidateResponse(Constant.code_success, strResume.toString(), strInsurance.toString(),
                        strTuition.toString(), strGrade.toString(), strContract.toString(), strLiangfang.toString());
            } else {
                return new ValidateResponse(Constant.code_fail, "", "", "", "", "", "");
            }
        }
    }

    @PostMapping("/excel")
    public int excel(@RequestParam MultipartFile file,
                      @RequestParam String type) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int rowNumbers = sheet.getLastRowNum() + 1;
            Row temp = sheet.getRow(0);
            if (temp == null) {
                return Constant.code_fail;
            }
            //学号 姓名
            switch (type) {
                case "导入学生名单":
                    for (int row = 1; row < rowNumbers; row++) {
                        Row r = sheet.getRow(row);
                        r.getCell(0).setCellType(CellType.STRING);
                        String uid = r.getCell(0).getStringCellValue();
                        r.getCell(1).setCellType(CellType.STRING);
                        String name = r.getCell(1).getStringCellValue();
                        r.getCell(2).setCellType(CellType.STRING);
                        String teacher = r.getCell(2).getStringCellValue();
                        this.fileService.addStudent(uid, name, teacher);
                    }
                    break;
                case "通过开题答辩名单":
                    //学号 姓名 是否通过
                    for (int row = 1; row < rowNumbers; row++) {
                        Row r = sheet.getRow(row);
                        r.getCell(0).setCellType(CellType.STRING);
                        String uid = r.getCell(0).getStringCellValue();
                        r.getCell(2).setCellType(CellType.STRING);
                        String bool = r.getCell(2).getStringCellValue();
                        if (bool.equals("是"))
                            this.fileService.changeStatus(uid, 2);
                    }
                    break;
                case "通过中期答辩名单":
                    for (int row = 1; row < rowNumbers; row++) {
                        Row r = sheet.getRow(row);
                        r.getCell(0).setCellType(CellType.STRING);
                        String uid = r.getCell(0).getStringCellValue();
                        r.getCell(2).setCellType(CellType.STRING);
                        String bool = r.getCell(2).getStringCellValue();
                        if (bool.equals("是"))
                            this.fileService.changeStatus(uid, 5);
                    }
                    break;
                case "通过毕业答辩名单":
                    for (int row = 1; row < rowNumbers; row++) {
                        Row r = sheet.getRow(row);
                        r.getCell(0).setCellType(CellType.STRING);
                        String uid = r.getCell(0).getStringCellValue();
                        r.getCell(2).setCellType(CellType.STRING);
                        String bool = r.getCell(2).getStringCellValue();
                        if (bool.equals("是"))
                            this.fileService.changeStatus(uid, 6);
                    }
                    break;
                case "图书归还名单":
                    for (int row = 1; row < rowNumbers; row++) {
                        Row r = sheet.getRow(row);
                        r.getCell(0).setCellType(CellType.STRING);
                        String uid = r.getCell(0).getStringCellValue();
                        r.getCell(2).setCellType(CellType.STRING);
                        String bool = r.getCell(2).getStringCellValue();
                        if (bool.equals("是"))
                            this.fileService.changeBook(uid);
                    }
                    break;
                case "论文电子版提交名单":
                    for (int row = 1; row < rowNumbers; row++) {
                        Row r = sheet.getRow(row);
                        r.getCell(0).setCellType(CellType.STRING);
                        String uid = r.getCell(0).getStringCellValue();
                        r.getCell(2).setCellType(CellType.STRING);
                        String bool = r.getCell(2).getStringCellValue();
                        if (bool.equals("是"))
                            this.fileService.changePaper(uid);
                    }
                    break;
            }
            workbook.close();
        } catch (IOException e) {
            return Constant.code_fail;
        }
        return Constant.code_success;
    }
}
