package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/7/18.
 */
public interface CommonAuthOrderService {
    /**
     * 获取VIP订单列表
     * @param key
     * @param payType
     * @param state
     * @return
     */
    JsonResult getAuthOrderListPage(String key, String payType, int state);

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    JsonResult getAuthOrderInfo(String id);
}
