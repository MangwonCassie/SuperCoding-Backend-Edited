package com.github.supercoding.service.mapper;

import com.github.supercoding.repository.items.ItemEntity;
import com.github.supercoding.web.dto.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    // 싱글톤
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    // 메소드 정의 부분 (Item 클래스 생성자 보면서 참고하면서 ItemEntity가 들어와서 Item만드는 거니까)
    Item itemEntityToItem(ItemEntity itemEntity);


}
