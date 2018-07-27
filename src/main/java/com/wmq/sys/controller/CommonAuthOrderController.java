package com.wmq.sys.controller;

import com.wmq.sys.service.CommonAuthOrderService;
import com.wmq.sys.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 李怀鹏 on 2018/7/18.
 */
@RequestMapping("/authOrder")
@RestController
public class CommonAuthOrderController extends BaseController {
    @Autowired
    private CommonAuthOrderService commonAuthOrderService;

    /**
     * 获取VIP订单列表
     * @return
     */
    @RequestMapping("/getAuthOrderListPage")
    @RequiresPermissions("sys:authOrder:authOrderList")
    public JsonResult getAuthOrderListPage() {
        String key = null;
        if(getParams().containsKey("key")) {
            key = getParams().getString("key");
        }
        String payType = getParams().getString("payType"); //支付方式 空值未选择 alipay wxpay
        int state = getParams().getInt("state"); //支付状态 -1未选择 0未支付,1支付成功,2支付失败
        return commonAuthOrderService.getAuthOrderListPage(key, payType, state);
    }

    /**
     * 获取订单详情
     * @return
     */
    @RequestMapping("/getAuthOrderInfo")
    @RequiresPermissions("sys:authOrder:authOrderInfo")
    public JsonResult getAuthOrderInfo() {
        String id = getParams().getString("id");
        return commonAuthOrderService.getAuthOrderInfo(id);
    }
}
