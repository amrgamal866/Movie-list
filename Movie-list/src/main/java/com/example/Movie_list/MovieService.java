package com.example.Movie_list;

import com.example.Movie_list.Global.ResourceNotFoundException;
import com.example.Movie_list.Models.Movie;
import com.example.Movie_list.Repos.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);


    @Autowired
    private MovieRepository movieRepository;

    public Movie getById(Long id) {
        try {
            Movie movie = movieRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Movie with id " + id + " not found"));
            logger.info("Movie with ID {} found: {}", id, movie.getTitle());
            return movie;
        }
        catch (ResourceNotFoundException ex) {
            logger.error("Movie with ID {} not found", id);
            throw ex; // Re-throw the exception so it can be handled globally
        }

    }
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
    public void deleteAllByIds(List<Long> ids) {
        movieRepository.deleteAllById(ids);
    }
    public List<Movie>
    findAll() {
        return movieRepository.findAll();
    }

    public Page<Movie> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> movies = movieRepository.findAll(pageable);
        return movieRepository.findAll(pageable);
    }

    public void saveRating(Long movieId, int rating) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setRating(rating);
        movieRepository.save(movie); // Save the updated movie with the new rating
    }

}
