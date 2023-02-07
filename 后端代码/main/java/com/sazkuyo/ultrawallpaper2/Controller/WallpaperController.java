package com.sazkuyo.ultrawallpaper2.Controller;

import com.sazkuyo.ultrawallpaper2.Entity.*;
import com.sazkuyo.ultrawallpaper2.Service.WallpaperService;
import com.sazkuyo.ultrawallpaper2.Util.Auth;
import com.sazkuyo.ultrawallpaper2.Util.ObjectReflectUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("WallpaperController")
public class WallpaperController {

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
    private WallpaperService wallpaperService;
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryAll")
    public Object queryAll() {
        return wallpaperService.queryAll();
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryPhoneAllOrderByTime")
    public Object queryPhoneAllOrderByTime() {
        return wallpaperService.queryPhoneAllOrderByTime();
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryAllOrderByTime")
    public Object queryAllOrderByTime(@RequestParam("currentPage") int currentPage) {
        return wallpaperService.queryAllOrderByTime(currentPage * 12, 12);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryAllOrderByHeat")
    public Object queryAllOrderByHeat(@RequestParam("currentPage") int currentPage) {
        System.out.println(currentPage * 12);
        List<Wallpaper> lis = wallpaperService.queryAllOrderByHeat(currentPage * 12, 12);
        System.out.println(lis.size());
        return lis;
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryByCategory")
    public Object queryByCategory(@RequestParam("cid") int cid) {
        return wallpaperService.queryByCategory(cid);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryByCategoryOrderByTime")
    public Object queryByCategoryOrderByTime(@RequestParam("cid") int cid, @RequestParam("currentPage") int currentPage) {
        return wallpaperService.queryByCategoryOrderByTime(cid, currentPage * 12, 12);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryPhoneByCategoryOrderByTime")
    public Object queryPhoneByCategoryOrderByTime(@RequestParam("cid") int cid, @RequestParam("currentPage") int currentPage) {
        return wallpaperService.queryPhoneByCategoryOrderByTime(cid, currentPage * 12, 12);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryByCategoryOrderByHeat")
    public Object queryByCategoryOrderByHeat(@RequestParam("cid") int cid) {
        return wallpaperService.queryByCategoryOrderByHeat(cid);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("simpleQueryPhoneByCategory")
    public Object simpleQueryPhoneByCategory(@RequestParam("cid") int cid) {
        return wallpaperService.simpleQueryPhoneByCategory(cid);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("simpleQueryByCategory")
    public Object simpleQueryByCategory(@RequestParam("cid") int cid) {
        return wallpaperService.simpleQueryByCategory(cid);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryBySizeOrderByTime")
    public Object queryBySizeOrderByTime(@RequestParam("String") String size) {
        return wallpaperService.queryBySizeOrderByTime(size);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryBySizeOrderByHeat")
    public Object queryBySizeOrderByHeat(@RequestParam("String") String size) {
        return wallpaperService.queryBySizeOrderByHeat(size);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryBySize")
    public Object queryBySize(@RequestParam("String") String size) {
        return wallpaperService.queryBySize(size);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("delete")
    public Object delete(@RequestBody Wallpaper wallpaper) {
        return wallpaperService.delete(wallpaper);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("deletePhone")
    public Object deletePhone(@RequestBody Wallpaper wallpaper) {
        return wallpaperService.deletePhone(wallpaper);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object update(@RequestBody Wallpaper wallpaper) {
        return wallpaperService.update(wallpaper);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "updatePhone", method = RequestMethod.POST)
    public Object updatePhone(@RequestBody Wallpaper wallpaper) {
        return wallpaperService.updatePhone(wallpaper);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "searchByKeyWord")
    public Object searchByKeyWord(@RequestParam("keyword") String keyword, @RequestParam("currentPage") int currentPage) {
        System.out.println(currentPage);
        List<Wallpaper> li = wallpaperService.searchByKeyWord(keyword, currentPage * 12, 12);
        System.out.println(li);
        return li;
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "searchPhoneByKeyWord")
    public Object searchPhoneByKeyWord(@RequestParam("keyword") String keyword, @RequestParam("currentPage") int currentPage) {
        List<Wallpaper> li = wallpaperService.searchPhoneByKeyWord(keyword, currentPage * 12, 12);
        if (currentPage == 0) {
            if (wallpaperService.isRecordExist(keyword) == 0) {
                wallpaperService.insertRecord(keyword);
            } else {
                wallpaperService.addRecordNum(keyword);
            }
        }
        return li;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "getTopRecord")
    public Object getTopRecord() {
        List<SearchRecord> li = wallpaperService.getTopRecord();
        return li;
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Object insert(@RequestBody imgPre img) {
        System.out.println(img.getFileName());
        System.out.println(img);
        try {
            String pre = "";
            pre = UUID.randomUUID() + "";
            String suffix = "";
            System.out.println(img.getFileName().split("\\.")[1]);
            suffix = "." + img.getFileName().split("\\.")[1];
            String fileName = pre + suffix;
            pre = UUID.randomUUID() + "";
            String fileName2 = pre + suffix;
            System.out.println(fileName);
            System.out.println(fileName2);
            String url = "http://qiniu.ultraer.icu/" + fileName;
            String url2 = "http://qiniu.ultraer.icu/" + fileName2;
            Wallpaper wallpaper = new Wallpaper();
            wallpaper.setCid(img.getCid());
            wallpaper.setTag(img.getTags());
            ObjectReflectUtil.setObjectProperty(wallpaper, "pdate", new Date());
            System.out.println(url + "|||||||" + url2);
            wallpaper.setSrc(url);
            wallpaper.setSm_src(url2);
            System.out.println("compliment");
            if (wallpaperService.insert(wallpaper) == 1) {
                String accessKey = "8O1LRmpI-kRz0m6HBmMHufaCZj-6mNNzOfdsbDOQ";
                String secretKey = "I4f85ksf387I_9KfOclydUZQOwuszjExjArW_WMo";
                String bucket = "sazkuyo";
                Auth auth = Auth.create(accessKey, secretKey);
                String upToken = auth.uploadToken(bucket);
                System.out.println(upToken);
                token_msg t = new token_msg(upToken, url, url2);
                return t;
            } else {
                return "图片上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "insertUserWallpaper", method = RequestMethod.POST)
    public Object insertUserWallpaper(@RequestBody imgPre img) {
        try {
            String pre = "";
            pre = UUID.randomUUID() + "";
            String suffix = "";
            suffix = "." + img.getFileName().split("\\.")[1];
            String fileName = pre + suffix;
            pre = UUID.randomUUID() + "";
            String fileName2 = pre + suffix;
            System.out.println(fileName);
            System.out.println(fileName2);
            String url = "http://qiniu.ultraer.icu/" + fileName;
            String url2 = "http://qiniu.ultraer.icu/" + fileName2;
            userWallpaper uw = new userWallpaper();
            uw.setUid(img.getUid());
            uw.setTags(img.getTags());
            ObjectReflectUtil.setObjectProperty(uw, "create_time", new Date());
            System.out.println(url + "|||||||" + url2);
            uw.setSrc(url);
            uw.setSm_src(url2);
            System.out.println("compliment");
            if (wallpaperService.insertUserWallpaper(uw) == 1) {
                String accessKey = "8O1LRmpI-kRz0m6HBmMHufaCZj-6mNNzOfdsbDOQ";
                String secretKey = "I4f85ksf387I_9KfOclydUZQOwuszjExjArW_WMo";
                String bucket = "sazkuyo";
                Auth auth = Auth.create(accessKey, secretKey);
                String upToken = auth.uploadToken(bucket);
                System.out.println(upToken);
                token_msg t = new token_msg(upToken, url, url2);
                return t;
            } else {
                return "图片上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "insertPhone", method = RequestMethod.POST)
    public Object insertPhone(@RequestBody imgPre img) {
        System.out.println(img.getFileName());
        System.out.println(img);
        try {
            String pre = "";
            pre = UUID.randomUUID() + "";
            String suffix = "";
            System.out.println(img.getFileName().split("\\.")[1]);
            suffix = "." + img.getFileName().split("\\.")[1];
            String fileName = pre + suffix;
            pre = UUID.randomUUID() + "";
            String fileName2 = pre + suffix;
            System.out.println(fileName);
            System.out.println(fileName2);
            String url = "http://qiniu.ultraer.icu/" + fileName;
            String url2 = "http://qiniu.ultraer.icu/" + fileName2;
            Wallpaper wallpaper = new Wallpaper();
            wallpaper.setCid(img.getCid());
            wallpaper.setTag(img.getTags());
            ObjectReflectUtil.setObjectProperty(wallpaper, "pdate", new Date());
            System.out.println(url + "|||||||" + url2);
            wallpaper.setSrc(url);
            wallpaper.setSm_src(url2);
            System.out.println("compliment");
            if (wallpaperService.insertPhone(wallpaper) == 1) {
                String accessKey = "8O1LRmpI-kRz0m6HBmMHufaCZj-6mNNzOfdsbDOQ";
                String secretKey = "I4f85ksf387I_9KfOclydUZQOwuszjExjArW_WMo";
                String bucket = "sazkuyo";
                Auth auth = Auth.create(accessKey, secretKey);
                String upToken = auth.uploadToken(bucket);
                System.out.println(upToken);
                token_msg t = new token_msg(upToken, url, url2);
                return t;
            } else {
                return "图片上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "queryAllByLimit")
    public Object queryAllByLimit(int currentPage) {
        List<Wallpaper> lis = wallpaperService.queryAllByLimit(currentPage * 12, 12);
        return lis;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "imgToBase64")
    public String imgToBase64(String url) {
        return wallpaperService.imgToBase64(url);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "picData")
    public int[] picData() {
        int picCount = wallpaperService.getWallpaperCount();
        int maxHeat = wallpaperService.getMaxHeat();
        int allHeat = wallpaperService.getAllHeat();
        int[] da = {picCount, maxHeat, allHeat};
        return da;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "getRandomWallpaper")
    public Object getRandomWallpaper(@RequestParam("pid") int pid) {
        System.out.println(pid);
        Wallpaper wallpaper = wallpaperService.getRandomWallpaper(pid);
        if (wallpaper == null) {
            wallpaper = wallpaperService.getRandomWallpaper(pid);
        }
        return wallpaper;
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "getRandomPhoneWallpaper")
    public Object getRandomPhoneWallpaper(@RequestParam("pid") int pid) {
        System.out.println(pid);
        Wallpaper wallpaper = wallpaperService.getRandomPhoneWallpaper(pid);
        if (wallpaper == null) {
            wallpaper = wallpaperService.getRandomPhoneWallpaper(pid);
        }
        return wallpaper;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "getRandomWallpaperByPhone")
    public Object getRandomWallpaperByPhone(@RequestParam("pid") int pid) {
        System.out.println(pid);
        Wallpaper wallpaper = wallpaperService.getRandomWallpaperByPhone(pid);
        if (wallpaper == null) {
            wallpaper = wallpaperService.getRandomWallpaperByPhone(pid);
        }
        return wallpaper;
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "allow")
    public Object allow(userWallpaper uw) {
        return wallpaperService.allow(uw);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "queryUWByHeat")
    public Object queryUWByHeat(@RequestParam("currentPage") int currentPage) {
        return wallpaperService.queryUWByHeat(currentPage * 9, 9);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "getAll")
    public Object getAll(@RequestParam("currentPage") int currentPage) {
        return wallpaperService.getAll(currentPage * 9, 9);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "liked")
    public Object liked(@RequestParam("id") int id) {
        return wallpaperService.liked(id);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "deleteUw")
    public Object deleteUw(@RequestParam("id") int id) {
        return wallpaperService.deleteUw(id);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "updateUw")
    public Object updateUw(@RequestBody userWallpaper uw) {
        System.out.println(uw);
        return wallpaperService.updateUw(uw);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "queryUWByTime")
    public Object queryUWByTime(@RequestParam("currentPage") int currentPage) {
        return wallpaperService.queryUWByTime(currentPage * 9, 9);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "favorite")
    public String favorite(HttpServletRequest req, @RequestParam("pid") int pid) {
        String ip = req.getRemoteAddr();
        if (wallpaperService.isFavorited(ip, pid) == 1) {
            return "不能重复操作";
        } else {
            if (wallpaperService.favorite(pid) == 1) {
                wallpaperService.create_record(ip, pid);
                return "success";
            } else {
                return "error";
            }
        }
    }


}
