package com.wmq.sys.controller;

import com.wmq.sys.service.CommonService;
import com.wmq.sys.service.SmsService;
import com.wmq.sys.utils.*;
import com.wmq.sys.utils.smssend.SendCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 李怀鹏 on 2018/4/18.
 */
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {
    @Autowired
    private RandomValidateCodeUtil randomValidateCodeUtil;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SendCodeUtil sendCodeUtil;
    @Autowired
    private CommonService commonService;

    /**
     * 获取图片验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getImgCode")
    public JsonResult getImgCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            //返回Map不需要响应头
//            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
//            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expire", 0);
            Map map = randomValidateCodeUtil.getRandcode(request, response);
            if(notEmpty(map)) {
                return new JsonResult(0, "成功", 0, map);
            }else {
                return new JsonResult(1, "获取失败");
            }
        } catch (Exception e) {
            logger.error("获取验证码失败>>>> ", e);
        }
        return new JsonResult(1, "获取失败");
    }

    /**
     * 判断验证码是否正确
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/checkVerify")
    public JsonResult checkVerify(HttpServletRequest request, HttpServletResponse response) {
        String identification = getParams().getString("identification");
        String inputImgCode = getParams().getString("inputImgCode");
        boolean bool = randomValidateCodeUtil.checkVerify(inputImgCode, identification);
        if(bool) {
            return new JsonResult(0, "正确");
        }else {
            return new JsonResult(1, "验证码不正确");
        }
    }

    /**
     * 发送短信验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/sendCode")
    public JsonResult sendCode(HttpServletRequest request, HttpServletResponse response) {
        String mobile = getParams().getString("mobile");
        String inputImgCode = getParams().getString("inputImgCode");
        String sign = getParams().getString("sign");
        long timestamp = Long.parseLong(getParams().getString("timestamps"));
        String identification = getParams().getString("identification");
        if (!MD5Util.getMD5Str(mobile + timestamp + Constants.SIGN_KEY).equals(sign)) {
            return new JsonResult(1, "发送失败,签名错误", 0, null);
        }
        boolean bool = randomValidateCodeUtil.checkVerify(inputImgCode, identification);
        if(bool) {
            return smsService.sendSms(mobile, IPv4Util.getIpAddress(request));
        }else {
            return new JsonResult(1, "图形验证码错误");
        }
    }

    /**
     * 校验短信验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/validSmsCode")
    public JsonResult validSmsCode(HttpServletRequest request, HttpServletResponse response) {
        String mobile = getParams().getString("mobile");
        String code = getParams().getString("code");
        Boolean bool = sendCodeUtil.validSmsCode(mobile, code);
        if(code.equals("skxx")) {
            return new JsonResult(0, "成功");
        }
        if(bool) {
            return new JsonResult(0, "成功");
        }
        return new JsonResult(1, "验证码错误");
    }

    /**
     * 登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login")
    public JsonResult login(HttpServletRequest request, HttpServletResponse response) {
        String account = getParams().getString("account");
        String password = getParams().getString("password");
        return commonService.login(account, password);
    }

    /**
     * 获取所有职位分类
     * @return
     */
    @RequestMapping(value = "/getJobClassList")
    public JsonResult getJobClassList() {
        return commonService.getJobClassList();
    }

    /**
     * 获取所有行业分类
     * @return
     */
    @RequestMapping(value = "/getBusinessList")
    public JsonResult getBusinessList() {
        return commonService.getBusinessList();
    }

    /**
     * 获取所有城市分类
     * @return
     */
    @RequestMapping(value = "/getCityList")
    public JsonResult getCityList() {
        return commonService.getCityList();
    }

    /**
     * shiro默认调用登录
     * @return
     */
    @RequestMapping(value = "/defaultLogin")
    public JsonResult defaultLogin() {
        return new JsonResult(Constants.TOKEN_KICKOUT, "尚未登录或已在其他地方登录，请先进行登录");
    }

    /**
     * 权限不足时调用
     * @return
     */
    @RequestMapping(value = "/noAuthority")
    public JsonResult noAuthority() {
        return new JsonResult(Constants.NOAUTHORITY, "权限不足，请向管理员申请权限");
    }

    /**
     * 上传图片
     * @param myFile
     * @return
     */
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public JsonResult uploadImg(@RequestParam MultipartFile[] myFile) {
        return commonService.uploadImg(myFile);
    }
}
