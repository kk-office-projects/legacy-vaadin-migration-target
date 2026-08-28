package de.kkendzia.legacy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LegacyVaadinApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegacyVaadinApplication.class, args);
    }

    @Bean
    CommandLineRunner seedCustomers(CustomerRepository repository) {
        return args -> {
            repository.save(new Customer("Ada", "Lovelace", "ada@example.com"));
            repository.save(new Customer("Grace", "Hopper", "grace@example.com"));
            repository.save(new Customer("Alan", "Turing", "alan@example.com"));
        };
    }
}

