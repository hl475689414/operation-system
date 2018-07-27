package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/1/19.
 * 短信验证码暴露的服务接口
 */
public interface SmsService {
    /**
     * 发送短信验证码
     * @return
     */
    JsonResult sendSms(String mobile, String ip);
}
