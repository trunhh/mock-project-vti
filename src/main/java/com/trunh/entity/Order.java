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
@Table(name = "`order`")
public class Order {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_cost")
    private int totalCost;

    @Column(name = "payment", columnDefinition = "ENUM('CASH', 'ONLINE_BANKING', 'CREDIT_OR_DEBIT')")
    @Enumerated(EnumType.STRING)
    private Order.OrderPayment payment;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "`status`", columnDefinition = "ENUM('ORDERED', 'PACKED', 'IN_TRANSIT', 'DELIVERED')")
    @Enumerated(EnumType.STRING)
    private Order.OrderStatus status;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItems;

    public void addCosts (int cost) {
        this.totalCost += cost;
    }
    public enum OrderPayment {
        CASH, ONLINE_BANKING, CREDIT_OR_DEBIT;
        public static Order.OrderPayment toEnum(String name) {
            for (Order.OrderPayment item : Order.OrderPayment.values()) {
                if (item.toString().equals(name))
                    return item;
            }
            return null;
        }
    }

    public enum OrderStatus {
        ORDERED, PACKED, IN_TRANSIT, DELIVERED;
        public static Order.OrderStatus toEnum(String name) {
            for (Order.OrderStatus item : Order.OrderStatus.values()) {
                if (item.toString().equals(name))
                    return item;
            }
            return null;
        }
    }
}
