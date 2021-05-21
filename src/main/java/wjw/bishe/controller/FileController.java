package wjw.bishe.controller;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wjw.bishe.Constant;
import wjw.bishe.entity.Internship;
import wjw.bishe.entity.Monthly;
import wjw.bishe.response.FileResponse;
import wjw.bishe.response.MonthlyResponse;
import wjw.bishe.response.ValidateResponse;
import wjw.bishe.service.FileService;
import wjw.bishe.service.FormService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;
    private FormService formService;
    Calendar calendar;

    public FileController(@Autowired FileService fileService, @Autowired FormService formService) {
        this.fileService = fileService;
        this.formService = formService;
        calendar = Calendar.getInstance();
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
        File contract = new File(Constant.storePath + "/contract/" + username);
        File liangfang = new File(Constant.storePath + "/liangfang/" + username);
        if (resume.exists()) {
            delete(resume);
        }
        if (insurance.exists()) {
            delete(insurance);
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
        File contract = new File(Constant.storePath + "/contract/" + username);
        File liangfang = new File(Constant.storePath + "/liangfang/" + username);
        StringBuilder strResume = new StringBuilder();
        StringBuilder strInsurance = new StringBuilder();
        StringBuilder strTuition = new StringBuilder();
        StringBuilder strGrade = new StringBuilder();
        StringBuilder strContract = new StringBuilder();

        if (resume.exists() && resume.list().length > 0 &&
                insurance.exists() && insurance.list().length > 0 &&
                contract.exists() && contract.list().length > 0) {

            for (String file : resume.list()) {
                strResume.append("/resume/").append(username).append('/').append(file).append(";");
            }
            for (String file : insurance.list()) {
                strInsurance.append("/insurance/").append(username).append('/').append(file).append(";");
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

    private boolean check(int type, Sheet sheet) {
        Row r = sheet.getRow(0);
        r.getCell(0).setCellType(CellType.STRING);
        String bool0 = r.getCell(0).getStringCellValue();
        r.getCell(1).setCellType(CellType.STRING);
        String bool1 = r.getCell(1).getStringCellValue();
        r.getCell(2).setCellType(CellType.STRING);
        String bool2 = r.getCell(2).getStringCellValue();
        if (type == 1) {
            return bool0.equals("学号") && bool1.equals("姓名") && bool2.equals("研究方向");
        } else if (type == 2) {
            return bool0.equals("学号") && bool1.equals("姓名") && bool2.equals("是否通过");
        } else if (type == 3) {
            return bool0.equals("学号") && bool1.equals("姓名") && bool2.equals("是否提交");
        } else {
            return bool0.equals("学号") && bool1.equals("姓名") && bool2.equals("是否归还");
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
            switch (type) {
                case "导入学生名单":
                    //学号 姓名 专业方向
                    if (!check(1, sheet))
                        return Constant.code_fail;
                    for (int row = 1; row < rowNumbers; row++) {
                        Row r = sheet.getRow(row);
                        r.getCell(0).setCellType(CellType.STRING);
                        String uid = r.getCell(0).getStringCellValue();
                        r.getCell(1).setCellType(CellType.STRING);
                        String name = r.getCell(1).getStringCellValue();
                        r.getCell(2).setCellType(CellType.STRING);
                        String direction = r.getCell(2).getStringCellValue();
                        this.fileService.addStudent(uid, name, direction);
                    }
                    break;
                case "通过开题答辩名单":
                    //学号 姓名 是否通过
                    if (!check(2, sheet))
                        return Constant.code_fail;
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
                    if (!check(2, sheet))
                        return Constant.code_fail;
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
                    if (!check(2, sheet))
                        return Constant.code_fail;
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
                    if (!check(4, sheet))
                        return Constant.code_fail;
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
                    if (!check(3, sheet))
                        return Constant.code_fail;
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
                case "导入学费信息":
                    if (!check(2, sheet))
                        return Constant.code_fail;
                    for (int row = 1; row < rowNumbers; row++) {
                        Row r = sheet.getRow(row);
                        r.getCell(0).setCellType(CellType.STRING);
                        String uid = r.getCell(0).getStringCellValue();
                        r.getCell(2).setCellType(CellType.STRING);
                        String bool = r.getCell(2).getStringCellValue();
                        if (bool.equals("是"))
                            this.fileService.changeTuition(uid, 1);
                        else if (bool.equals("否"))
                            this.fileService.changeTuition(uid, 0);
                    }
                    break;
                case "导入成绩单信息":
                    if (!check(2, sheet))
                        return Constant.code_fail;
                    for (int row = 1; row < rowNumbers; row++) {
                        Row r = sheet.getRow(row);
                        r.getCell(0).setCellType(CellType.STRING);
                        String uid = r.getCell(0).getStringCellValue();
                        r.getCell(2).setCellType(CellType.STRING);
                        String bool = r.getCell(2).getStringCellValue();
                        if (bool.equals("是"))
                            this.fileService.changeGrade(uid, 1);
                        else if(bool.equals("否"))
                            this.fileService.changeGrade(uid, 0);
                    }
                    break;
            }
            workbook.close();
        } catch (IOException e) {
            return Constant.code_fail;
        }
        return Constant.code_success;
    }

    @GetMapping("/downloadIntern")
    public String downloadIntern(@RequestParam String username) throws IOException, DocumentException {
        String fileName = Constant.storePath + "/download/pdf.pdf";
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        //将要生成的目标PDF文件名称
        PdfStamper ps = new PdfStamper(reader, bos);

        //设置中文字体
        BaseFont bf = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<>();

        fontList.add(bf);

        //取出报表模板中的所有字段
        AcroFields fields = ps.getAcroFields();
        fields.setSubstitutionFonts(fontList);

        //对表单数据进行赋值
        fillData(fields, username);

        //必须要调用这个，否则文档不会生成的
        ps.setFormFlattening(true);

        ps.close();

        String path = Constant.storePath + "/download/" + username + "/";
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();

        OutputStream fos = new FileOutputStream(path + username + ".pdf");

        fos.write(bos.toByteArray());

        fos.flush();

        fos.close();

        bos.close();

        return "/download/" + username + "/" + username + ".pdf";
    }

    void fillData(AcroFields fields, String username) throws IOException, DocumentException {
        Internship internship = this.formService.getInternship(username);
        fields.setFieldProperty("name", "textsize", 10f, null);
        fields.setField("name", internship.getName());
        fields.setFieldProperty("uid", "textsize", 10f, null);
        fields.setField("uid", internship.getUid());
        fields.setFieldProperty("phoneNumber", "textsize", 10f, null);
        fields.setField("phoneNumber", internship.getPhoneNumber());
        fields.setFieldProperty("email", "textsize", 9f, null);
        fields.setField("email", internship.getEmail());
        fields.setFieldProperty("idCard", "textsize", 10f, null);
        fields.setField("idCard", internship.getIdCard());
        fields.setFieldProperty("companyName", "textsize", 10f, null);
        fields.setField("companyName", internship.getCompanyName());
        fields.setFieldProperty("companyWebsite", "textsize", 10f, null);
        fields.setField("companyWebsite", internship.getCompanyWebsite());
        fields.setFieldProperty("schoolTeacher", "textsize", 10f, null);
        fields.setField("schoolTeacher", internship.getSchoolTeacher());
        fields.setFieldProperty("schoolTeacherPhone", "textsize", 10f, null);
        fields.setField("schoolTeacherPhone", internship.getSchoolTeacherPhone());
        fields.setFieldProperty("companyContact", "textsize", 10f, null);
        fields.setField("companyContact", internship.getCompanyContact());
        fields.setFieldProperty("companyContactPhone", "textsize", 10f, null);
        fields.setField("companyContactPhone", internship.getCompanyContactPhone());
        fields.setFieldProperty("companyTeacher", "textsize", 10f, null);
        fields.setField("companyTeacher", internship.getCompanyTeacher());
        fields.setFieldProperty("companyTeacherPhone", "textsize", 10f, null);
        fields.setField("companyTeacherPhone", internship.getCompanyTeacherPhone());
        fields.setFieldProperty("companyTeacherPost", "textsize", 10f, null);
        fields.setField("companyTeacherPost", internship.getCompanyTeacherPost());
        fields.setFieldProperty("q", "textsize", 10f, null);
        fields.setField("q" + String.valueOf(internship.getCompanyTeacherQualification()), "√");
        fields.setFieldProperty("projectInfo", "textsize", 10f, null);
        fields.setField("projectInfo", internship.getProjectInfo());
        fields.setFieldProperty("companyTeacherInfo", "textsize", 10f, null);
        fields.setField("companyTeacherInfo", internship.getCompanyTeacherInfo());
        if (internship.getInsuranceType() == 1) {
            calendar.setTime(internship.getInsuranceStartDate());
            fields.setFieldProperty("year1", "textsize", 10f, null);
            fields.setField("year1", String.valueOf(calendar.get(Calendar.YEAR)));
            fields.setFieldProperty("month1", "textsize", 10f, null);
            fields.setField("month1", String.valueOf(calendar.get(Calendar.MONTH) + 1));
            fields.setFieldProperty("day1", "textsize", 10f, null);
            fields.setField("day1", String.valueOf(calendar.get(Calendar.DATE)));
            calendar.setTime(internship.getInsuranceEndDate());
            fields.setFieldProperty("year11", "textsize", 10f, null);
            fields.setField("year11", String.valueOf(calendar.get(Calendar.YEAR)));
            fields.setFieldProperty("month11", "textsize", 10f, null);
            fields.setField("month11", String.valueOf(calendar.get(Calendar.MONTH) + 1));
            fields.setFieldProperty("day11", "textsize", 10f, null);
            fields.setField("day11", String.valueOf(calendar.get(Calendar.DATE)));
        } else {
            fields.setFieldProperty("year2", "textsize", 10f, null);
            fields.setField("year2", String.valueOf(calendar.get(Calendar.YEAR)));
            fields.setFieldProperty("month2", "textsize", 10f, null);
            fields.setField("month2", String.valueOf(calendar.get(Calendar.MONTH) + 1));
            fields.setFieldProperty("day2", "textsize", 10f, null);
            fields.setField("day2", String.valueOf(calendar.get(Calendar.DATE)));
            calendar.setTime(internship.getInsuranceEndDate());
            fields.setFieldProperty("year22", "textsize", 10f, null);
            fields.setField("year22", String.valueOf(calendar.get(Calendar.YEAR)));
            fields.setFieldProperty("month22", "textsize", 10f, null);
            fields.setField("month22", String.valueOf(calendar.get(Calendar.MONTH) + 1));
            fields.setFieldProperty("day22", "textsize", 10f, null);
            fields.setField("day22", String.valueOf(calendar.get(Calendar.DATE)));
        }
    }
}
