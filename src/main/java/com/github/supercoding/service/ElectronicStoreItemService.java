package com.github.supercoding.service;

import com.github.supercoding.repository.ElectonicStoreItemRepository;
import com.github.supercoding.repository.ItemEntity;
import com.github.supercoding.web.dto.Item;
import com.github.supercoding.web.dto.ItemBody;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectronicStoreItemService {


    private ElectonicStoreItemRepository electronicStoreRepository;

    public ElectronicStoreItemService(ElectonicStoreItemRepository electronicStoreRepository) {
        this.electronicStoreRepository = electronicStoreRepository;
    }

    public List<Item> findAllItem() {
        List<ItemEntity> itemEntities =  electronicStoreRepository.findAllItems();
        return itemEntities.stream().map(Item::new).collect(Collectors.toList());
    }

    public Integer saveItem(ItemBody itemBody) {
        ItemEntity itemEntity = new ItemEntity(null, itemBody.getName(), itemBody.getType(),
                itemBody.getPrice(), itemBody.getSpec().getCpu(), itemBody.getSpec().getCapacity());
        return electronicStoreRepository.saveItem(itemEntity);
    }

    public Item findItemById(String id) {
        Integer idInt = Integer.parseInt(id);
        ItemEntity itemEntity = electronicStoreRepository.findItemById(idInt);
        Item item = new Item(itemEntity);
        return item;
    }

    public Item findItemByPathId(String id) {
        Integer idInt = Integer.parseInt(id);
        ItemEntity itemEntity = electronicStoreRepository.findItemById(idInt);
        Item item = new Item(itemEntity);
        return item;
    }

    public List<Item> findItemsByIds(List<String> ids) {

        List<ItemEntity> itemEntities = electronicStoreRepository.findAllItems();

        List<Item> itemsfounded = itemEntities.stream()
                .map(Item::new)
                .filter((item -> ids.contains(item.getId()))).collect(Collectors.toList());

        return itemsfounded;
    }

    public void deleteItem(String id) {
        Integer idInt = Integer.parseInt(id);
        electronicStoreRepository.deleteItem(idInt);
    }

    public Item updateItem(String id, ItemBody itemBody) {
        Integer idInt = Integer.valueOf(id);
        ItemEntity itemEntity = new ItemEntity(idInt, itemBody.getName(), itemBody.getType(), itemBody.getPrice(),
                itemBody.getSpec().getCpu(), itemBody.getSpec().getCapacity());

        ItemEntity itemEntityUpdated = electronicStoreRepository.updateItemEntity(Integer.valueOf(id), itemEntity);

        Item itemUpdated = new Item(itemEntityUpdated);

        return itemUpdated;
    }


}
