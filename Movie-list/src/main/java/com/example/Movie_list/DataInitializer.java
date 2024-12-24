package com.example.Movie_list;

import com.example.Movie_list.Models.Role;
import com.example.Movie_list.Models.User;
import com.example.Movie_list.Repos.RoleRepository;
import com.example.Movie_list.Repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.HashSet;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            PasswordEncoder encoder = new BCryptPasswordEncoder();

            // Check if roles already exist, if not, create them
            Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> roleRepository.save(new Role("ADMIN")));
            Role userRole = roleRepository.findByName("USER").orElseGet(() -> roleRepository.save(new Role("USER")));

            if (userRepository.count() == 0) {
                // Create users and assign roles
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(adminRole);
                User admin = new User("admin", encoder.encode("adminPassword"), adminRoles);

                Set<Role> userRoles = new HashSet<>();
                userRoles.add(userRole);
                User user = new User("user", encoder.encode("userPassword"), userRoles);

                userRepository.save(admin);
                userRepository.save(user);
            }
        };
    }
}