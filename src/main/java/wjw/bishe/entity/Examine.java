package wjw.bishe.entity;

import java.util.Date;

public class Examine {
    int id;
    String uid;
    boolean pass;
    int type;
    Date date;
    String examiner;

    public Examine() {

    }

    public Examine(String uid, boolean pass, int type, Date date, String examiner) {
        this.uid = uid;
        this.pass = pass;
        this.type = type;
        this.date = date;
        this.examiner = examiner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExaminer() {
        return examiner;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner;
    }
}
