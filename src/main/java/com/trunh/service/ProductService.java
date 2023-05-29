package com.trunh.service;

import com.trunh.entity.Product;
import com.trunh.form.ProductFilterForm;
import com.trunh.repository.IProductRepository;
import com.trunh.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository pRepository;
    @Override
    public Product getProductById(int id){
        return pRepository.findById(id).orElse(null);
    };

    @Override
    public Page<Product> getProductList(Pageable pageable, ProductFilterForm productFilterForm) {
        Specification<Product> where = ProductSpecification.buildWhere(productFilterForm);
        return pRepository.findAll(where,pageable);
    };
    @Override
    public void createProduct(Product product) {
        pRepository.save(product);
    };
    public void updateProduct(Product product) {
        pRepository.save(product);
    }
    public void deleteProduct(int id) {
        pRepository.deleteById(id);
    };
}
