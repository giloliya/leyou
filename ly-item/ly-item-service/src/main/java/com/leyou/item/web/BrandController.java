package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.BrandDTO;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<BrandDTO>> queryBrandByPage(

        @RequestParam(value = "key",required = false) String key,
        @RequestParam(value = "page", defaultValue =  "1") Integer page,
        @RequestParam(value = "rows", defaultValue =  "5") Integer rows,
        @RequestParam(value = "sortBy", required = false) String sortBy,
        @RequestParam(value = "desc", defaultValue = "true") Boolean desc
   ){
        System.out.println(1);
    return ResponseEntity.ok(brandService.queryByPage(key,page,rows,sortBy,desc));
    }

    /**
     * add brand
     * @param brandDTO brand object
     * @param cids id relate to brand
     * @return null
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(BrandDTO brandDTO,
                                          @RequestParam("cids") List<Long> cids){
        System.out.println("cids = " + cids);
        brandService.saveBrand(brandDTO,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}