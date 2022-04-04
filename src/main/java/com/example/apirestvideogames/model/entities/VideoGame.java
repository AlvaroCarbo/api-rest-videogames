package com.example.apirestvideogames.model.entities;

import lombok.Data;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class VideoGame {
    @Id
    private String idVideoGame;
    private String name;
    private String genre;
    private double price;
    private float criticEvaluation;
}
