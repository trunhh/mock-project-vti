package com.trunh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.trunh.entity.Product;
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>{

}
