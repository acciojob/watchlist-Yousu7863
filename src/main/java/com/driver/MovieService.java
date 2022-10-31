package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepo;

    void addMovie(Movie movie) {
        movieRepo.addMovie(movie);
    }

    void addDirector(Director director) {
        movieRepo.addDirector(director);
    }

    void addMovieDirectorPair(String movieName, String directorName) {
        movieRepo.addMovieDirectorPair(movieName, directorName);
    }

    Movie getMovieByName(String name) {
        return movieRepo.getMovieByName(name);
    }

    Director getDirectorByName(String name) {
        return movieRepo.getDirectorByName(name);
    }

    List<String> getMoviesByDirectorName(String director) {
        return movieRepo.getMoviesByDirectorName(director);
    }

    List<String> findAllMovies() {
        return movieRepo.findAllMovies();
    }

    void deleteDirectorByName(String name) {
        movieRepo.deleteDirectorByName(name);
    }

    void deleteAllDirectors() {
        movieRepo.deleteAllDirectors();
    }
}
