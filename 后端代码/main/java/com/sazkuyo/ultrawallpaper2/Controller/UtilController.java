package com.sazkuyo.ultrawallpaper2.Controller;

import com.sazkuyo.ultrawallpaper2.Service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("util")
public class UtilController {
    @Autowired
    private UtilService utilService;

    @RequestMapping("visit")
    public void addRecord(HttpServletRequest req, HttpServletResponse res) {
        String adr = req.getRemoteAddr();
        if (utilService.queryIpRecord(adr) == 0) {
            utilService.insertIpRecord(adr);
        } else {
            utilService.addCount(adr);
        }
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "queryVisitorCount")
    public int queryVisitorCount() {
        return utilService.queryVisitorCount();
    }
}
