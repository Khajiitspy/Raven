package Graveyard;

import Graveyard.entities.UserEntity;
import Graveyard.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.NoSuchElementException;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner runner(IUserRepository repository) {
        return args -> {
            // if (repository.findByUsername("admin").isEmpty()) {
            //     UserEntity user = new UserEntity();
            //     user.setUsername("admin");
            //     user.setPassword("");
            //     repository.save(user);
            //     System.out.println("✅ Created admin user.");
            // } else {
            //     System.out.println("ℹ️ Admin user already exists, skipping insert.");
            // }
        };
    }
}
