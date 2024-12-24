package com.example.Movie_list.Repos;

import com.example.Movie_list.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
