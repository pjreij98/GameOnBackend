package com.gameon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public GreetResponse greet() {
        GreetResponse greetResponse = new GreetResponse(
                "greet",
                List.of("Java", "C#", "JavaScript"),
                new Person("Alex", 28, 30_000)
        );
        return greetResponse;
    }

    record Person(String name, int age, double savings) {

    }

    record GreetResponse(
            String greet,
            List<String> favProgrammingLanguages,
            Person person
            ) {}
}
