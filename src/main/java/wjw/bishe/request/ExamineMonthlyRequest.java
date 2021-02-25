package wjw.bishe.request;

public class ExamineMonthlyRequest {
    String uid;
    int num;
    int examineStatus;
    String examineContent;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
