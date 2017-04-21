package com.taotao.commom;

/**
 * Created by winsion on 2017/4/18.
 */
public class PictureResult {
    /**
     * 上传图片返回值，成功：0	失败：1
     */
    private Integer error;
    /**
     * 回显图片使用的url
     */
    private String url;
    /**
     * 错误时的错误消息
     */
    private String message;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
