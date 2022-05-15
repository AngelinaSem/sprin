package com.example.spring.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PolDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer weight;
    private String password;
}