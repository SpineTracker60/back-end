package com.spinetracker.spinetracker.domain.board.command.domain.repository;

import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
