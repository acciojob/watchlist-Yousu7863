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
HashMap<Movie,Director> moviemap=new HashMap<>();
private List<Movie>movielist;

    public List<Movie> getMovielist() {
        return movielist;
    }

    public void setMovielist(List<Movie> movielist) {
        this.movielist = movielist;
    }

    public MovieController() {
        this.movielist =new ArrayList<Movie>();
    }

    List<Director>directlist=new ArrayList<>();

    //@Autowired
  //  Movie movie;
    //  @GetMapping("/get-movie-by-name/{name}")
@GetMapping("/get-movie-by-name/{name}")
public ResponseEntity getMovieByname(@PathVariable String name){
        for(Movie m:movielist){
            if(m.getName()==name){
                return new ResponseEntity<>(m,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

@PostMapping("/add_movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
    try {
        movielist.add(movie);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    catch (Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
@PostMapping("/add_director")
 public ResponseEntity<String> addDirector(@RequestBody Director director) {
    try {
        directlist.add(director);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getDirectorByname(@PathVariable String name){
        for(Director d:directlist){
            if(d.getName()==name){
                return new ResponseEntity<>(d,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    List<String>moviename=new ArrayList<>();
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String name){
    List<Director>ndelist=new ArrayList<>();
      for(Director d:directlist){
          if(d.getName()==name)
              ndelist.add(d);
      }
      for(Movie mo:movielist){
          for(Director di:ndelist){
              if(mo.getImdbRating()== di.getImdbRating()){
                  moviename.add(mo.getName());
              }
          }
      }
      return new ResponseEntity<>(moviename,HttpStatus.CREATED);
    }
    //------------
    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        return new ResponseEntity(movielist,HttpStatus.CREATED);
    }
    //---------------
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity  deleteDirectorByName(@RequestParam String name){
        List<Director>did=new ArrayList<>();
        for(int i=0;i<directlist.size();i++){
            if(directlist.get(i).getName()==name){
                did.add(directlist.remove(i));
            }
        }
        for(int j=0;j<movielist.size();j++){
            if(movielist.get(j).getImdbRating()==did.get(j).getImdbRating()){
                movielist.remove(j);
            }
        }
            return new ResponseEntity<>("delete successfull",HttpStatus.OK);
    }@GetMapping("/delete-all-directors")
    public ResponseEntity  deleteAllDirectors(){
        movielist=new ArrayList<>();
        directlist=new ArrayList<>();
        return new ResponseEntity<>("all delete successfull",HttpStatus.OK);
    }
}
