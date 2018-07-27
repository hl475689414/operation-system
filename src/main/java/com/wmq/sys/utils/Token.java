package com.wmq.sys.utils;

import java.util.UUID;

/**
 * Created by 李怀鹏 on 2018/5/14.
 */
public class Token {
    /**
     * 生成登录token
     * @param userId
     * @return
     */
    public static String generateToken(int userId) {
        String str = "WMQ_OPERATION-SYSTEM_"+ UUID.randomUUID() + "_" + userId;
        return MD5Util.MD5(str);
    }
}
