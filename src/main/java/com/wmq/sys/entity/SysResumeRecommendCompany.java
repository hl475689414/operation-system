package com.wmq.sys.entity;

import java.util.Date;

public class SysResumeRecommendCompany {
    private Integer id;

    private Integer recommenduserid;

    private Integer jobbaseid;

    private Integer filterid;

    private Integer userid;

    private Integer sysuserid;

    private Date createtime;

    public SysResumeRecommendCompany(Integer id, Integer recommenduserid, Integer jobbaseid, Integer filterid, Integer userid, Integer sysuserid, Date createtime) {
        this.id = id;
        this.recommenduserid = recommenduserid;
        this.jobbaseid = jobbaseid;
        this.filterid = filterid;
        this.userid = userid;
        this.sysuserid = sysuserid;
        this.createtime = createtime;
    }

    public SysResumeRecommendCompany() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecommenduserid() {
        return recommenduserid;
    }

    public void setRecommenduserid(Integer recommenduserid) {
        this.recommenduserid = recommenduserid;
    }

    public Integer getJobbaseid() {
        return jobbaseid;
    }

    public void setJobbaseid(Integer jobbaseid) {
        this.jobbaseid = jobbaseid;
    }

    public Integer getFilterid() {
        return filterid;
    }

    public void setFilterid(Integer filterid) {
        this.filterid = filterid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSysuserid() {
        return sysuserid;
    }

    public void setSysuserid(Integer sysuserid) {
        this.sysuserid = sysuserid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}