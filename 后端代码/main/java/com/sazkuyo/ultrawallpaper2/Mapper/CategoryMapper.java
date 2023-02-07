package com.sazkuyo.ultrawallpaper2.Mapper;

import com.sazkuyo.ultrawallpaper2.Entity.Category;
import com.sazkuyo.ultrawallpaper2.Entity.Wallpaper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    List<Category> queryAll();//查找所有的类别

    List<Category> queryAllPhoneWallpaper();//查找粟所有手机壁纸类别

    int insert(Category category);//插入类别

    int delete(Category category);//删除类别

    int update(Category category);//修改类别

    List<Category> queryByPage(int begin, int end);//查找指定页数的类别

    List<Category> queryPhoneByPage(int begin, int end);//查找指定页数的手机图片类别
}
