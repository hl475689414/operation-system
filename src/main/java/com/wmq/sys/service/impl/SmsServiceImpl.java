package com.wmq.sys.service.impl;

import com.wmq.sys.redis.RedisCache;
import com.wmq.sys.service.SmsService;
import com.wmq.sys.utils.Constants;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.utils.smssend.Forbidener;
import com.wmq.sys.utils.smssend.SendCodeUtil;
import com.wmq.sys.utils.smssend.SmsSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 李怀鹏 on 2018/1/19.
 * 短信服务相关接口
 */
@Service("smsService")
public class SmsServiceImpl extends BaseService implements SmsService {
    @Autowired
    private SendCodeUtil sendCodeUtil;

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @param ip
     * @return
     */
    @Override
    public JsonResult sendSms(String mobile, String ip) {
        try {
            if (sendCodeUtil.timeLimit(mobile)) {
                return new JsonResult(1, "1分钟只能发起一次验证请求");
            }
            Forbidener fb = Forbidener.getInstance();
            if (!fb.check(ip)) {
                return new JsonResult(Constants.FAIL, "ip限制，一个ip每天只能发送10条短信");
            }
            return baseSendSms(mobile);
        } catch (Exception e) {
            logger.error(e.toString());
            return new JsonResult(1, "发送失败，系统错误");
        }
    }

    /**
     * 发送短信基础方法
     *
     * @param mobile
     * @return
     */
    public JsonResult baseSendSms(String mobile) {
        boolean result = sendCodeUtil.smsSend(mobile);
        if (result) {
            sendCodeUtil.addTimeLimit(mobile);
            return new JsonResult(Constants.SUCCESS, "发送成功");
        }
        return new JsonResult(Constants.FAIL, "发送失败");
    }
}
