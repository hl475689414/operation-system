package com.wmq.sys.utils.smssend;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.wmq.sys.utils.Constants;

/**
 * Created by Adolph on 2017/12/6.
 */
public class SmsSendUtil {
    //private static Logger logger = Logger.getLogger(SmsSendUtil.class);
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "U9CEEAsL7Hyqpimw";
    static final String accessKeySecret = "9Ol27QEg8q9mhaSAHzPCYxtZhJYM6x";

    private static SendSmsRequest request ;
    private static IAcsClient acsClient ;

    static {
        try{
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            acsClient = new DefaultAcsClient(profile);
            //组装请求对象-具体描述见控制台-文档部分内容
            request = new SendSmsRequest();
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("外贸圈");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("smsUtil工具初始化失败");
            //logger.error("smsUtil工具初始化失败");
        }
    }

    public static boolean send(String phone, String code){
        try{
            //System.out.println("\">>>>>>>>>短信验证码>>>>>>>>>>phone:\" + phone + \">>>>>>>>>>>>code:\" + code ");
            //logger.info(">>>>>>>>>短信验证码>>>>>>>>>>phone:" + phone + ">>>>>>>>>>>>code:" + code );
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(Constants.SMS_MODEL_CODE_FIVE_MINUTES);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"" + code + "\"}");

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if("OK".equals(sendSmsResponse.getMessage()) && "OK".equals(sendSmsResponse.getCode())) {
                return true;
            }else {
                return false;
            }
        }catch (Exception e) {
            //logger.error("短信验证码异常"+e);
            //System.out.println("短信验证码异常"+e);
            return false;
        }
    }

    /**
     * 通用信息发送
     * @param message 发送的内容
     * @return
     */
    public static boolean sendSmsMessage(String phone, String message){
        try{
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode("SMS_117465077");
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"message\":\"" + message + "\"}");

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if("OK".equals(sendSmsResponse.getMessage()) && "OK".equals(sendSmsResponse.getCode())) {
                return true;
            }else {
                return false;
//                throw new RuntimeException("Code=" + sendSmsResponse.getCode()
//                        +"Message=" + sendSmsResponse.getMessage()
//                        +"RequestId=" + sendSmsResponse.getRequestId()
//                        +"BizId=" + sendSmsResponse.getBizId());
            }
        }catch (Exception e) {
            //logger.error(e);
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        send("18617377358", "1234");
        //sendSmsMessage("18617377358", "您的企业认证将于15天后到期，到期未续费则关闭相应的服务功能，请在企业认证处续费保持原有功能及服务。");
    }

}