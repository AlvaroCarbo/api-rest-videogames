package com.example.apirestvideogames.model.services;


import com.example.apirestvideogames.model.entities.VideoGame;
import com.example.apirestvideogames.model.repositories.VideoGamesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoGamesService {
    private final VideoGamesRepository videoGamesRepository;

    public List<VideoGame> listVideoGames(){

        return videoGamesRepository.findAll();
    }


    public List<VideoGame> listVideoGamesByGenre(String genre){
        return videoGamesRepository.findByGenre(genre);
    }

    public long countByGenre(String genre){
        return videoGamesRepository.countByGenre(genre);
    }

    public List<VideoGame> listByPriceLessThan(double price){
        return videoGamesRepository.findByPriceLessThan(price);
    }

    //consultar ítem per id
    public VideoGame getVideoGame(String id){
        return videoGamesRepository.findById(id).orElse(null);
    }

    //afegir ítem
    public VideoGame putVideoGame(VideoGame it){
        return videoGamesRepository.save(it);
    }

    //modificar sencer, si existeix el canvia, sino retorna null
    public VideoGame updateVideoGame(VideoGame videoGame){
        VideoGame aux=null;
        if(videoGamesRepository.existsById(videoGame.getIdVideoGame())) aux=videoGamesRepository.save(videoGame);
        return aux;
    }

    //eliminar ítem per id
    //si no existeix id retorna null
    public VideoGame deleteVideoGame(String id){
        VideoGame res=videoGamesRepository.findById(id).orElse(null);
        if(res!=null) videoGamesRepository.deleteById(id);
        return res;
    }
}
