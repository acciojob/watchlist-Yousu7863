package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
HashMap<String,String> moviemap=new HashMap<>();
List<Movie>movielist=new ArrayList<>();
List<Director>directlist=new ArrayList<>();

    //@Autowired
  //  Movie movie;
    //  @GetMapping("/get-movie-by-name/{name}")
//------------------------------------------
    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie (@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>("add movie success", HttpStatus.CREATED);
    }

    //----------------------------- 2
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new ResponseEntity<>("add Director success", HttpStatus.CREATED);
    }
// ---------- 3 number

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair ( @RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName)
    {
        movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity<>("addMovieDirectorPair success", HttpStatus.OK);
    }
//--------------------------4 number
@GetMapping("/get-movie-by-name/{name}")
public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {

    if(movieService.getMovieByName(name) !=null)
        return new ResponseEntity<>(movieService.getMovieByName(name), HttpStatus.OK);

    else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

}
    //-------------------------------------------------------6
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name) {

        if(movieService.getDirectorByName(name) !=null)
            return new ResponseEntity<>(movieService.getDirectorByName(name), HttpStatus.OK);

        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    //-----------------------
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director) {
        return new ResponseEntity<>(movieService.getMoviesByDirectorName(director), HttpStatus.OK);
    }
    //--------------------
    @GetMapping("/get-all-movies")
    public  ResponseEntity<List<String>> findAllMovies() {
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
    }
    //________________________
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name) {
        movieService.deleteDirectorByName(name);
        return new ResponseEntity<>("deleteDirectorByName successfull", HttpStatus.OK);
    }
    //---------------*************------------
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("deleteAllDirectors successfull", HttpStatus.OK);
    }
}