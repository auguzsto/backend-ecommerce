package io.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.backend.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>{
}