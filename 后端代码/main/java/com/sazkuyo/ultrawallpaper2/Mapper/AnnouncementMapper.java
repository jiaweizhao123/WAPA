package com.sazkuyo.ultrawallpaper2.Mapper;

import com.sazkuyo.ultrawallpaper2.Entity.Announcement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementMapper {
    List<Announcement> queryAll();//查找所有公告

    int delete(int aid);//删除指定公告

    int update(Announcement announcement);//更新公告

    int insert(Announcement announcement);//插入公告

    Announcement getLater();//获取最新公告
}
