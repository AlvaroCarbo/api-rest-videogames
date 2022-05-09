package com.example.apirestvideogames.model.repositories;

import com.example.apirestvideogames.model.entities.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoGamesRepository extends JpaRepository<VideoGame, String> {
    List<VideoGame> findByGenre(String r); //l'atribut gènere ha d'existir a la classe Usuari

    long countByGenre(String r);

    List<VideoGame> findByPriceLessThan(double price);
}
