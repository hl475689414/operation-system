package com.wmq.sys.utils.weixin;

import java.security.MessageDigest;

/**
 * Created by 李怀鹏 on 2018/4/24.
 */
public class SignUtil {
    /**
     * 获得分享链接的签名。
     * @param ticket
     * @param nonceStr
     * @param timeStamp
     * @param url
     * @return
     * @throws Exception
     */
    public static String getSignature(String ticket, String nonceStr, String timeStamp, String url) throws Exception {
        String sKey = "jsapi_ticket=" + ticket
                + "&noncestr=" + nonceStr + "&timestamp=" + timeStamp
                + "&url=" + url;
        return getSignature(sKey);
    }


    /**
     * 验证签名。
     *
     * @param sKey
     * @return
     */
    public static String getSignature(String sKey) throws Exception {
        String ciphertext = null;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(sKey.toString().getBytes());
        ciphertext = byteToStr(digest);
        return ciphertext.toLowerCase();
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
