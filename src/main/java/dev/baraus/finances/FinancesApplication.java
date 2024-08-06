package dev.baraus.finances;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class FinancesApplication {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, System.getProperty("app.environment", "development"));
        SpringApplication.run(FinancesApplication.class, args);
    }
}
