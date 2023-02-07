package com;

import com.sazkuyo.ultrawallpaper2.Entity.Wallpaper;
import com.sazkuyo.ultrawallpaper2.Util.ObjectReflectUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] buffer = null;
        String url = "http://42.240.141.211/wallpaper/fc04744c-3bb9-43dc-a3d0-5ca6a5ca8764.jpg";
        try {
            //判断网络链接图片文件/本地目录图片文件
            if (url.startsWith("http://") || url.startsWith("https://")) {
                // 创建URL
                URL con = new URL(url);
                // 创建链接
                HttpURLConnection conn = (HttpURLConnection) con.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                inputStream = conn.getInputStream();
                outputStream = new ByteArrayOutputStream();
                // 将内容读取内存中
                buffer = new byte[1024];
                int len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                buffer = outputStream.toByteArray();
            } else {
                inputStream = new FileInputStream(url);
                int count = 0;
                while (count == 0) {
                    count = inputStream.available();
                }
                buffer = new byte[count];
                inputStream.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // 关闭inputStream流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // 关闭outputStream流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Base64Utils.encodeToString(buffer));
    }

}
