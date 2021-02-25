package wjw.bishe.response;

public class FileResponse {
    int code;
    String path;

    public FileResponse(int code, String path) {
        this.code = code;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
