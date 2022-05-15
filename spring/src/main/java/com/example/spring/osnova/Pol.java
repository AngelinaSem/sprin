package com.example.spring.osnova;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Pol {
    @Id
    @GeneratedValue
    private Long id;

    private String login;

    @NonNull
    private String firstName;

    private String lastName;

    private Integer age;

    private Integer weight;

    private int hashPassword;

    @OneToMany(mappedBy = "pol")
    private List<Pul> bpmData;

    @OneToMany(mappedBy = "pol")
    private List<Shag> shagData;


}