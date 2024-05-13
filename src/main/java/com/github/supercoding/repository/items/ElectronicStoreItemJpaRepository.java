package com.github.supercoding.repository.items;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicStoreItemJpaRepository extends JpaRepository<ItemEntity, Integer> {
}
