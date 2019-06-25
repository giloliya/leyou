package com.leyou.item.web;

import com.leyou.item.pojo.CategoryDTO;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Author: Beryl
 * @Date: 2019/6/19 10:35 PM
 * @Version: 1.0
 * @Description: TODO
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
/*业务的分析方式
*1、 接口的声明部分（Controller的handler声明，对外提供的可访问的api，方法）
*    请求方式；请求路径； 请求参数 ；返回值：对象/List，其实是DTO（VO被忽略）
*
* 业务逻辑部分
*   得到什么参数：父类目id
*   返回什么数据：父类下的子类集合
*   两者关系：
*/

    /**
     * 根据parent_id查询子业务
     * @param pid
     * @return
     */

    @GetMapping("/of/parent")
    public ResponseEntity<List<CategoryDTO>> queryByParentId(
            @RequestParam("pid") Long pid) {
        List<CategoryDTO> list = categoryService.queryCategoryByPid(pid);

        return ResponseEntity.ok(list);
    }

}

