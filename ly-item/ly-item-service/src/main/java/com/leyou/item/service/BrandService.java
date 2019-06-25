package com.leyou.item.service;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.common.vo.PageResult;
import com.leyou.item.entity.Brand;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.BrandDTO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Author: Beryl
 * @Date: 2019/6/21 3:47 PM
 * @Version: 1.0
 * @Description: TODO
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<BrandDTO> queryByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc){

        PageHelper.startPage(page,rows);

        Example example = new Example(Brand.class);

        if(StringUtils.isNotBlank(key)){
            example.createCriteria().
                    orLike("name", "%"  +key+ "%")
                    .orEqualTo("letter", key );
        }
        if(StringUtils.isNotBlank(sortBy)){
           String orderByClause = sortBy + (desc ? " Desc": " Asc");
           example.setOrderByClause(orderByClause);
        }
        List<Brand> list = brandMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        brandMapper.selectByExample(example);

        PageInfo<Brand> info = new PageInfo<>(list);
        Long total = info.getTotal();
        List<BrandDTO> items = BeanHelper.copyWithCollection(list, BrandDTO.class);
        return new PageResult<>(total, items);
    }

    /**
     * 添加brand
     * @param brandDTO
     * @param ids
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveBrand(BrandDTO brandDTO, List<Long> ids) {

        Brand brand =  BeanHelper.copyProperties(brandDTO,Brand.class);
        int count = brandMapper.insertSelective(brand);
        if(count != 1){
           throw new LyException(ExceptionEnum.INSERT_OPERATION_FAIL);
        }

        count = brandMapper.insertCategoryBrand(brand.getId(),ids);
        if(count!= ids.size()){
           throw new LyException(ExceptionEnum.INSERT_OPERATION_FAIL);
        }
    }
}
