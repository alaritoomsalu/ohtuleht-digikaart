package com.pooltoon.web.controller;

import com.pooltoon.web.service.WishCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/count-wish")
public class WishCounterController {

    private final WishCounterService wishCounterService;

    @Autowired
    public WishCounterController(WishCounterService wishCounterService) {
        this.wishCounterService = wishCounterService;
    }

    @PostMapping
    public Map<String, Long> countWish() {
        Long totalWishes = wishCounterService.incrementWishCount();
        return Map.of("totalWishes", totalWishes);
    }
}