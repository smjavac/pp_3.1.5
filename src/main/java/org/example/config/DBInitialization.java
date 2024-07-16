package org.example.config;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInitialization {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(DBInitialization.class);

    public DBInitialization(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initializeUsers() {
        User user = userService.findByEmail("admin@gmail.com");
        if (user != null) { logger.info("user is not null"); }

        if (user == null) {
            logger.info("user is null");
            Role userRole = new Role("ROLE_USER");
            Role adminRole = new Role("ROLE_ADMIN");
            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(userRole);
            adminRoles.add(adminRole);
            User admin = new User(adminRoles, "user", "lastname", 30L, "admin@gmail.com", "admin");

            userService.save(admin);

        }

    }

}
