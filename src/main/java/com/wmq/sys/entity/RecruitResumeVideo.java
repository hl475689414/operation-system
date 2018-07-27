package com.wmq.sys.entity;

public class RecruitResumeVideo {
    private Integer userid;

    private Integer showvideo;

    private String url;

    private String thumbnail;

    private Integer state;

    private String videoid;

    public RecruitResumeVideo(Integer userid, Integer showvideo, String url, String thumbnail, Integer state, String videoid) {
        this.userid = userid;
        this.showvideo = showvideo;
        this.url = url;
        this.thumbnail = thumbnail;
        this.state = state;
        this.videoid = videoid;
    }

    public RecruitResumeVideo() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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