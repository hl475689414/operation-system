package com.wmq.sys.utils;

/**
 * Created by 李怀鹏 on 2018/1/18
 */
import java.io.Serializable;

public class JsonResult implements Serializable{

	/**
	 * 状态 0成功，1失败，2未登录
	 */
	private int code;
	/**
	 * 提示信息， 当code＝0时， 返回  成功，，
	 */
	private String message;
	/**
	 * 总条数
	 */
	private long totalCount;
	/**
	 * 数据封装
	 */
	private Object data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public JsonResult(int code, String message, long totalCount, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.totalCount = totalCount;
		this.data = data;
	}
	public JsonResult(int code, String message) {
		super();
		this.code = code;
		this.message = message;
		this.totalCount = 0;
		this.data = null;
	}
	public JsonResult() {
		super();
		// TODO Auto-generated constructor stub
	}
}
