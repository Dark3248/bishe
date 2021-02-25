package wjw.bishe.response;

import java.util.List;

public class ExamineResponse2 {

    String uid;
    String name;
    List<String> tuitionPath;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTuitionPath() {
        return tuitionPath;
    }

    public void setTuitionPath(List<String> tuitionPath) {
        this.tuitionPath = tuitionPath;
    }
}
