package vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VodConfig {

    @Bean
    String foo() {
        return new String("bar");
    }
}
