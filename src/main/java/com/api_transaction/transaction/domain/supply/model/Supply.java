package com.api_transaction.transaction.domain.supply.model;

import java.time.LocalDateTime;

public class Supply {
    private Long id;
    private Long product;
    private int amount;
    private LocalDateTime date;
    private Long user;


    public Supply(Long id, Long product, int amount, LocalDateTime date, Long user) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
