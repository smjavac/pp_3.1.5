package org.example.config;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInitialization {
    private final UserService userService;

    public DBInitialization(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initializeUsers() {
        User user = userService.findByEmail("admin@mail.ru");
        if (user == null) {
            Role userRole = new Role("ROLE_USER");
            Role adminRole = new Role("ROLE_ADMIN");
            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(userRole);
            adminRoles.add(adminRole);
            User admin = new User(adminRoles, "user", "lastname", 30L, "admin@mail.ru", "admin");

            userService.save(admin);

        }

    }

}
