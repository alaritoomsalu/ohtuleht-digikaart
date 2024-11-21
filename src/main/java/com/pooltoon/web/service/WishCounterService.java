package com.pooltoon.web.service;

import com.pooltoon.web.entity.WishCount;
import com.pooltoon.web.repository.WishCountRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class WishCounterService {

    private final WishCountRepository repository;

    @Autowired
    public WishCounterService(WishCountRepository repository) {
        this.repository = repository;
    }

    public synchronized Long incrementWishCount() {
        // Fetch the wish count record (ID 1)
        WishCount wishCount = repository.findById(1L).orElse(new WishCount(1L, 0L));

        // Increment and save the count
        wishCount.setCount(wishCount.getCount() + 1);
        repository.save(wishCount);

        return wishCount.getCount();
    }
}