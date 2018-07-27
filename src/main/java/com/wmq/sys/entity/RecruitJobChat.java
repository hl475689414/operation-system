package com.wmq.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class RecruitJobChat implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3232244857638403662L;
	private Integer id;
	private Integer talkId;//个人为求职意向ID,企业为职位Id
	private Integer hrId;//发布者Id
	private Integer userId;
	private Integer openTalkUser;//0:个人 1:企业
	private Integer replyState;//回复状态    0发起，1 回复
	private Date createTime;
	

	public RecruitJobChat(Integer talkId, Integer hrId, Integer userId, Integer openTalkUser, Integer replyState, Date createTime) {
		super();
		this.talkId = talkId;
		this.hrId = hrId;
		this.userId = userId;
		this.openTalkUser = openTalkUser;
		this.replyState = replyState;
		this.createTime = createTime;
	}

	/**
	 *
	 * @param objId
	 * @param otherId
	 * @param userId
	 * @param openRole
     */
	public RecruitJobChat(Integer objId, Integer otherId, Integer userId, Integer openRole) {
		super();
		this.talkId = objId;
		this.hrId = otherId;
		this.userId = userId;
		this.openTalkUser = openRole;
		this.replyState = 0;
		this.createTime = new Date();
	}

	public RecruitJobChat() {
		super();
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTalkId() {
		return talkId;
	}
	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}
	public Integer getOpenTalkUser() {
		return openTalkUser;
	}
	public void setOpenTalkUser(Integer openTalkUser) {
		this.openTalkUser = openTalkUser;
	}
	public Integer getHrId() {
		return hrId;
	}
	public void setHrId(Integer hrId) {
		this.hrId = hrId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getReplyState() {
		return replyState;
	}
	public void setReplyState(Integer replyState) {
		this.replyState = replyState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
