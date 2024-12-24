package com.example.Movie_list.Controllers;



import com.example.Movie_list.Models.Movie;
import com.example.Movie_list.MovieService;
import com.example.Movie_list.dto.RatingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "Authorization")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MovieService movieService;

   /* @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.findAll();
    }*/
   @GetMapping("/movies")
   public Page<Movie> getAllMovies(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
       return movieService.findAll(page, size);
   }

   @PostMapping("/rate")
   public ResponseEntity<String>movieRate(@RequestBody RatingRequest ratingRequest)
    {
        logger.info("Request to rate movie: {}", ratingRequest.getMovieId());
try {


    movieService.saveRating(ratingRequest.getMovieId(), ratingRequest.getRating());
    return ResponseEntity.ok("Rating saved successfully.");
}
catch (Exception ex) {
    logger.error("Error occurred while Rating movie: {}", ratingRequest.getMovieId(), ex);
    return ResponseEntity.status(500).build();
}    }
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getById(id);
    }
}

