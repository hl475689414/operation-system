package com.wmq.sys.utils.smssend;

import com.wmq.sys.redis.RedisCache;
import com.wmq.sys.utils.Constants;
import com.wmq.sys.utils.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by 李怀鹏 on 2018/4/18.
 * 发送短信工具类
 */
@Component
public class SendCodeUtil {
    @Autowired
    private RedisCache redisCache;

    private static final String SMS_PREFIX = "SMS_";
    private static final String SMS_REQUESTQTY = "SMSQTY_";
    private static final String SMS_LIMIT = "SMS_LIMIT";

    /**
     * 发送短信验证码基础方法 by 程江涛
     * @param mobile 手机号码
     * @return
     */
    public Boolean smsSend(String mobile) {
        String key = SMS_PREFIX + mobile;
        String code = redisCache.get(key);
        if (StringUtils.empty(code)) {
            code = UUID.randomUUID().toString().replaceAll("-", "").replaceAll("[a-zA-Z]", "").substring(0, Constants.SMS_LENGTH);
            redisCache.setex(key, code, Constants.SMS_VALIDITY * 60, TimeUnit.SECONDS);
        }
        boolean flag = SmsSendUtil.send(mobile, code);
        return flag;
    }

    /**
     * 校验短信验证码 by 程江涛
     * @param mobile
     * @param smsCode 短信验证码
     * @return
     */
    public Boolean validSmsCode(String mobile, String smsCode) {
        String key = SMS_PREFIX + mobile;
        String code = redisCache.get(key);
        if (smsCode.equals(code)) {
            return true;
        }
        return false;
    }

    /**
     * 检查当天发送验证码次数
     * @param mobile 手机号码
     * @return
     */
    public int checkQty(String mobile) {
        String key = SMS_REQUESTQTY + mobile;
        String code = redisCache.get(key);
        if (StringUtils.empty(code)) {
            redisCache.setex(key, "1", 86400 - (int) DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DATE), TimeUnit.SECONDS);
        }else {
            redisCache.increment(key);
        }
        return Integer.valueOf(redisCache.get(key));
    }

    /**
     * 60 秒只能发一次限制
     * * @param mobile 手机号码
     * @return
     */
    public boolean timeLimit(String mobile) {
        String key = SMS_LIMIT + mobile;
        return redisCache.exists(key);
    }

    /**
     * 添加发送短信限制，一分钟只能发送一次
     * @param mobile
     */
    public void addTimeLimit(String mobile) {
        String key = SMS_LIMIT + mobile;
        redisCache.setex(key, mobile, 60, TimeUnit.SECONDS);
    }
}
