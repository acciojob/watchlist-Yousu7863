package com.driver;

import org.springframework.stereotype.Component;

import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieRepository {

    HashMap<String, Movie> mapMovie = new HashMap<>();
    HashMap<String, Director> mapDirector = new HashMap<>();
    HashMap<String, String> mapPair = new HashMap<>();

    void addMovie(Movie movie) {
        mapMovie.put(movie.getName(), movie);
    }


    void addDirector(Director director) {
        mapDirector.put(director.getName(), director);
    }

    void addMovieDirectorPair(String movieName, String directorName) {
        mapPair.put(movieName, directorName);
    }

    Movie getMovieByName(String name) {
        for (String n : mapMovie.keySet()) {
            if (n.equals(name)) return mapMovie.get(n);
        }
        return null;
    }

    Director getDirectorByName(String name) {
        for (String n : mapDirector.keySet()) {
            if (n.equals(name)) return mapDirector.get(n);
        }
        return null;
    }

    List<String> getMoviesByDirectorName(String director) {
        List<String> moviesList = new ArrayList<>();
        for (Map.Entry<String, String> entry : mapPair.entrySet()) {
            if (entry.getValue().equals(director)) moviesList.add(entry.getKey());
        }
        return moviesList;
    }

    List<String> findAllMovies() {
        List<String> allMoviesList = new ArrayList<>();
        allMoviesList.addAll(mapMovie.keySet());
        return allMoviesList;
    }

    void deleteDirectorByName(String name) {
        //deleting all the occurance of director in the director_DB
        for (String s : mapDirector.keySet()) {
            if (s.equals(name)) mapDirector.remove(s);
        }
        for (Map.Entry<String, String> entry : mapPair.entrySet()) {
            if (entry.getValue().equals(name)) {
                // entry== moviesName here
                // first delete the movie from movie_DB
                if (mapMovie.containsKey(entry.getKey())) mapMovie.remove(entry.getKey());
                // now remove the pair from pair_DB
                if (mapPair.containsKey(entry.getKey())) mapPair.remove(entry.getKey());
            }
        }
    }

    void deleteAllDirectors() {
        for (String director : mapDirector.keySet()) {
            for (Map.Entry<String, String> entry : mapPair.entrySet()) {
                if (entry.getValue().equals(director)) {
                    if (mapMovie.containsKey(entry.getKey())) mapMovie.remove(entry.getKey());
                    if (mapPair.containsKey(entry.getKey())) mapPair.remove(entry.getKey());
                }
            }
            mapDirector.remove(director);
        }
    }
}