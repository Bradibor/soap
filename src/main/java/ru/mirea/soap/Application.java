package ru.mirea.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import ru.mirea.xmltools.xmlprocessing.Marshaller;
import ru.mirea.xmltools.xmlprocessing.OrganizationService;
import ru.mirea.xmltools.xmlprocessing.Unmarshaller;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OrganizationService organizationService(Environment env) {
        return new OrganizationService(new Marshaller(), new Unmarshaller(), env.getProperty("working-dir"));
    }
}