package com.eco.monitor.file.dto;

public class FileResponse {

    private String url;

    public FileResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
