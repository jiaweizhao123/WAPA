package com.sazkuyo.ultrawallpaper2.Mapper;

import com.sazkuyo.ultrawallpaper2.Entity.SearchRecord;
import com.sazkuyo.ultrawallpaper2.Entity.Wallpaper;
import com.sazkuyo.ultrawallpaper2.Entity.userWallpaper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WallpaperMapper {

    List<Wallpaper> queryAll();//查找所有图片

    List<Wallpaper> queryByCategory(int cid);//根据分类id查找所属图片

    List<Wallpaper> queryBySize(String size);//根据尺寸查找图片

    int insert(Wallpaper wallpaper);//插入图片

    int delete(Wallpaper wallpaper);//删除图片

    int update(Wallpaper wallpaper);//修改图片信息

    List<Wallpaper> queryAllOrderByTime(int start, int end);//根据时间降序查找图片

    List<Wallpaper> queryByCategoryOrderByTime(int cid, int start, int end);//根据时间降序查找特定类别图片

    List<Wallpaper> queryBySizeOrderByTime(String size);//根据时间降序查找特定尺寸图片

    List<Wallpaper> queryAllOrderByHeat(int start, int end);//根据热度降序查找所有图片

    List<Wallpaper> queryByCategoryOrderByHeat(int cid);//根据热度降序查找特定类别图片

    List<Wallpaper> queryBySizeOrderByHeat(String size);//根据热度降序查找特定尺寸图片    

    int getAllCount();//获取所有图片数量

    int getCountByCategory();//获取分类数量

    List<Wallpaper> queryAllByLimit(int start, int end);//根据页数返回图片

    String imgToBase64(String url);//将图片转为base64

    List<Wallpaper> searchByKeyWord(String keyword, int start, int end);//关键字模糊搜索

    int favorite(int pid);//喜爱图片

    int isFavorited(String ip, int pid);//查询用户是否多次投票

    int create_record(String ip, int pid);//创建用户喜爱纪录

    Wallpaper getRandomWallpaper(int pid);//随机取图

    Wallpaper getRandomWallpaperByPhone(int pid);//手机随机取图

    Wallpaper getRandomPhoneWallpaper(int pid);//手机随机取图

    int insertPhone(Wallpaper wallpaper);//插入手机图片

    int deletePhone(Wallpaper wallpaper);//删除手机图片

    int updatePhone(Wallpaper wallpaper);//更新手机图片

    List<Wallpaper> queryPhoneAllOrderByTime();//查询所有手机图片

    List<Wallpaper> queryPhoneByCategoryOrderByTime(int cid, int start, int end);//根据时间降序查找特定类别手机图片

    List<Wallpaper> simpleQueryPhoneByCategory(int cid);//根据部分展示特定类别手机图片

    List<Wallpaper> simpleQueryByCategory(int cid);//根据部分展示特定类别图片

    List<Wallpaper> searchPhoneByKeyWord(String keyword, int start, int end);//关键字模糊搜索

    int isRecordExist(String keyword);//查找是否有搜索记录

    int insertRecord(String keyword);//插入搜索记录

    int addRecordNum(String keyword);//增加搜索次数

    List<SearchRecord> getTopRecord();//返回热门搜索记录

    int getWallpaperCount();//获取图片总数

    int getMaxHeat();//获取最高热度

    int getAllHeat();//获取总热度

    int insertUserWallpaper(userWallpaper uw);//用户上传推荐图片

    int allow(userWallpaper uw);//用户上传图片审核通过

    int liked(int id);//用户点赞

    List<userWallpaper> getAll(int start, int end);//获取所有用户推荐图片

    List<userWallpaper> queryLimitByRecommend(int start, int end);//获取审核通过的用户推荐图片

    List<userWallpaper> queryUWByTime(int start, int end);//返回时间降序的用户推荐图片;

    List<userWallpaper> queryUWByHeat(int start, int end);//按热度降序返回用户推荐图片

    int deleteUw(int uid);//删除指定用户推荐图片

    int updateUw(userWallpaper uw);//更新用户推荐图片

}
