package com.trunh.controller;

import com.trunh.dto.ProductDTO;
import com.trunh.entity.Product;
import com.trunh.form.ProductCreateForm;
import com.trunh.form.ProductFilterForm;
import com.trunh.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable(name = "id") int id) {
        Product product = productService.getProductById(id);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    @GetMapping()
    public Page<ProductDTO> getProductList(Pageable pageable, ProductFilterForm pFF) {
        System.out.println("Product paging: ");
        System.out.println("pFF: " + pFF.toString());
        Page<Product> productPage = productService.getProductList(pageable, pFF);
        List<ProductDTO> productDTOList = modelMapper.map(productPage.getContent(), new TypeToken<List<ProductDTO>>(){}.getType());
        return new PageImpl(productDTOList, pageable, productPage.getTotalElements());

    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductCreateForm cpf) {
        System.out.println("Create product: ");
        System.out.println(cpf.toString());

        Product product = modelMapper.map(cpf, Product.class);
        productService.createProduct(product);
        return  ResponseEntity.ok().body("Created product successfully!");
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestParam(name = "id") int id, @RequestBody @Valid ProductCreateForm cpf) {
        System.out.println("Update product: ");
        System.out.println(cpf.toString());

        Product product = modelMapper.map(cpf, Product.class);
        product.setId(id);

        System.out.println("Update product: ");
        System.out.println(product.toString());

        productService.updateProduct(product);
        return ResponseEntity.ok().body("Updated product successfully!");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestParam(name = "id") int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body("Deleted product successfully!");
    }
}
