package com.wmq.sys.utils.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.wmq.sys.utils.DateUtils;
import com.wmq.sys.utils.base.Base;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 李怀鹏 on 2018/4/25.
 */
@Component
public class GenerateQrCode extends Base {
    /**
     * 生成二维码
     * @param contents
     * @param width
     * @param height
     * @return
     */
    public String encode(String contents, int width, int height, String saveRealPathDir) {
        //生成条形码时的一些配置
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //减少边框留白 可选1-4
        hints.put(EncodeHintType.MARGIN, 1);

        String filePath = null;
        OutputStream out=null;
        try {
            //生成图片的路径
            String savePath = DateUtils.getDate(); //生成日期
            if (!savePath.endsWith("/")) {
                savePath += File.separator;
            }
            File  f = new File(saveRealPathDir+savePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            filePath = savePath + UUID.randomUUID() + ".png";
            out = new FileOutputStream(saveRealPathDir+filePath);
        } catch (FileNotFoundException e1) {
            logger.error(e1);
        }
        BitMatrix bitMatrix;
        try {
            // 生成二维码
            bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            //保存图片
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
        } catch (Exception e) {
            logger.error(e);
        }
        return saveRealPathDir+filePath;
    }

    /**
     * 加入logo 此方法JPEGImageEncoder引入jdk包会报找不到包异常，解决方法，换种方式实现，pom配置jdk指向包路径
     * @param image
     * @param uploadPath
     * @param realUploadPath
     * @param imgPath
     * @return
     */
//    public String LogoMatrix(File image, String uploadPath, String realUploadPath, String imgPath) {
//        /**
//         * 读取二维码图片，并构建绘图对象
//         */
//        OutputStream os = null ;
//        String logoFileName = "logo_"+imgPath ;
//        try {
//            Image image2 = ImageIO.read(image) ;
//            int width = image2.getWidth(null) ;
//            int height = image2.getHeight(null) ;
//            BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB) ;
//            //BufferedImage bufferImage =ImageIO.read(image) ;
//            Graphics2D g2 = bufferImage.createGraphics();
//            g2.drawImage(image2, 0, 0, width, height, null) ;
//            int matrixWidth = bufferImage.getWidth();
//            int matrixHeigh = bufferImage.getHeight();
//
//            //读取Logo图片
//            BufferedImage logo= ImageIO.read(new File(realUploadPath+"/"+"logo.jpg"));
//            //开始绘制图片
//            g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);//绘制
//            BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
//            g2.setStroke(stroke);// 设置笔画对象
//            //指定弧度的圆角矩形
//            RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5,20,20);
//            g2.setColor(Color.white);
//            g2.draw(round);// 绘制圆弧矩形
//
//            //设置logo 有一道灰色边框
//            BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
//            g2.setStroke(stroke2);// 设置笔画对象
//            RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2, matrixWidth/5-4, matrixHeigh/5-4,20,20);
//            g2.setColor(new Color(128,128,128));
//            g2.draw(round2);// 绘制圆弧矩形
//
//            g2.dispose();
//
//            bufferImage.flush() ;
//            os = new FileOutputStream(realUploadPath+"/"+logoFileName) ;
//            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os) ;
//            en.encode(bufferImage) ;
//
//        } catch (Exception e) {
//            logger.error(e);
//        } finally {
//            if(os!=null) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    logger.error(e);
//                }
//            }
//        }
//        return uploadPath+"/"+logoFileName ;
//    }

    /**
     * 解码
     * @param realImgPath
     * @return
     */
    public String zxingdecode(String realImgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(realImgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            logger.error(e);
        }
        return result.getText() ;
    }
}
