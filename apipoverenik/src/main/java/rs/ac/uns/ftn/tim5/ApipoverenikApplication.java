package rs.ac.uns.ftn.tim5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApipoverenikApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApipoverenikApplication.class, args);
    }

}
