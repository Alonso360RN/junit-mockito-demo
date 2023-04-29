package com.alonso360rn.blockingexample.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "environment")
public class EnvironmentConfiguration {

    @NotNull
    private JsonPlaceholder jsonPlaceholder;

    @Getter
    @Setter
    @Validated
    public static class JsonPlaceholder {

        @NotBlank
        private String host;
    }
}
