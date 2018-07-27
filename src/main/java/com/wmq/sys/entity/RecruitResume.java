package com.wmq.sys.entity;

import java.util.Date;

public class RecruitResume {
    private Integer userid;

    private Date birthday;

    private String nation;

    private String nativeplace;

    private String liveplace;

    private Integer startworkyear;

    private Integer education;

    private String tel;

    private String email;

    private String intro;

    private String tag;

    private String oldposition;

    private String oldcompany;

    private Integer wroktype;

    private String askremark;

    private Integer state;

    private Integer viewnum;

    private String senceurl;

    private String docurl;

    private Integer completerate;

    private Integer isdelete;

    private Integer isoverseas;

    private Integer jobwantedclass;

    private String worklanguage;

    private Integer exportexperience;

    private String businessfunctions;

    private Long createtime;

    private Long updatetime;

    private Integer entrystate;

    private Date entrytime;

    private int sysuserid;

    public RecruitResume(Integer userid, Date birthday, String nation, String nativeplace, String liveplace, Integer startworkyear, Integer education, String tel, String email, String intro, String tag, String oldposition, String oldcompany, Integer wroktype, String askremark, Integer state, Integer viewnum, String senceurl, String docurl, Integer completerate, Integer isdelete, Integer isoverseas, Integer jobwantedclass, String worklanguage, Integer exportexperience, String businessfunctions, Long createtime, Long updatetime, Integer entrystate, Date entrytime, Integer sysuserid) {
        this.userid = userid;
        this.birthday = birthday;
        this.nation = nation;
        this.nativeplace = nativeplace;
        this.liveplace = liveplace;
        this.startworkyear = startworkyear;
        this.education = education;
        this.tel = tel;
        this.email = email;
        this.intro = intro;
        this.tag = tag;
        this.oldposition = oldposition;
        this.oldcompany = oldcompany;
        this.wroktype = wroktype;
        this.askremark = askremark;
        this.state = state;
        this.viewnum = viewnum;
        this.senceurl = senceurl;
        this.docurl = docurl;
        this.completerate = completerate;
        this.isdelete = isdelete;
        this.isoverseas = isoverseas;
        this.jobwantedclass = jobwantedclass;
        this.worklanguage = worklanguage;
        this.exportexperience = exportexperience;
        this.businessfunctions = businessfunctions;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.entrystate = entrystate;
        this.entrytime = entrytime;
        this.sysuserid = sysuserid;
    }

    public RecruitResume() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace == null ? null : nativeplace.trim();
    }

    public String getLiveplace() {
        return liveplace;
    }

    public void setLiveplace(String liveplace) {
        this.liveplace = liveplace == null ? null : liveplace.trim();
    }

    public Integer getStartworkyear() {
        return startworkyear;
    }

    public void setStartworkyear(Integer startworkyear) {
        this.startworkyear = startworkyear;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getOldposition() {
        return oldposition;
    }

    public void setOldposition(String oldposition) {
        this.oldposition = oldposition == null ? null : oldposition.trim();
    }

    public String getOldcompany() {
        return oldcompany;
    }

    public void setOldcompany(String oldcompany) {
        this.oldcompany = oldcompany == null ? null : oldcompany.trim();
    }

    public Integer getWroktype() {
        return wroktype;
    }

    public void setWroktype(Integer wroktype) {
        this.wroktype = wroktype;
    }

    public String getAskremark() {
        return askremark;
    }

    public void setAskremark(String askremark) {
        this.askremark = askremark == null ? null : askremark.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getViewnum() {
        return viewnum;
    }

    public void setViewnum(Integer viewnum) {
        this.viewnum = viewnum;
    }

    public String getSenceurl() {
        return senceurl;
    }

    public void setSenceurl(String senceurl) {
        this.senceurl = senceurl == null ? null : senceurl.trim();
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl == null ? null : docurl.trim();
    }

    public Integer getCompleterate() {
        return completerate;
    }

    public void setCompleterate(Integer completerate) {
        this.completerate = completerate;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getIsoverseas() {
        return isoverseas;
    }

    public void setIsoverseas(Integer isoverseas) {
        this.isoverseas = isoverseas;
    }

    public Integer getJobwantedclass() {
        return jobwantedclass;
    }

    public void setJobwantedclass(Integer jobwantedclass) {
        this.jobwantedclass = jobwantedclass;
    }

    public String getWorklanguage() {
        return worklanguage;
    }

    public void setWorklanguage(String worklanguage) {
        this.worklanguage = worklanguage == null ? null : worklanguage.trim();
    }

    public Integer getExportexperience() {
        return exportexperience;
    }

    public void setExportexperience(Integer exportexperience) {
        this.exportexperience = exportexperience;
    }

    public String getBusinessfunctions() {
        return businessfunctions;
    }

    public void setBusinessfunctions(String businessfunctions) {
        this.businessfunctions = businessfunctions == null ? null : businessfunctions.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getEntrystate() {
        return entrystate;
    }

    public void setEntrystate(Integer entrystate) {
        this.entrystate = entrystate;
    }

    public Date getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Date entrytime) {
        this.entrytime = entrytime;
    }

    public int getSysuserid() {
        return sysuserid;
    }

    public void setSysuserid(int sysuserid) {
        this.sysuserid = sysuserid;
    }
}