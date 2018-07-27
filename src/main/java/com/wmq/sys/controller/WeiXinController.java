package com.wmq.sys.controller;

import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.utils.weixin.SignUtil;
import com.wmq.sys.utils.weixin.WeixinUtil;
import com.wmq.sys.utils.weixin.WxConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李怀鹏 on 2018/4/24.
 */
@RestController
@RequestMapping("/weixin")
public class WeiXinController extends BaseController {
    @Autowired
    private WeixinUtil weixinUtil;

    /**
     * 获取分享参数信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/share")
    public JsonResult share(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = getParams().getString("url");
            String jsapi_ticket = weixinUtil.getJsApiTicket();
            String nonceStr = WxConst.nonceStr;
            String timestamp = Long.toString(System.currentTimeMillis());
            String signature = SignUtil.getSignature(jsapi_ticket, nonceStr, timestamp, url);
            Map map = new HashMap();
            map.put("appId",WxConst.appId);
            map.put("timestamp",timestamp);
            map.put("nonceStr",WxConst.nonceStr);
            map.put("signature",signature);
            return new JsonResult(0, "获取成功", 0, map);
        }catch (Exception e){
            return new JsonResult(1, "获取分享信息失败");
        }
    }
}
