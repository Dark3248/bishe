package wjw.bishe.entity;

import java.util.Date;

public class Monthly {
    private int id;
    private String uid;
    private String path;
    private int num;
    private Date postDate;
    private int examineStatus;
    private String examineContent;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(int examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getExamineContent() {
        return examineContent;
    }

    public void setExamineContent(String examineContent) {
        this.examineContent = examineContent;
    }
}
