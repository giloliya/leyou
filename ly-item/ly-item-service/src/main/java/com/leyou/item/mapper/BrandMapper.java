package com.leyou.item.mapper;

import com.leyou.item.entity.Brand;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {

    /**
     * 新增商品分类和品牌中间表数据
     * @param ids 商品分类id集合
     * @param bid 品牌id
     * @return 新增的条数
     */
    int insertCategoryBrand(@Param("bid") Long bid, @Param("ids") List<Long> ids);



}