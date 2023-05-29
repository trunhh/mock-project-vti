package com.trunh.service;

import java.util.List;

import com.trunh.entity.Product;
import com.trunh.form.ProductFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Product getProductById(int id);
    Page<Product> getProductList(Pageable pageable, ProductFilterForm productFilterForm);
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);

}
