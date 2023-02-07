package com.sazkuyo.ultrawallpaper2.Util;

//import com.swetake.util.Qrcode;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//import javax.imageio.ImageIO;
//import javax.imageio.stream.ImageInputStream;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.awt.image.renderable.RenderableImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;

public class QRCodeUtil {
    /*
     * 1、src路径（图片路径）
     * 2、二维码内容
     */

    private static String imgPath = "E:\\论文\\result.png";
//    private String content;

    //生成二维码
    /**
     * 加密: 文字信息 -> 二维码
     * 解密: 二维码信息 -> 文字信息
     */

//    //加密
//    public static void encoderQRCode(String content,String imgType,int size) throws Exception{
//        BufferedImage  bufImg = qRcodeCommon(content,imgType,size);
//        File file = new File(imgPath);
//        ImageIO.write(bufImg,imgType,file);//生成图片
//    }
//
//    //解密
//
//
//    public static BufferedImage qRcodeCommon(String content,String imgType,int size) throws Exception{
//
//
//        //字符串->布尔数组
//        Qrcode qrcode = new Qrcode();
//        //设置二维码的排错率:7% L、M、Q、H 30%
//        qrcode.setQrcodeErrorCorrect('M');
//        //二维码可存放信息类型:N、数字 A、数字+大写字母 B、都支持
//        qrcode.setQrcodeEncodeMode('B');
//        //尺寸：取值范围 1-40
//        qrcode.setQrcodeVersion(size);
//
//        int imgSize = 67 + 12*(size-1);
//
//        BufferedImage bufferedImage = null;
//        bufferedImage = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics = bufferedImage.createGraphics();//产生画板
//        graphics.setBackground(Color.WHITE);//背景色设为白色
//        graphics.clearRect(0,0,imgSize,imgSize);//初始化
//        graphics.setColor(Color.BLACK);
//        int pixoff = 2;
//        byte[] contentBytes = content.getBytes();//字节数组化
//        System.out.println(contentBytes.length);
//        boolean[][] calQrcode = qrcode.calQrcode(contentBytes);
//        for(int i=0;i<calQrcode.length;i++){
//            for(int j=0;j<calQrcode.length;j++){
//                if(calQrcode[i][j]){
//                    graphics.fillRect(j*3+pixoff,i*3+pixoff,3,3);
//                }
//
//            }
//        }
//
//        graphics.dispose();//释放空间
//        bufferedImage.flush();//清理
//        return bufferedImage;
//    }
//
//    public static void main(String[] args) {
//        try {
//            InputStream in = new FileInputStream("E:\\网页设计\\作业（2）\\image\\头像.jpg");
//            byte[] data = null;
//            data = new byte[in.available()];
//            in.read(data);
//            in.close();
//            BASE64Encoder encoder = new BASE64Encoder();
//            String source = encoder.encode(data);
//            QRCodeUtil.encoderQRCode("http://www.ultraer.icu/test1","png",7);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
