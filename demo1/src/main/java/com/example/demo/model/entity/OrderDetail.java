package com.example.demo.model.entity;

import jakarta.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Float price;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName ="id")
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName ="id")
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Long id, Integer quantity, Float price, Orders order, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
