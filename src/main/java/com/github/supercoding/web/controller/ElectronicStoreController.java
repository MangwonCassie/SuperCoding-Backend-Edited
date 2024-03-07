package com.github.supercoding.web.controller;


import com.github.supercoding.service.ElectronicStoreItemService;
import com.github.supercoding.web.dto.items.BuyOrder;
import com.github.supercoding.web.dto.items.Item;
import com.github.supercoding.web.dto.items.ItemBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ElectronicStoreController {

    private final ElectronicStoreItemService electronicStoreItemService;
    private static int serialItemId = 1;
    private List<Item> items = new ArrayList<>(Arrays.asList(
            new Item (String.valueOf(serialItemId++), "Apple iPhone12", "Smartphone", 1490000, "A14 Bionic", "512GB"),
            new Item (String.valueOf(serialItemId++), "Galaxy S12 Ultra", "Smartphone", 1490000, "A14 Bionic", "512GB")
    ));


    @GetMapping("/items")
    public List<Item> findAllItem (){
        return electronicStoreItemService.findAllItem();
    }

    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody){
        Integer itemId = electronicStoreItemService.saveItem(itemBody);
        return "ID: " + itemId;
    }

    @GetMapping("/items/{id}")
    public Item findItemByPathId(@PathVariable String id ){
        return electronicStoreItemService.findItemByPathId(id);
    }

    @GetMapping("/items-query")
    public Item findItemByQueryId(@RequestParam("id") String id ){
        return electronicStoreItemService.findItemById(id);
    }

    @GetMapping("/items-queries")
    public List<Item> findItemByQueryId(@RequestParam("id") List<String> ids ){
        return electronicStoreItemService.findItemsByIds(ids);
    }

    @DeleteMapping("/items/{id}")
    public String deleteItemByPathId(@PathVariable String id){
        electronicStoreItemService.deleteItem(id);
        return "Object with id =" + id + "has been deleted";
    }

    @PutMapping("/items/{id}")
    public Item updateItem (@PathVariable String id, @RequestBody ItemBody itemBody) {
        return electronicStoreItemService.updateItem(id, itemBody);
    };

    //NOTE: storeSales 테이블 추가 후  http://localhost:8080/v3/api/items/buy
    @PostMapping("/items/buy")
    public String buyItem(@RequestBody BuyOrder buyOrder){
        Integer orderItemNums = electronicStoreItemService.buyItems(buyOrder);
        return "요청하신 Item 중 " +  orderItemNums + "개를 구매하였습니다.";
    }
}
