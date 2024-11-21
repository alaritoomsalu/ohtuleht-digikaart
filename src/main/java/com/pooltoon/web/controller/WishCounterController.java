package com.pooltoon.web.controller;

import com.pooltoon.web.service.WishCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/count-wish")
@CrossOrigin(origins = "https://ohtuleht-digikaart.netlify.app", allowCredentials = "true")
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

    // New endpoint to retrieve the current counter
    @GetMapping("/counter")
    public ResponseEntity<Map<String, Long>> getCounter() {
        Long currentCount = wishCounterService.getCurrentCount();
        return ResponseEntity.ok(Map.of("totalWishes", currentCount));
    }

    // New endpoint to reset the counter to 0
    @PostMapping("/counter/reset")
    public ResponseEntity<Map<String, String>> resetCounter() {
        wishCounterService.resetCounter();
        return ResponseEntity.ok(Map.of("message", "Counter has been reset to 0"));
    }
}