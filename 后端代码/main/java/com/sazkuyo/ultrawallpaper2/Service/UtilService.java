package com.sazkuyo.ultrawallpaper2.Service;

import com.sazkuyo.ultrawallpaper2.Entity.visitor;
import com.sazkuyo.ultrawallpaper2.Mapper.UtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UtilService implements UtilMapper {

    @Autowired
    private UtilMapper utilMapper;

    @Override
    public int queryIpRecord(String ip) {
        return utilMapper.queryIpRecord(ip);
    }

    @Override
    public int insertIpRecord(String adr) {
        return utilMapper.insertIpRecord(adr);
    }

    @Override
    public int addCount(String ip) {
        return utilMapper.addCount(ip);
    }

    @Override
    public int queryVisitorCount() {
        return utilMapper.queryVisitorCount();
    }


}
