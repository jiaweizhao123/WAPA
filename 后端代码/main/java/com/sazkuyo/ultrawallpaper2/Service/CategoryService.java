package com.sazkuyo.ultrawallpaper2.Service;

import com.sazkuyo.ultrawallpaper2.Entity.Category;
import com.sazkuyo.ultrawallpaper2.Mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryAll() {
        return categoryMapper.queryAll();
    }

    public List<Category> queryByPage(int begin, int end) {
        return categoryMapper.queryByPage(begin, end);
    }

    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    public int delete(Category category) {
        return categoryMapper.delete(category);
    }

    public int update(Category category) {
        return categoryMapper.update(category);
    }

    public List<Category> queryAllPhoneWallpaper() {
        return categoryMapper.queryAllPhoneWallpaper();
    }

    public List<Category> queryPhoneByPage(int begin, int end) {
        return categoryMapper.queryPhoneByPage(begin, end);
    }
}
