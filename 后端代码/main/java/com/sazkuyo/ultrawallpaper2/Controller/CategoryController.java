package com.sazkuyo.ultrawallpaper2.Controller;

import com.sazkuyo.ultrawallpaper2.Entity.Category;
import com.sazkuyo.ultrawallpaper2.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("CategoryController")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryALl")
    public Object queryAll() {
        return categoryService.queryAll();
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryAllPhoneWallpaper")
    public Object queryAllPhoneWallpaper() {
        return categoryService.queryAllPhoneWallpaper();
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryByPage")
    public Object queryByPage(@RequestParam("page") int page) {
        return categoryService.queryByPage(page * 5, (page + 1) * 5);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("queryPhoneByPage")
    public Object queryPhoneByPage(@RequestParam("page") int page) {
        return categoryService.queryPhoneByPage(page * 5, (page + 1) * 5);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("insert")
    public Object insert(@RequestBody Category category) {
        return categoryService.insert(category);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("delete")
    public Object delete(@RequestBody Category category) {
        return categoryService.delete(category);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Category category) {
        return categoryService.update(category);
    }

}
