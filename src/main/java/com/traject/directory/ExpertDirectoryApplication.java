package com.traject.directory;

import com.traject.directory.model.User;
import com.traject.directory.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpertDirectoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpertDirectoryApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserService userService) {
        return args -> {
            User matt = new User();
            matt.setName("Matt");
            matt.setUrl("https://github.com/MattIPv4");
            matt = userService.createUser(matt);

            User stefan = new User();
            stefan.setName("Stefan");
            stefan.setUrl("https://github.com/stefanprodan");
            stefan = userService.createUser(stefan);

            User teddy = new User();
            teddy.setName("Teddy");
            teddy.setUrl("https://github.com/teddykoker");
            teddy = userService.createUser(teddy);

            matt.addFriends(stefan.getId());
            stefan.addFriends(matt.getId(), teddy.getId());
            teddy.addFriends(stefan.getId());

            userService.updateUser(matt);
            userService.updateUser(stefan);
            userService.updateUser(teddy);
        };
    }

}
