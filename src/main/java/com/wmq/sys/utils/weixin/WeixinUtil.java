package com.wmq.sys.utils.weixin;

import com.wmq.sys.redis.RedisCache;
import com.wmq.sys.utils.HttpConnHelper;
import com.wmq.sys.utils.base.Base;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by 李怀鹏 on 2018/4/24.
 */
@Component
public class WeixinUtil {
    @Autowired
    private RedisCache redisCache;

    /**
     * 获取ACCESS_TOKEN，此值为所有微信接口调用的唯一凭证
     * 过期时间为2个小时
     * @return
     */
    public String getAccessToken() {
        //获取缓存中的access_token对象
        String accessToken = redisCache.get("yyrc_cache_access_token");
        if(Base.notEmpty(accessToken)) {
            return accessToken;
        }
        //否则获取新的
        String json = HttpConnHelper.doHttpRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WxConst.appId+"&secret="+WxConst.appSecret+"");
        JSONObject object = JSONObject.fromObject(json);
        //将access_token放入缓存中,有效时间为110分钟，微信那边的有效时间为120分钟
        redisCache.setex("cache_access_token", object.get("access_token").toString(), 110, TimeUnit.MINUTES);
        return object.get("access_token").toString();
    }

    /**
     * JsApiTicket
     * 过期时间为2个小时
     * @return
     */
    public String getJsApiTicket() {
        //获取缓存中的access_token对象
        String jsApiTicket = redisCache.get("yyrc_cache_jsapiticket");
        if(Base.notEmpty(jsApiTicket)) {
            return jsApiTicket;
        }
        //否则获取新的
        String json = HttpConnHelper.doHttpRequest("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+getAccessToken()+"&type=jsapi");
        JSONObject object = JSONObject.fromObject(json);
        //将access_token放入缓存中,有效时间为110分钟，微信那边的有效时间为120分钟
        redisCache.setex("yyrc_cache_jsapiticket", object.get("ticket").toString(), 110, TimeUnit.MINUTES);
        return object.get("ticket").toString();
    }
}
