package com.pooltoon.web.service;

import com.pooltoon.web.entity.WishCounter;
import com.pooltoon.web.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishCounterService {

    private final WishRepository wishRepository;

    @Autowired
    public WishCounterService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public synchronized Long incrementWishCount() {
        WishCounter counter = wishRepository.findById(1L).orElse(new WishCounter(1L, 0L));
        counter.setCount(counter.getCount() + 1);
        wishRepository.save(counter);
        return counter.getCount();
    }

    // Get the current counter value
    public synchronized Long getCurrentCount() {
        return wishRepository.findById(1L).map(WishCounter::getCount).orElse(0L);
    }

    // Reset the counter to 0
    public synchronized void resetCounter() {
        WishCounter counter = wishRepository.findById(1L).orElse(new WishCounter(1L, 0L));
        counter.setCount(0L);
        wishRepository.save(counter);
    }
}