package wjw.bishe.request;

public class ExamineRequest {
    String uid;
    int examineStatus;
    String examineContent;
    int type;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
