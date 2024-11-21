package com.pooltoon.web.repository;

import com.pooltoon.web.entity.WishCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishCountRepository extends JpaRepository<WishCount, Long> {}