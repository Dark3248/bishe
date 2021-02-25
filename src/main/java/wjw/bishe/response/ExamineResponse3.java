package wjw.bishe.response;

import java.util.List;

public class ExamineResponse3 {
    String uid;
    String name;
    List<String> resumePath;
    List<String> insurancePath;
    List<String> contractPath;
    List<String> liangfangPath;

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

    public List<String> getResumePath() {
        return resumePath;
    }

    public void setResumePath(List<String> resumePath) {
        this.resumePath = resumePath;
    }

    public List<String> getInsurancePath() {
        return insurancePath;
    }

    public void setInsurancePath(List<String> insurancePath) {
        this.insurancePath = insurancePath;
    }

    public List<String> getContractPath() {
        return contractPath;
    }

    public void setContractPath(List<String> contractPath) {
        this.contractPath = contractPath;
    }

    public List<String> getLiangfangPath() {
        return liangfangPath;
    }

    public void setLiangfangPath(List<String> liangfangPath) {
        this.liangfangPath = liangfangPath;
    }
}
