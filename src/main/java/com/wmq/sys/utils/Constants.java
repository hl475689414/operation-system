package com.wmq.sys.utils;

public class Constants {

	public Constants() {
	}

	/** 日期模式 */
	public static final String DATE_PATTERN_DASH_YEAR_MONTH_DAY = "yyyy-MM-dd";

	/** 日期模式 */
	public static final String DATE_PATTERN_DASH_YEAR_MONTH_DAY_HOUR_MIN_SEC = "yyyy-MM-dd HH:mm:ss";
	
	/** utf8 编码**/
	public static final String ENCODE_UTF8 = "utf-8";

	/** 验证码签名 **/
	public static final String SIGN_KEY = "asD#85T3cj309";

	/**成功返回码**/
	public static final int SUCCESS = 0;

	/**失败返回码**/
	public static final int FAIL = 1;

	/**异常返回码**/
	public static final int ANOMALY = 1002;

	/**发送短信间隔不足一分钟**/
	public static final int SMS_ONE_MINUTE = 101;

	/**发送短信次数超过5次**/
	public static final int SMS_OVERRUN = 102;

	/** 短信验证码有效期，单位分钟。 **/
	public static final int SMS_VALIDITY = 5;

	/** 短信验证码位数。 **/
	public static final int SMS_LENGTH = 6;

	/** 短信验证码模板CODE 5 分钟有效期模板 **/
	public static final String SMS_MODEL_CODE_FIVE_MINUTES = "SMS_120376813";

	/** 短信验证码模板CODE 10 分钟有效期模板 **/
	public static final String SMS_MODEL_CODE_TEN_MINUTES = "SMS_115765459";

	/** Token过期或被踢下线 **/
	public static final int TOKEN_KICKOUT = 1006;

	/** 无访问权限 **/
	public static final int NOAUTHORITY = 403;

	/** 参数异常 **/
	public static final int PARAMETER_ABNORMAL = 1007;

}