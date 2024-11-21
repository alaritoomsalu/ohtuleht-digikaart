package com.pooltoon.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class WishCounter {

    @Id
    private Long id; // Single record with ID 1
    private Long count;

    // Constructors
    public WishCounter() {}

    public WishCounter(Long id, Long count) {
        this.id = id;
        this.count = count;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}