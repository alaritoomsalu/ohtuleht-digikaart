package com.pooltoon.web.repository;

import com.pooltoon.web.entity.WishCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<WishCounter, Long> {}