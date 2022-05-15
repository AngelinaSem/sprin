package com.example.spring.osnova;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Pul {

    @Id
    @GeneratedValue
    private Long pulId;

    private LocalDateTime date;

    private Integer bpm;

    @ManyToOne
    @JoinColumn(name="pol_id")
    private Pol pol;

}
