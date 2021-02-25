package wjw.bishe.entity;

import java.util.Date;

public class Graduation {

    String name;
    int gender;
    String uid;
    Date birthday;
    String politic;
    String birthPlace;
    String major;
    int studyYear;
    Date graduateTime;
    String phoneNumber;
    String qq;
    String email;
    String company;
    String department;
    String companyEmail;
    String title;
    Date companyTime;
    String companyContact;
    String companyPhone;
    int salary;
    String companyType;
    boolean wish;
    String college;
    String graduationPath;

    @Override
    public String toString() {
        return "Graduation{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", uid='" + uid + '\'' +
                ", birthday=" + birthday +
                ", politic='" + politic + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", major='" + major + '\'' +
                ", studyYear=" + studyYear +
                ", graduateTime=" + graduateTime +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", department='" + department + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", title='" + title + '\'' +
                ", companyTime='" + companyTime + '\'' +
                ", companyContact='" + companyContact + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", salary=" + salary +
                ", companyType='" + companyType + '\'' +
                ", wish=" + wish +
                ", college='" + college + '\'' +
                ", graduationPath='" + graduationPath + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPolitic() {
        return politic;
    }

    public void setPolitic(String politic) {
        this.politic = politic;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public Date getGraduateTime() {
        return graduateTime;
    }

    public void setGraduateTime(Date graduateTime) {
        this.graduateTime = graduateTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCompanyTime() {
        return companyTime;
    }

    public void setCompanyTime(Date companyTime) {
        this.companyTime = companyTime;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public boolean isWish() {
        return wish;
    }

    public void setWish(boolean wish) {
        this.wish = wish;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGraduationPath() {
        return graduationPath;
    }

    public void setGraduationPath(String graduationPath) {
        this.graduationPath = graduationPath;
    }
}
