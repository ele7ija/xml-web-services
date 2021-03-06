package rs.ac.uns.ftn.tim5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApiorganvlastiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiorganvlastiApplication.class, args);
    }
}
