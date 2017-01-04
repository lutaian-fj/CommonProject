package lta.commonproject.data.entity;

import java.util.List;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2017/1/4
 */

public class PicResultEntity {
    private int code;
    private String msg;
    private List<PicItemEntity> newslist;

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

    public List<PicItemEntity> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<PicItemEntity> newslist) {
        this.newslist = newslist;
    }
}
