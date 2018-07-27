package com.wmq.sys.interceptor;

import com.wmq.sys.utils.Constants;
import com.wmq.sys.utils.JsonResult;
import net.sf.json.JSONException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 李怀鹏 on 2018/5/26.
 */
@ControllerAdvice
@ResponseBody
public class GlobalDefaultExceptionHandler {

    /**
     * 由于shiro本身问题，导致未授权页面跳转失效，所以在此捕获异常，提示权限不足
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    private JsonResult noAuthorityError(UnauthorizedException e) {
        return new JsonResult(Constants.NOAUTHORITY, "权限不足，请向管理员申请权限");
    }

    /**
     * token过期或已退出登陆
     * @param e
     * @return
     */
    @ExceptionHandler(UnknownSessionException.class)
    private JsonResult SessionBeOverdueError(UnknownSessionException e) {
        return new JsonResult(Constants.TOKEN_KICKOUT, "token已过期，请重新登录");
    }

    /**
     * 参数异常
     * 缺失必传字段时、逗号中文、缺失引号之类的会导致此异常
     * @param e
     * @return
     */
    @ExceptionHandler(JSONException.class)
    private JsonResult parameterError(JSONException e, HttpServletRequest request) {
        /**
         * 如果session已过期，并且已被清空，shiro会抛出JSONException异常并且跳转默认接口，所以在此判断是否是找不到session而抛出的一次
         * 如果是，提示token过期，否则抛出参数异常
         */
        String path = request.getServletPath();
        if(path.contains("/common/defaultLogin")) {
            return new JsonResult(Constants.TOKEN_KICKOUT, "token已过期，请重新登录");
        }
        return new JsonResult(Constants.PARAMETER_ABNORMAL, "参数异常");
    }

    /**
     * 全局异常捕捉
     * 这里根据报的异常类型可以写不同的方法，反别捕捉
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    private JsonResult returnErrorPage(Exception e) {
        return new JsonResult(1002, "系统错误");
    }
}
