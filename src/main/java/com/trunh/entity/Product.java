package com.trunh.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`", length = 50, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "sku", length = 50, nullable = false)
    private String sku;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "`desc`", length = 50)
    private String desc;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Product.ProductGender gender;

    @Column(name = "category", columnDefinition = "ENUM('SHIRT', 'PANT', 'JACKET', 'COAT', 'ACCESSORY')")
    @Enumerated(EnumType.STRING)
    private Product.ProductCategory category;

    @Column(name = "size", columnDefinition = "ENUM('S', 'M', 'L', 'XL', 'XXl')")
    @Enumerated(EnumType.STRING)
    private Product.ProductSize size;

    @Column(name = "image_url", length = 250)
    private String imageUrl;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    public enum ProductGender {
        MALE, FEMALE, UNISEX;
        public static Product.ProductGender toEnum(String name) {
            for (Product.ProductGender item : Product.ProductGender.values()) {
                if (item.toString().equals(name))
                    return item;
            }
            return null;
        }
    }

    public enum ProductCategory {
        SHIRT, PANT, JACKET, COAT, ACCESSORY;
        public static Product.ProductCategory toEnum(String name) {
            for (Product.ProductCategory item : Product.ProductCategory.values()) {
                if (item.toString().equals(name))
                    return item;
            }
            return null;
        }
    }

    public enum ProductSize {
        S, M, L, XL, XXL;
        public static Product.ProductSize toEnum(String name) {
            for (Product.ProductSize item : Product.ProductSize.values()) {
                if (item.toString().equals(name))
                    return item;
            }
            return null;
        }
    }
}
