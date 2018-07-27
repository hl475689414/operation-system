package com.wmq.sys.service.impl;

import com.wmq.sys.dao.CommonAuthOrderMapper;
import com.wmq.sys.service.CommonAuthOrderService;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.vo.CommonAuthOrderInfoVo;
import com.wmq.sys.vo.CommonAuthOrderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 李怀鹏 on 2018/7/18.
 */
@Service("commonAuthOrderService")
public class CommonAuthOrderServiceImpl extends BaseService implements CommonAuthOrderService {
    @Autowired
    private CommonAuthOrderMapper commonAuthOrderMapper;

    /**
     * 获取VIP订单列表
     * @param key
     * @param payType
     * @param state
     * @return
     */
    @Override
    public JsonResult getAuthOrderListPage(String key, String payType, int state) {
        List<CommonAuthOrderListVo> list = commonAuthOrderMapper.getAuthOrderListPage(key, payType, state);
        for(CommonAuthOrderListVo c : list) {
            if(c.getPayType().equals("alipay")) {
                c.setPayType("支付宝");
            }else if(c.getPayType().equals("wxpay")) {
                c.setPayType("微信");
            }
        }
        return new JsonResult(0, "获取成功", getTotalCount(), list);
    }

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @Override
    public JsonResult getAuthOrderInfo(String id) {
        CommonAuthOrderInfoVo commonAuthOrderInfoVo = commonAuthOrderMapper.getAuthOrderInfo(id);
        if(commonAuthOrderInfoVo.getPayType().equals("alipay")) {
            commonAuthOrderInfoVo.setPayType("支付宝");
        }else if(commonAuthOrderInfoVo.getPayType().equals("wxpay")) {
            commonAuthOrderInfoVo.setPayType("微信");
        }
        commonAuthOrderInfoVo.setState(commonAuthOrderInfoVo.getState().equals("1") ? "支付成功" : "支付失败");
        commonAuthOrderInfoVo.setCurrency("人民币");
        commonAuthOrderInfoVo.setBusinessPreferences("-");
        commonAuthOrderInfoVo.setBusinessTetReceipts(commonAuthOrderInfoVo.getMoney());
        commonAuthOrderInfoVo.setServiceCharge("-");
        commonAuthOrderInfoVo.setRefundMoeny("-");
        commonAuthOrderInfoVo.setNetReceiptsAmount(commonAuthOrderInfoVo.getMoney());
        commonAuthOrderInfoVo.setCommodityDescribe(commonAuthOrderInfoVo.getSubject());
        return new JsonResult(0, "获取成功", 0, commonAuthOrderInfoVo);
    }
}
