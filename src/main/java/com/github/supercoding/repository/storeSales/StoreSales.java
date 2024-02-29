package com.github.supercoding.repository.storeSales;

import java.util.Objects;

public class StoreSales {
    private Integer id;
    private String storeName;
    private Integer amount;

    public StoreSales(Integer id, String storeName, Integer amount) {
        this.id = id;
        this.storeName = storeName;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreSales that = (StoreSales) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}