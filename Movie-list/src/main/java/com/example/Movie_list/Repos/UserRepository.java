package com.example.Movie_list.Repos;

import com.example.Movie_list.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username); // To find user by username
}
