package com.example.spring.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.spring.osnova.Pol;

@Repository
public interface PolJPARep extends JpaRepository<Pol, Long> {

    Pol findByLogin(String login);
}