package wjw.bishe.response;

import java.util.List;

public class ExamineResponse1 {

    String uid;
    String name;
    List<String> gradePath;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<String> getGradePath() {
        return gradePath;
    }

    public void setGradePath(List<String> gradePath) {
        this.gradePath = gradePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
