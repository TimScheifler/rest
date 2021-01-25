package com.example.restexample;

import com.example.restexample.employee.Employee;
import com.example.restexample.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * Spring will run ALL CommandLineRunner beans when the application is running.
     * @param repository
     * @return
     */
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository){
        return args -> {
            log.info("Preloading "+repository.save(new Employee("Tim Scheifler","CEO")));
            log.info("Preloading"+repository.save(new Employee("Lutz Weigold","Trainee")));
        };
    }
}
