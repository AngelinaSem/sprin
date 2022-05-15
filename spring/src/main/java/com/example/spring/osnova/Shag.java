package com.example.spring.osnova;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Shag {

    @Id
    @GeneratedValue
    private Long shagId;

    private LocalDate date;

    private Integer shagCount;

    @ManyToOne
    @JoinColumn(name="pol_id")
    private Pol pol;

}