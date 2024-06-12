package com.techwave.Models.Services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techwave.Models.Pojo.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByProductDescEquals(String productDesc);
	Product findByProductDescIs(String productDesc);
}
