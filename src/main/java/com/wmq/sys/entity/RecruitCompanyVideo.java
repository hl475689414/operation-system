package com.wmq.sys.entity;

public class RecruitCompanyVideo {
    private Integer companyid;

    private Integer showvideo;

    private String url;

    private String thumbnail;

    private Integer state;

    private String videoid;

    public RecruitCompanyVideo(Integer companyid, Integer showvideo, String url, String thumbnail, Integer state, String videoid) {
        this.companyid = companyid;
        this.showvideo = showvideo;
        this.url = url;
        this.thumbnail = thumbnail;
        this.state = state;
        this.videoid = videoid;
    }

    public RecruitCompanyVideo() {
        super();
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getShowvideo() {
        return showvideo;
    }

    public void setShowvideo(Integer showvideo) {
        this.showvideo = showvideo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid == null ? null : videoid.trim();
    }
}