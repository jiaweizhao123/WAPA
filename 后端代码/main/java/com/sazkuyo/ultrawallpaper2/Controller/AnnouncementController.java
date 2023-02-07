package com.sazkuyo.ultrawallpaper2.Controller;

import com.sazkuyo.ultrawallpaper2.Entity.Announcement;
import com.sazkuyo.ultrawallpaper2.Service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("AnnouncementController")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryAll")
    public Object queryAll() {
        return announcementService.queryAll();
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Object insert(@RequestBody Announcement announcement) {
        announcement.setCreate_time(new Date());
        return announcementService.insert(announcement);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("delete")
    public Object delete(@RequestParam("aid") int aid) {
        return announcementService.delete(aid);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Announcement announcement) {
        return announcementService.update(announcement);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("getLater")
    public Object getLater() {
        return announcementService.getLater();
    }

}
