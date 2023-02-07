package com.sazkuyo.ultrawallpaper2.Service;

import com.sazkuyo.ultrawallpaper2.Entity.SearchRecord;
import com.sazkuyo.ultrawallpaper2.Entity.Wallpaper;
import com.sazkuyo.ultrawallpaper2.Entity.userWallpaper;
import com.sazkuyo.ultrawallpaper2.Mapper.WallpaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class WallpaperService {
    /*
        List<Wallpaper> queryAll();//查找所有图片
        List<Wallpaper> queryByCategory(int cid);//根据分类id查找所属图片
        List<Wallpaper> queryBySize(String size);//根据尺寸查找图片
        int insert(Wallpaper wallpaper);//插入图片
        int delete(Wallpaper wallpaper);//删除图片
        int update(Wallpaper wallpaper);//修改图片信息
        List<Wallpaper> queryAllOrderByTime();//根据时间降序查找图片
        List<Wallpaper> queryByCategoryOrderByTime(int cid);//根据时间降序查找特定类别图片
        List<Wallpaper> queryBySizeOrderByTime(String size);//根据时间降序查找特定尺寸图片
        List<Wallpaper> queryAllOrderByHeat();//根据热度降序查找所有图片
        List<Wallpaper> queryByCategoryOrderByHeat(int cid);//根据热度降序查找特定类别图片
        List<Wallpaper> queryBySizeOrderByHeat(String size);//根据热度降序查找特定尺寸图片
     */

    @Autowired
    private WallpaperMapper wallpaperMapper;

    public List<Wallpaper> queryAll() {
        return wallpaperMapper.queryAll();
    }

    public List<Wallpaper> queryByCategory(int cid) {
        return wallpaperMapper.queryByCategory(cid);
    }

    public List<Wallpaper> queryBySize(String size) {
        return wallpaperMapper.queryBySize(size);
    }

    public int insert(Wallpaper wallpaper) {
        return wallpaperMapper.insert(wallpaper);
    }

    public int delete(Wallpaper wallpaper) {
        return wallpaperMapper.delete(wallpaper);
    }

    public int update(Wallpaper wallpaper) {
        return wallpaperMapper.update(wallpaper);
    }

    public List<Wallpaper> queryAllOrderByTime(int start, int end) {
        return wallpaperMapper.queryAllOrderByTime(start, end);
    }

    public List<Wallpaper> queryByCategoryOrderByTime(int cid, int start, int end) {
        return wallpaperMapper.queryByCategoryOrderByTime(cid, start, end);
    }

    public List<Wallpaper> queryBySizeOrderByTime(String size) {
        return wallpaperMapper.queryBySizeOrderByTime(size);
    }

    public List<Wallpaper> queryAllOrderByHeat(int start, int end) {
        return wallpaperMapper.queryAllOrderByHeat(start, end);
    }

    public List<Wallpaper> queryByCategoryOrderByHeat(int cid) {
        return wallpaperMapper.queryByCategoryOrderByHeat(cid);
    }

    public List<Wallpaper> queryBySizeOrderByHeat(String size) {
        return wallpaperMapper.queryBySizeOrderByHeat(size);
    }

    public List<Wallpaper> queryAllByLimit(int start, int end) {
        return wallpaperMapper.queryAllByLimit(start, end);
    }

    public List<Wallpaper> searchByKeyWord(String keyword, int start, int end) {
        return wallpaperMapper.searchByKeyWord(keyword, start, end);
    }

    public List<Wallpaper> searchPhoneByKeyWord(String keyword, int start, int end) {
        return wallpaperMapper.searchPhoneByKeyWord(keyword, start, end);
    }

    public int favorite(int pid) {
        return wallpaperMapper.favorite(pid);
    }

    public int isFavorited(String ip, int pid) {
        return wallpaperMapper.isFavorited(ip, pid);
    }

    public int create_record(String ip, int pid) {
        return wallpaperMapper.create_record(ip, pid);
    }

    public Wallpaper getRandomWallpaper(int pid) {
        return wallpaperMapper.getRandomWallpaper(pid);
    }

    public String imgToBase64(String url) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] buffer = null;
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
        return Base64Utils.encodeToString(buffer);
    }


    //插入手机图片
    public int insertPhone(Wallpaper wallpaper) {
        return wallpaperMapper.insertPhone(wallpaper);
    }

    //删除手机图片
    public int deletePhone(Wallpaper wallpaper) {
        return wallpaperMapper.deletePhone(wallpaper);
    }

    //更新手机图片
    public int updatePhone(Wallpaper wallpaper) {
        return wallpaperMapper.updatePhone(wallpaper);
    }

    //查询所有手机图片
    public List<Wallpaper> queryPhoneAllOrderByTime() {
        return wallpaperMapper.queryPhoneAllOrderByTime();
    }

    //根据时间降序查找特定类别手机图片
    public List<Wallpaper> queryPhoneByCategoryOrderByTime(int cid, int start, int end) {
        return wallpaperMapper.queryPhoneByCategoryOrderByTime(cid, start, end);
    }

    //根据部分展示特定类别手机图片
    public List<Wallpaper> simpleQueryPhoneByCategory(int cid) {
        return wallpaperMapper.simpleQueryPhoneByCategory(cid);
    }

    //根据部分展示特定类别图片
    public List<Wallpaper> simpleQueryByCategory(int cid) {
        return wallpaperMapper.simpleQueryByCategory(cid);
    }

    //查找是否有搜索记录
    public int isRecordExist(String keyword) {
        return wallpaperMapper.isRecordExist(keyword);
    }

    //插入搜索记录
    public int insertRecord(String keyword) {
        return wallpaperMapper.insertRecord(keyword);
    }

    //增加搜索次数
    public int addRecordNum(String keyword) {
        return wallpaperMapper.addRecordNum(keyword);
    }

    public List<SearchRecord> getTopRecord() {
        return wallpaperMapper.getTopRecord();
    }

    public int getWallpaperCount() {
        return wallpaperMapper.getWallpaperCount();
    }

    public int getMaxHeat() {
        return wallpaperMapper.getMaxHeat();
    }

    public int getAllHeat() {
        return wallpaperMapper.getAllHeat();
    }

    public Wallpaper getRandomPhoneWallpaper(int pid) {
        return wallpaperMapper.getRandomPhoneWallpaper(pid);
    }


    public Wallpaper getRandomWallpaperByPhone(int pid) {
        return wallpaperMapper.getRandomWallpaperByPhone(pid);
    }


    public int insertUserWallpaper(userWallpaper uw) {
        return wallpaperMapper.insertUserWallpaper(uw);
    }


    //用户上传图片审核通过
    public int allow(userWallpaper uw) {
        return wallpaperMapper.allow(uw);
    }

    //用户点赞
    public int liked(int id) {
        return wallpaperMapper.liked(id);
    }

    //获取所有用户推荐图片
    public List<userWallpaper> getAll(int start, int end) {
        return wallpaperMapper.getAll(start, end);
    }

    //获取审核通过的所有用户推荐图片
    public List<userWallpaper> queryLimitByRecommend(int start, int end) {
        return wallpaperMapper.queryLimitByRecommend(start, end);
    }

    //返回时间降序的用户推荐图片
    public List<userWallpaper> queryUWByTime(int start, int end) {
        return wallpaperMapper.queryUWByTime(start, end);
    }

    //按热度降序返回用户推荐图片
    public List<userWallpaper> queryUWByHeat(int start, int end) {
        return wallpaperMapper.queryUWByHeat(start, end);
    }

    //删除指定用户推荐图片
    public int deleteUw(int uid) {
        return wallpaperMapper.deleteUw(uid);
    }

    //更新用户推荐图片
    public int updateUw(userWallpaper uw) {
        return wallpaperMapper.updateUw(uw);
    }

}
