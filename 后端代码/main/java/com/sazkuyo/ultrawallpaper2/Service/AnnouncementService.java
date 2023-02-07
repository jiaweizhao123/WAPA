package com.sazkuyo.ultrawallpaper2.Service;

import com.sazkuyo.ultrawallpaper2.Entity.Announcement;
import com.sazkuyo.ultrawallpaper2.Mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    AnnouncementMapper announcementMapper;


    //查找所有公告
    public List<Announcement> queryAll() {
        return announcementMapper.queryAll();
    }

    //删除指定公告
    public int delete(int aid) {
        return announcementMapper.delete(aid);
    }

    //更新公告
    public int update(Announcement announcement) {
        return announcementMapper.update(announcement);
    }

    //插入公告
    public int insert(Announcement announcement) {
        return announcementMapper.insert(announcement);
    }

    //获取最新公告
    public Announcement getLater() {
        return announcementMapper.getLater();
    }
}
