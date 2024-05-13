package com.github.supercoding.service;

import com.github.supercoding.repository.items.ElectronicStoreItemJpaRepository;
import com.github.supercoding.repository.items.ElectronicStoreItemRepository;
import com.github.supercoding.repository.items.ItemEntity;
import com.github.supercoding.repository.storeSales.StoreSales;
import com.github.supercoding.repository.storeSales.StoreSalesJpaRepository;
import com.github.supercoding.repository.storeSales.StoreSalesRepository;
import com.github.supercoding.service.exceptions.NotAcceptException;
import com.github.supercoding.service.exceptions.NotFoundException;
import com.github.supercoding.service.mapper.ItemMapper;
import com.github.supercoding.web.dto.items.BuyOrder;
import com.github.supercoding.web.dto.items.Item;
import com.github.supercoding.web.dto.items.Item;
import com.github.supercoding.web.dto.items.ItemBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElectronicStoreItemService {

    private final ElectronicStoreItemRepository electronicStoreItemRepository;

    private final ElectronicStoreItemJpaRepository electronicStoreItemJpaRepository;

    private final StoreSalesRepository storeSalesRepository;

    private final StoreSalesJpaRepository storeSalesJpaRepository;

    public List<Item> findAllItem() {
        List<ItemEntity> itemEntities =  electronicStoreItemJpaRepository.findAll();
        return itemEntities.stream().map(Item::new).collect(Collectors.toList());
    }


    public Integer saveItem(ItemBody itemBody) {

        ItemEntity itemEntity = ItemMapper.INSTANCE.idAndItemBodyToItemEntity(null, itemBody);
        ItemEntity itemEntityCreated;

        try {
            itemEntityCreated = electronicStoreItemJpaRepository.save(itemEntity);
        } catch(RuntimeException exception) {
            throw new NotAcceptException("Item을 저장하는 도중에 Error 가 발생하였습니다.");
        }
        return itemEntityCreated.getId();
    }

    public Item findItemById(String id) {
        Integer idInt = Integer.parseInt(id);
        ItemEntity itemEntity = electronicStoreItemJpaRepository.findById(idInt)
                .orElseThrow(() -> new NotFoundException("해당 ID: " + idInt + "의 Item을 찾을 수 없습니다."));
        Item item = new Item(itemEntity);
        return item;
    }

    public Item findItemByPathId(String id) {
        Integer idInt = Integer.parseInt(id);
        ItemEntity itemEntity = electronicStoreItemRepository.findItemById(idInt);
        Item item = new Item(itemEntity);
        return item;
    }

    public List<Item> findItemsByIds(List<String> ids) {

        List<ItemEntity> itemEntities = electronicStoreItemRepository.findAllItems();

        List<Item> itemsfounded = itemEntities.stream()
                .map(Item::new)
                .filter((item -> ids.contains(item.getId()))).collect(Collectors.toList());

        return itemsfounded;
    }

    public void deleteItem(String id) {
        Integer idInt = Integer.parseInt(id);
//        electronicStoreItemRepository.deleteItem(idInt);

        electronicStoreItemJpaRepository.deleteById(idInt);
    }

    @Transactional(transactionManager = "tmJpa1")
    public Item updateItem(String id, ItemBody itemBody) {
        Integer idInt = Integer.valueOf(id);
        ItemEntity itemEntityUpdated = electronicStoreItemJpaRepository.findById(idInt)
                .orElseThrow(() -> new NotFoundException("아무 Items 들을 찾을 수 없습니다."));

        itemEntityUpdated.setItemBody(itemBody); //자체 정의

//        ItemEntity itemEntityUpdated = electronicStoreItemRepository.updateItemEntity(Integer.valueOf(id), itemEntity);
//
//        Item itemUpdated = new Item(itemEntityUpdated);

        return ItemMapper.INSTANCE.itemEntityToItem(itemEntityUpdated);
    }


    @Transactional(transactionManager = "tm1")
    public Integer buyItems(BuyOrder buyOrder) {
        //로직 구현
        //1. BuyOrder에서 상품 Id와 수량을 얻는다.
        //2. 상품 조회하여 수량이 얼마나 있는지 확인한다.
        //3. 상품의 수량과 가격을 가지고 계산하여 총 가격을 구한다.
        //4. 상품의 재고에 기존 계산한 재고를 구매하는 수량을 뺀다.
        //5. 상품 사용한 재고 + 가격 만큼 가게 매상으로 올린다.
        //(단, 재고가 없거나 매장을 찾을 수 없으면 살 수 없다.) -> 몇개의 테이블을 유동적으로 쓰는 방법
        Integer itemId = buyOrder.getItemId();
        Integer itemNums = buyOrder.getItemNums();

        //NOTE: 일단 아이템을 불러와야하니까 ItemEntity 불러옴
        ItemEntity itemEntity = electronicStoreItemJpaRepository.findById(itemId)
                .orElseThrow(()->new NotFoundException("해당 이름의 Item을 찾을 수 없습니다."));
        if(itemEntity.getStoreId() == null) throw new RuntimeException ("매장을 찾을 수 없습니다");
        if(itemEntity.getStock() == null) throw new RuntimeException("상품의 재고가 없습니다");

        Integer successBuyItemNums;
        if(itemNums >= itemEntity.getStock()) successBuyItemNums = itemEntity.getStock(); //NOTE:  재고 만큼 살 수 있음
        else successBuyItemNums = itemNums;

        //NOTE: 총가격
        Integer totalPrice = successBuyItemNums * itemEntity.getPrice();

        //빼는거 DB에 반영 Item 재고 감소
        electronicStoreItemRepository.updateItemStock(itemId, itemEntity.getStock() - successBuyItemNums);


        StoreSales storeSales = storeSalesJpaRepository.findById(itemEntity.getStoreId())
                .orElseThrow(()-> new NotFoundException("요청하신 StoreId : " + itemEntity.getStoreId() + "에 해당하는 StoreSale 없습니다."));
        //매장 매상 추가 - > 매장 레포지토리가 필요하다. (레포지토리 입장에서는 update니까)

        storeSales.setAmount(storeSales.getAmount() + totalPrice);
//        storeSalesRepository.updateSalesAmount(itemEntity.getStoreId(), storeSales.getAmount() + totalPrice );
        return successBuyItemNums;

    }
}
