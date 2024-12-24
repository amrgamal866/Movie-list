package com.example.Movie_list.Repos;

import com.example.Movie_list.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<
        Role, Long> {
    Optional<Role> findByName(String name); // Find role by name (e.g., "ROLE_USER" or "ROLE_ADMIN")
}
