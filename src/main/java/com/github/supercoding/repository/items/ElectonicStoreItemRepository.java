package com.github.supercoding.repository.items;

import java.util.List;

public interface ElectonicStoreItemRepository {


    List<ItemEntity> findAllItems();

    Integer saveItem(ItemEntity itemEntity);

    ItemEntity updateItemEntity(Integer integer, ItemEntity itemEntity);
    void deleteItem(int parseInt);


    ItemEntity findItemById(Integer idInt);

}
