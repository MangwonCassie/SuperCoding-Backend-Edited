package com.github.supercoding.web.controller;

import com.github.supercoding.repository.ElectonicStoreItemRepository;
import com.github.supercoding.repository.ItemEntity;
import com.github.supercoding.service.ElectronicStoreItemService;
import com.github.supercoding.web.dto.Item;
import com.github.supercoding.web.dto.ItemBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ElectronicStoreController {

    private ElectronicStoreItemService electonicStoreItemService;


    public ElectronicStoreController(ElectronicStoreItemService electonicStoreItemService) {
        this.electonicStoreItemService = electonicStoreItemService;
    }

    private static int serialItemId = 1;
    private List<Item> items = new ArrayList<>(Arrays.asList(
            new Item (String.valueOf(serialItemId++), "Apple iPhone12", "Smartphone", 1490000, "A14 Bionic", "512GB"),
            new Item (String.valueOf(serialItemId++), "Galaxy S12 Ultra", "Smartphone", 1490000, "A14 Bionic", "512GB")
    ));


    @GetMapping("/items")
    public List<Item> findAllItem (){

        return electonicStoreItemService.findAllItem();
    }

    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody){



        Integer itemId = electonicStoreItemService.saveItem(itemBody);
        return "ID: " + itemId;
    }

    @GetMapping("/items/{id}")
    public Item findItemByPathId(@PathVariable String id ){
        Item  itemfounded = items.stream()
                                    .filter((item) -> item.getId().equals(id))
                                    .findFirst()
                                    .orElseThrow(()-> new RuntimeException());
        return itemfounded;
    }

    @GetMapping("/items-query")
    public Item fineItemByQueryId(@RequestParam("id") String id ){
        return electonicStoreItemService.findItemById(id);
    }

    @GetMapping("/items-queries")
    public List<Item> fineItemByQueryId(@RequestParam("id") List<String> ids ){

        return electonicStoreItemService.findItemsByIds(ids);


    }

    @DeleteMapping("/items/{id}")
    public String deleteItemByPathId(@PathVariable String id){
        electonicStoreItemService.deleteItem(id);
        return "Object with id =" + id + "has been deleted";
    }

        @PutMapping("/items/{id}")
        public Item updateItem (@PathVariable String id, @RequestBody ItemBody itemBody) {

            return electonicStoreItemService.updateItem(id, itemBody);
        };
}
