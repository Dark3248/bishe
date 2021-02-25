package wjw.bishe.response;

import wjw.bishe.entity.Monthly;

import java.util.List;

public class ExamineMonthlyResponse {

    String uid;
    List<Monthly> monthlyList;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Monthly> getMonthlyList() {
        return monthlyList;
    }

    public void setMonthlyList(List<Monthly> monthlyList) {
        this.monthlyList = monthlyList;
    }
}
