package com.example.spring.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.spring.osnova.Pul;

@Repository
public interface PulJPARep extends JpaRepository<Pul, Long> {
}

