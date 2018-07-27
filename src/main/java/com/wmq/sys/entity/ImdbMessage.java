package com.wmq.sys.entity;

public class ImdbMessage {
    private Integer id;

    private String messageid;

    private String touserid;

    private String myuserid;

    private Long timestamp;

    private Integer target;

    private String type;

    private String message;

    private String bodies;

    private String ext;

    private Integer state;

    public ImdbMessage(Integer id, String messageid, String touserid, String myuserid, Long timestamp, Integer target, String type, String message, String bodies, String ext, Integer state) {
        this.id = id;
        this.messageid = messageid;
        this.touserid = touserid;
        this.myuserid = myuserid;
        this.timestamp = timestamp;
        this.target = target;
        this.type = type;
        this.message = message;
        this.bodies = bodies;
        this.ext = ext;
        this.state = state;
    }

    public ImdbMessage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }

    public String getTouserid() {
        return touserid;
    }

    public void setTouserid(String touserid) {
        this.touserid = touserid == null ? null : touserid.trim();
    }

    public String getMyuserid() {
        return myuserid;
    }

    public void setMyuserid(String myuserid) {
        this.myuserid = myuserid == null ? null : myuserid.trim();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getBodies() {
        return bodies;
    }

    public void setBodies(String bodies) {
        this.bodies = bodies == null ? null : bodies.trim();
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}