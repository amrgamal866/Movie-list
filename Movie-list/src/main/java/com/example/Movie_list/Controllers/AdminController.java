package com.example.Movie_list.Controllers;

import com.example.Movie_list.Models.Movie;
import com.example.Movie_list.MovieService;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping ("/api/admin")
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "Authorization")
public class AdminController {
    @Autowired
    private MovieService movieService;
    private final String OMDB_API_URL = "https://www.omdbapi.com/?apikey=681991a6&s=";

    @GetMapping("/search")
    public String SearchMovie (@RequestParam String query, @RequestParam(defaultValue = "1") int page)
    {
        RestTemplate template = new RestTemplate();
        String url = OMDB_API_URL + query + "&page=" + page;
        return template.getForObject(url, String.class);
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody List<Movie> movies){

        for (Movie movie : movies) {
            movieService.save(movie);
        }    }
@PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieService.deleteById(id);
            return "Deleted Successfully!";
    }
    @PostMapping("/deleteMovies")
    public String deleteMovies(@RequestBody List<Long> movieIds) {
        // Loop through the list of IDs and delete each one
        for (Long id : movieIds) {
            movieService.deleteById(id);  // Delete movie by its ID
        }
        return "Deleted Movies Successfully!";
    }
    @GetMapping("/movies")
    public Page<Movie> getAllMovies(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        return movieService.findAll(page, size);
    }

}
