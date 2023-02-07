package com.sazkuyo.ultrawallpaper2.Mapper;

import com.sazkuyo.ultrawallpaper2.Entity.visitor;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilMapper {
    int queryIpRecord(String ip);

    int insertIpRecord(String adr);

    int addCount(String ip);

    int queryVisitorCount();//查询访客总数
}
