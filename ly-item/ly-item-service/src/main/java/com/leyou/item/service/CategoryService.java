package com.leyou.item.service;





import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.item.entity.Category;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @ProjectName: leyou
 * @Author: Beryl
 * @Date: 2019/6/19 10:33 PM
 * @Version: 1.0
 * @Description: TODO
 */
@Service
public class CategoryService  {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDTO> queryCategoryByPid(Long pid) {

        Category category = new Category();
        category.setParentId(pid);

        List<Category> list = categoryMapper.select(category);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        // 使用自定义工具类，把Category集合转为DTO的集合
       return BeanHelper.copyWithCollection(list, CategoryDTO.class);
    }


}
