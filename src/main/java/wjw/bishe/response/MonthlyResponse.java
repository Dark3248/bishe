package wjw.bishe.response;

import wjw.bishe.entity.Monthly;

import java.util.List;

public class MonthlyResponse {

    int code;
    String msg;
    List<Monthly> monthlyList;

    public MonthlyResponse(int code, String msg, List<Monthly> monthlyList) {
        this.code = code;
        this.msg = msg;
        this.monthlyList = monthlyList;
    }

    public List<Monthly> getMonthlyList() {
        return monthlyList;
    }

    public void setMonthlyList(List<Monthly> monthlyList) {
        this.monthlyList = monthlyList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
