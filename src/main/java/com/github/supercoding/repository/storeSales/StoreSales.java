package com.github.supercoding.repository.storeSales;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class StoreSales {
    private Integer id;
    private String storeName;
    private Integer amount;
}
