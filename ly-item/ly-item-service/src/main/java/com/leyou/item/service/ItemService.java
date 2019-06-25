package com.leyou.item.service;

import com.leyou.item.pojo.Item;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @ProjectName: leyou
 * @Author: Beryl
 * @Date: 2019/6/17 11:42 AM
 * @Version: 1.0
 * @Description: TODO
 */
@Service
public class ItemService {

    public Item saveItem(Item item){

        int id = new Random().nextInt(100);
        item.setId(id);
        return item;
    }
}