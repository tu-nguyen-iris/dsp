package io.dev.employees;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EmployeesApplication {
    /**
     * Support convert Dto to entity and reverse
     *
     * @return {object}
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);

        List<String> data = Arrays.asList("dasd", "sdasd", "asdasdsd");
        data.stream().map(item -> item.toLowerCase());
        System.out.println(data.stream().map(item -> item.toLowerCase()));


    }

}