package wjw.bishe.response;

public class ValidateResponse {
    private int code;
    private String resume;
    private String insurance;
    private String tuition;
    private String grade;
    private String contract;
    private String liangfang;

    public ValidateResponse(int code, String resume, String insurance, String tuition, String grade, String contract, String liangfang) {
        this.code = code;
        this.resume = resume;
        this.insurance = insurance;
        this.tuition = tuition;
        this.grade = grade;
        this.contract = contract;
        this.liangfang = liangfang;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getTuition() {
        return tuition;
    }

    public void setTuition(String tuition) {
        this.tuition = tuition;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getLiangfang() {
        return liangfang;
    }

    public void setLiangfang(String liangfang) {
        this.liangfang = liangfang;
    }
}
