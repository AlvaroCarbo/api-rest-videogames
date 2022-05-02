package com.example.apirestvideogames.controller;


import com.example.apirestvideogames.model.entities.VideoGame;
import com.example.apirestvideogames.model.services.VideoGamesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/
@RestController
@RequiredArgsConstructor
public class VideoGamesController {
    private final VideoGamesService videoGamesService;


    @CrossOrigin(origins = "http://localhost:9001")
    @GetMapping("/video-games")
    public ResponseEntity<?> listVideoGames(@RequestParam(value = "price", required = false) String price) {
        List<VideoGame> videoGames = (price != null) ? videoGamesService.listByPriceLessThan(Double.parseDouble(price)) : videoGamesService.listVideoGames();
        return videoGames.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(videoGames);
    }

    @GetMapping("/video-games/{id}")
    public ResponseEntity<?> getVideoGame(@PathVariable String id) {
        return (videoGamesService.getVideoGame(id) == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(videoGamesService.getVideoGame(id));
    }

    @GetMapping("/video-games/genre/{genre}")
    public ResponseEntity<?> listVideoGamesByGenre(@PathVariable String genre) {
        return (videoGamesService.listVideoGamesByGenre(genre).isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(videoGamesService.listVideoGamesByGenre(genre));
    }

    @GetMapping("/video-games/count/{genre}")
    public ResponseEntity<?> countByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(videoGamesService.countByGenre(genre));
    }

    @PostMapping("/video-games")
    public ResponseEntity<?> createVideoGame(@RequestBody VideoGame newVideoGame) {
        return new ResponseEntity<>(videoGamesService.putVideoGame(newVideoGame), HttpStatus.CREATED);
    }

    @DeleteMapping("/video-games/{id}")
    public ResponseEntity<?> deleteVideoGame(@PathVariable String id) {
        return (videoGamesService.deleteVideoGame(id) == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(videoGamesService.deleteVideoGame(id));
    }

    @PutMapping("/video-games")
    public ResponseEntity<?> updateVideoGame(@RequestBody VideoGame updatedVideoGame) {
        return (videoGamesService.updateVideoGame(updatedVideoGame) == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(videoGamesService.updateVideoGame(updatedVideoGame));
    }
}
